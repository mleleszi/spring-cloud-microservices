package com.example.productservice.service;

import java.util.List;

public interface ProductService {
    ProductDto save(ProductDto productDto);
    ProductDto findById(Long id);
    List<ProductDto> findAll();
}
