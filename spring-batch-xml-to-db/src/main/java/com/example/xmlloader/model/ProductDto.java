package com.example.xmlloader.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "product")
public class ProductDto {
    private String name;
    private String description;
    private BigDecimal price;
}
