package com.example.productservice.service;

import com.example.productservice.repository.ProductEntity;
import com.example.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public ProductDto save(ProductDto productDto) {
        return new ProductDto(productRepository.save(productDto.toEntity()));
    }

    @Override
    public ProductDto findById(Long id) {
        return new ProductDto(
                productRepository
                        .findById(id)
                        .orElseThrow(() -> new NoSuchProductException(id))
        );
    }

    @Override
    public List<ProductDto> findAll() {
         return productRepository
                    .findAll()
                    .stream()
                    .map(ProductDto::new)
                    .collect(Collectors.toList());
    }
}
