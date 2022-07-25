package com.manikala.shop.mapper;


import com.manikala.shop.dto.ProductDTO;
import com.manikala.shop.obj.Product;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ProductMapper {
    ProductMapper MAPPER = Mappers.getMapper(ProductMapper.class);

    Product toProduct (ProductDTO dto);

    @InheritInverseConfiguration
    ProductDTO fromProduct (Product product);

    List<Product> toProductList (List<ProductDTO> productDTOS);
    List<ProductDTO> fromProductList (List<Product> products);

}
