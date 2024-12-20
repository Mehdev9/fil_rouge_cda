package com.insy.fil_rouge_cda.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ContactFormValidationException extends RuntimeException {

    // Getter pour les erreurs
    private List<String> errors;

    public ContactFormValidationException(BindingResult result) {
        // Collecter les erreurs de validation à partir de BindingResult
        this.errors = result.getFieldErrors().stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.toList());
    }

}
