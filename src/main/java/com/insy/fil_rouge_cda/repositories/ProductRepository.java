package com.insy.fil_rouge_cda.repositories;

import com.insy.fil_rouge_cda.models.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
}
