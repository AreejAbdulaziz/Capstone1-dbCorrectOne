package com.example.capstone1db.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotEmpty(message = "Product name cannot be empty")
    @Size(min = 4,message = "Product name have to be more than 3 length long")
    @Column(columnDefinition = "varchar(20) not null")
    private String name;
    @NotNull(message = "Product price cannot be null")
    @Positive(message = "enter correct price!")
    @Column(columnDefinition = "int not null")
    private Integer price; //check
    @NotNull(message = "category id cannot be null")
    @Column(columnDefinition = "int not null")
    private Integer categoryID;
}
