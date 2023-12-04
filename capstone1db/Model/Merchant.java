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
public class Merchant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotEmpty(message = "Merchant name cannot be empty")
    @Size(min = 4,message = "Merchant name have to be more than 3 length long")
    @Column(columnDefinition = "varchar(20) not null")
    private String name;
}
