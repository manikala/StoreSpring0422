package com.manikala.shop.controllers;

import com.manikala.shop.dto.ProductDTO;
import com.manikala.shop.service.ProductService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/products")
public class ProductRestController {

    private final ProductService productService;


    public ProductRestController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public ProductDTO getById(@PathVariable Long id){
        return productService.getById(id);
    }

    @PostMapping
    public void addProduct(@RequestBody ProductDTO dto){
        productService.addProduct(dto);
    }

}
