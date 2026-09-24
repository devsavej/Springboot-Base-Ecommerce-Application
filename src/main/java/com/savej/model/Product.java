package com.savej.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long productId;
    @NotBlank(message = "product name should be given")
    private String productName;

    @Positive(message = "should be greater 0")
    private double price;

    @NotNull(message = "Quantity is required")
    @PositiveOrZero(message = "Quantity cannot be negative")
    private double quantity;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}
