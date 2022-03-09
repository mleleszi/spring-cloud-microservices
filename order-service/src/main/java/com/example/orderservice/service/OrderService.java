package com.example.orderservice.service;

import com.example.orderservice.dto.OrderDto;

public interface OrderService {
    String placeOrder(OrderDto orderDto);
}
