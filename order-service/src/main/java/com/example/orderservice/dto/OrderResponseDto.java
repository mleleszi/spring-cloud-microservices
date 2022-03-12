package com.example.orderservice.dto;

import com.example.orderservice.model.Order;
import com.example.orderservice.model.OrderLineItems;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponseDto {
    private String orderNumber;
    private List<OrderLineItems> orderLineItemsList;

    public OrderResponseDto(Order order) {
        this.orderNumber = order.getOrderNumber();
        this.orderLineItemsList = order.getOrderLineItems();
    }
}
