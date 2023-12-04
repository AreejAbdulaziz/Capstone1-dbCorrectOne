package com.example.capstone1db.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Size(min = 4,message = "Category name have to be more than 3 length long")
    @NotEmpty(message = "Category name cannot be empty")
    @Column(columnDefinition = "varchar(20) not null")
    private String name;
}
