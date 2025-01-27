package com.insy.fil_rouge_cda.repositories;

import com.insy.fil_rouge_cda.models.CartProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartProductRepository extends JpaRepository<CartProductEntity, Long> {
}
