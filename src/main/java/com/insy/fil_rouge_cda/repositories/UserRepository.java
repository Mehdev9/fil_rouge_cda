package com.insy.fil_rouge_cda.repositories;

import com.insy.fil_rouge_cda.models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
}
