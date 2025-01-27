package com.insy.fil_rouge_cda.repositories;

import com.insy.fil_rouge_cda.models.CartEntity;
import com.insy.fil_rouge_cda.models.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<CartEntity, Long> {

    CartEntity findByUserUsername(String userName);
}
