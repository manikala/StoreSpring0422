package com.manikala.shop.dto;

import com.manikala.shop.obj.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BucketDetailDTO {
    private Long productId;
    private Integer vendor; //артикул
    private String title;
    private BigDecimal price;
    private BigDecimal amount;
    private String description;
    private Double sum;

    public BucketDetailDTO(Product product) {
        this.productId = product.getId();
        this.vendor = product.getVendor();
        this.title = product.getTitle();
        this.price = product.getPrice();
        this.amount = new BigDecimal(1.0);
        this.description = product.getDescription();
        //this.sum = product.getPrice().toString();
        this.sum = Double.valueOf(product.getPrice().toString());
    }
}
