package com.insy.fil_rouge_cda.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class UserEntity {

    @Id
    @GeneratedValue
    private Long id;

    @NotBlank
    @Size(min = 3, max = 50)
    @Column(nullable = false, length = 50)
    private String name;

    @Email
    @NotBlank
    @Size(min = 10, max = 50)
    @Column(nullable = false, unique = true, length = 50)
    private String email;

    @NotBlank
    @Column(nullable = false)
    private String password;
}
