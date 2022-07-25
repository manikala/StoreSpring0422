package com.manikala.shop.service;

import com.manikala.shop.dao.ProductRepository;
import com.manikala.shop.dao.ProductService;
import com.manikala.shop.dto.ProductDTO;
import com.manikala.shop.mapper.ProductMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductMapper mapper = ProductMapper.MAPPER;
    private final ProductRepository productRepository;

    public ProductServiceImpl (ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<ProductDTO> getAll() {
        return mapper.fromProductList(productRepository.findAll());
    }
}
