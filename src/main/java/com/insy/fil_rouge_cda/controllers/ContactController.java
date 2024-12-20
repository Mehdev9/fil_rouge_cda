package com.insy.fil_rouge_cda.controllers;

import com.insy.fil_rouge_cda.Dtos.ContactFormDTO;
import com.insy.fil_rouge_cda.services.ContactService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ContactController {

    private final ContactService contactService;

    @PostMapping("/contact")
    @ResponseStatus(HttpStatus.OK)
    public void submitContactForm(@Valid @RequestBody ContactFormDTO contactFormDTO) {

        contactService.processContactForm(contactFormDTO);
    }
}
