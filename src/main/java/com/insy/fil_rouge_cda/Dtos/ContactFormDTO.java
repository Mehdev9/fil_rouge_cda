package com.insy.fil_rouge_cda.Dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ContactFormDTO {

    // Getters and Setters
    @Email(message = "L'email doit être valide")
    @NotBlank(message = "L'email est obligatoire")
    private String email;

    @NotBlank(message = "L'objet est obligatoire")
    private String object;

    @NotBlank(message = "Le message est obligatoire")
    @Size(min = 10, message = "Le message doit comporter au moins 10 caractères")
    private String message;

}
