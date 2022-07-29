package com.manikala.shop.dto;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductDTO {
    private Long id;
    private int vendor; //артикул
    private String name;
    private double price;
    private String description;
}
