package com.example.capstone1db.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@Entity
@RequiredArgsConstructor
public class MerchantStock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull(message = "product id cannot be null")
    @Column(columnDefinition = "int not null")
    private Integer productId;
    @NotNull(message = "merchant id cannot be null")
    @Column(columnDefinition = "int not null")
    private Integer merchantId;
    @NotNull(message = "stock cannot be empty")
    @Min(value = 10,message = "stock have to be more than 10 at start")
    @Column(columnDefinition = "int not null")
    private int stock;
}
