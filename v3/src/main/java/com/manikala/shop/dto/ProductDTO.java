package com.manikala.shop.dto;


import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class ProductDTO {
    private Long id;
    private Integer vendor; //артикул
    private String title;
    private BigDecimal price;
    private BigDecimal amount;
    private String description;
}
