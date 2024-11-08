package com.insy.fil_rouge_cda.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Line_OrderEntity {

    @Id
    @GeneratedValue
    private Long id;

    @NotBlank
    @Positive
    private int price;

    @NotBlank
    @Positive
    private int quantity;
}
