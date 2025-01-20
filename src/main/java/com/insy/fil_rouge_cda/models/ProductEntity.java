package com.insy.fil_rouge_cda.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class ProductEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = true)
    private String imageUrl;

    @Size(min = 1, max = 30)
    @NotBlank
    @Column(nullable = false, unique = true, length = 30)
    private String name;

    @NotBlank
    @Size(min = 1, max = 255)
    @Column(nullable = false)
    private String description;

    @NotBlank
    @Size(min = 4, max = 30)
    private String category;

    @NotBlank
    @Size(min = 2, max = 30)
    private String brand;


    @Positive
    private Double price;

    @Positive
    private int quantity;
}
