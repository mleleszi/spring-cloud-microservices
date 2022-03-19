package com.example.orderservice.service;

import com.example.orderservice.dto.OrderDto;
import com.example.orderservice.dto.OrderResponseDto;
import com.example.orderservice.model.Order;

import java.util.List;

public interface OrderService {
    String placeOrder(OrderDto orderDto);

    List<OrderResponseDto> findAll();
    OrderResponseDto findById(Long id);
}
