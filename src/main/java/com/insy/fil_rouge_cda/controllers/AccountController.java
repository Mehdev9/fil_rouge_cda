package com.insy.fil_rouge_cda.controllers;


import com.insy.fil_rouge_cda.models.Account;
import com.insy.fil_rouge_cda.services.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @GetMapping("/accounts/me")
    public ResponseEntity<Account>getCurrentAccount(){
        Account currentAccount = accountService.getCurrentAccount();
        return ResponseEntity.ok().body(currentAccount);
    }
}
