package com.insy.fil_rouge_cda.repositories;


import com.insy.fil_rouge_cda.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IAccountRepository extends JpaRepository<Account, String> {

    Optional<Account> findByUsernameIgnoreCase(String username);
}
