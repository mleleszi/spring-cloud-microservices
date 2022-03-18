package com.example.productservice.service;

public class NoSuchProductException extends RuntimeException {
    public NoSuchProductException(Long id) {
        super(String.format("No product found with id: %s", id));
    }
}
