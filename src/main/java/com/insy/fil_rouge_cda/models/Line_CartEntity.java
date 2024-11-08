package com.insy.fil_rouge_cda.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Positive;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Line_CartEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Positive
    private int quantity;
}
