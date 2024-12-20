package com.insy.fil_rouge_cda.services;

import com.insy.fil_rouge_cda.Dtos.ContactFormDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ContactService {

    private final MailService mailService;

    public void processContactForm(ContactFormDTO contactFormDTO) {
        System.out.println("Message de contact reçu :");
        System.out.println("Email: " + contactFormDTO.getEmail());
        System.out.println("Objet: " + contactFormDTO.getObject());
        System.out.println("Message: " + contactFormDTO.getMessage());
        mailService.sendEmail(contactFormDTO.getEmail(), contactFormDTO.getObject(), contactFormDTO.getMessage());
    }
}
