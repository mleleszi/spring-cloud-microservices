package com.example.xmlloader.service;

import com.example.xmlloader.model.ProductDto;
import com.example.xmlloader.repository.ProductEntity;
import com.example.xmlloader.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public void save(ProductDto productDto){
        productRepository.save(new ProductEntity(productDto.getName(), productDto.getDescription(), productDto.getPrice()));
    }
}
