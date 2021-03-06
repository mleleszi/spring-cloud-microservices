package com.example.productservice.service;

import com.example.productservice.repository.ProductEntity;
import com.example.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    @CachePut(value = "productCache", key = "#productDto")
    public ProductDto save(ProductDto productDto) {
        log.info("ProductService - save");
        return new ProductDto(productRepository.save(productDto.toEntity()));
    }

    @Override
    @Cacheable("productCache")
    public ProductDto findById(Long id) {
        log.info("ProductService - findById");
        return new ProductDto(
                productRepository
                        .findById(id)
                        .orElseThrow(() -> new NoSuchProductException(id))
        );
    }

    @Override
    @Cacheable("productCache")
    public List<ProductDto> findAll(int page, int size) {
        log.info("ProductService - findAll");
        return productRepository
                    .findAllBy(PageRequest.of(page, size))
                    .stream()
                    .map(ProductDto::new)
                    .collect(Collectors.toList());
    }

    @Override
    @CachePut(value = "productCache", key = "#productDto")
    public ProductDto update(ProductDto productDto) {
        log.info("ProductService - update");
        if (productRepository.findById(productDto.getId()).isEmpty())
            throw new NoSuchProductException(productDto.getId());
        else
            return new ProductDto(productRepository.save(productDto.toEntity()));
    }

    @Override
    @CacheEvict(value = "productCache", key = "#id")
    public void deleteById(Long id) {
        log.info("ProductService - update");
        try {
            productRepository.deleteById(id);
        } catch (EmptyResultDataAccessException ex) {
            throw new NoSuchProductException(id);
        }

    }


}
