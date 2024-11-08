package com.insy.fil_rouge_cda.repositories;

import com.insy.fil_rouge_cda.models.DeliveryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryRepository extends JpaRepository<DeliveryEntity, Long> {
}
