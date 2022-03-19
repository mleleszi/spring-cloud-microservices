package com.example.productservice.service;

import java.util.List;

public interface ProductService {
    ProductDto save(ProductDto productDto);
    ProductDto findById(Long id);
    List<ProductDto> findAll(int page, int size);
    ProductDto update(ProductDto productDto);
    void deleteById(Long id);
}
