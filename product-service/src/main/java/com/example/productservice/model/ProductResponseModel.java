package com.example.productservice.model;

import com.example.productservice.service.ProductDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponseModel {

    @NotNull
    @Min(1)
    private  Long id;

    @NotNull
    @NotBlank
    private String name;

    @NotNull
    @NotBlank
    private String description;

    @NotNull
    private BigDecimal price;

    public ProductDto toProductDto() {
        return new ProductDto(id, name, description, price);
    }

    public ProductResponseModel(ProductDto productDto) {
        this.id = productDto.getId();
        this.name = productDto.getName();
        this.description = productDto.getDescription();
        this.price = productDto.getPrice();
    }
}
