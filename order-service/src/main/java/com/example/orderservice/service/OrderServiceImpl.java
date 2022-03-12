package com.example.orderservice.service;

import com.example.orderservice.client.InventoryClient;
import com.example.orderservice.dto.OrderDto;
import com.example.orderservice.dto.OrderResponseDto;
import com.example.orderservice.model.Order;
import com.example.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final InventoryClient inventoryClient;
    private final StreamBridge streamBridge;

    @Override
    public String placeOrder(OrderDto orderDto) {
        if (allProductsInStock(orderDto)) {
            Order order = new Order();
            order.setOrderLineItems(orderDto.getOrderLineItemsList());
            order.setOrderNumber(UUID.randomUUID().toString());

            orderRepository.save(order);

            log.info("sender order details with order id {} to notification service", order.getId());
            streamBridge.send("notificationEventSupplier-out-0", MessageBuilder.withPayload(order.getId()).build());

            return "Order placed succesfully";
        }
        return "Order failed, one of the products is not in stock";
    }

    private boolean allProductsInStock(OrderDto orderDto) {
        return orderDto
                .getOrderLineItemsList()
                .stream()
                .allMatch(lineItem -> {
                    log.info("making a call to inventory service for skuCode {}", lineItem.getSkuCode());
                    return inventoryClient.checkStock(lineItem.getSkuCode());
                });
    }

    @Override
    public List<OrderResponseDto> findAll() {
        return orderRepository
                .findAll()
                .stream()
                .map(OrderResponseDto::new)
                .collect(Collectors.toList());
    }
}
