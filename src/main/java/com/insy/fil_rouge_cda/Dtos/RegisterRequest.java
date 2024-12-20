package com.insy.fil_rouge_cda.Dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
public class RegisterRequest {

    private LocalDate birthday;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String password;
}
