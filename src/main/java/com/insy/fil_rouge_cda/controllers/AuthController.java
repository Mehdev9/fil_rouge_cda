package com.insy.fil_rouge_cda.controllers;


import com.insy.fil_rouge_cda.Dtos.AuthRequest;
import com.insy.fil_rouge_cda.Dtos.RegisterRequest;
import com.insy.fil_rouge_cda.services.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AccountService accountService;


    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody RegisterRequest request) {
        accountService.register(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/authenticate")
    public ResponseEntity<String> authenticate(@RequestBody AuthRequest request) {
        String token = accountService.authenticate(request);
        return ResponseEntity.ok().body(token);
    }




}
