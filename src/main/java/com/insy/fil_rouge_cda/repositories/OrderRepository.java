package com.insy.fil_rouge_cda.repositories;

import com.insy.fil_rouge_cda.models.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
}
