package com.example.productservice;

import com.example.productservice.model.CreateProductModel;
import com.example.productservice.service.ProductDto;
import com.example.productservice.service.ProductService;
import net.sf.ehcache.CacheManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class ProductServiceApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    private ProductService productService;

    /*
        @Test

    public void test() {
        // given
        CreateProductModel createProductModel =
                new CreateProductModel("name", "desc", new BigDecimal(1000));

        // when
        ProductDto productDto = productService.save(createProductModel.toProductDto());
        productService.findById(productDto.getId());
        int size = CacheManager.ALL_CACHE_MANAGERS
                .get(0)
                .getCache("com.example.productservice.repository.ProductEntity")
                .getSize();

        assertTrue(size > 0);


    }
*/
}
