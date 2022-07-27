package com.manikala.shop.service;

import com.manikala.shop.dto.ProductDTO;

import java.util.List;

public interface ProductService {
    List<ProductDTO> getAll();
    void addToUserBucket (Long productId, String username);
}