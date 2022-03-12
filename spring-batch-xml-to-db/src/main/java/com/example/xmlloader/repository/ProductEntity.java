package com.example.xmlloader.repository;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "product")
@Getter
@Setter
@NoArgsConstructor
public class ProductEntity {

    @Id
    @GeneratedValue
    private Long id;

    //@Column(unique = true)
    private String name;
    private String description;
    private BigDecimal price;

    public ProductEntity(String name, String description, BigDecimal price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }
}
