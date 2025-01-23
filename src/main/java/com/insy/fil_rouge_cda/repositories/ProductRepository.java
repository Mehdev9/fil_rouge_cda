package com.insy.fil_rouge_cda.repositories;

import com.insy.fil_rouge_cda.models.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    List<ProductEntity> findByNameContainingIgnoreCase(String name);

    List<ProductEntity> findByCategoryIgnoreCase(String category);

    List<ProductEntity> findByBrandIgnoreCase(String brand);

    List<ProductEntity> findByPriceBetween(Double minPrice, Double maxPrice);

    List<ProductEntity> findByOrderByPriceAsc();
    List<ProductEntity> findByOrderByPriceDesc();

}
