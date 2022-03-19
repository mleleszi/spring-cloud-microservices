package com.example.productservice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.PagingAndSortingRepository;

import javax.persistence.QueryHint;


import java.util.List;

import static org.hibernate.jpa.QueryHints.HINT_CACHEABLE;

public interface ProductRepository extends PagingAndSortingRepository<ProductEntity, Long> {

    @QueryHints( value = { @QueryHint(name = HINT_CACHEABLE, value = "true")})
    List<ProductEntity> findAllBy(Pageable pageable);
}
