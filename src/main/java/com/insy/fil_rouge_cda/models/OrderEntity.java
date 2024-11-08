package com.insy.fil_rouge_cda.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class OrderEntity {

    @Id
    @GeneratedValue
    private Long id;

    @NotBlank
    @PastOrPresent
    private LocalDate date;

    @NotBlank
    private Boolean status;

    @NotBlank
    private String payment_method;
}
