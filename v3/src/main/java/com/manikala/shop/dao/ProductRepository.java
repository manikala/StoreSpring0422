package com.manikala.shop.dao;

import com.manikala.shop.dto.ProductDTO;
import com.manikala.shop.obj.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
