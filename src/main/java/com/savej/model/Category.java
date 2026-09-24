package com.savej.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long categoryId;
    private String categoryName;

    @OneToMany(mappedBy = "category")
    @JsonIgnore
    private List<Product> products;
}
