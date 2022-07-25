package com.manikala.shop.dao;

import com.manikala.shop.dto.ProductDTO;

import java.util.List;

public interface ProductService {
    List<ProductDTO> getAll();
}