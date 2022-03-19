package com.example.productservice.controller;

import com.example.productservice.model.CreateProductModel;
import com.example.productservice.model.ProductResponseModel;
import com.example.productservice.repository.ProductRepository;
import com.example.productservice.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/product")
@AllArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public List<ProductResponseModel> getAllProducts(@RequestParam int page, @RequestParam int size) {
       return productService
               .findAll(page, size)
               .stream()
               .map(ProductResponseModel::new)
               .collect(Collectors.toList());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponseModel createProduct(@RequestBody CreateProductModel product) {
        return new ProductResponseModel(productService.save(product.toProductDto()));
    }

    @GetMapping("/{id}")
    public ProductResponseModel getProductById(@PathVariable Long id) {
        return new ProductResponseModel(productService.findById(id));
    }

    @PutMapping
    public ProductResponseModel update(@RequestBody ProductResponseModel productResponseModel) {
        return new ProductResponseModel(productService.update(productResponseModel.toProductDto()));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        productService.deleteById(id);
    }

}
