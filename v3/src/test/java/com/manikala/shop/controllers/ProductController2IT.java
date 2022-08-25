package com.manikala.shop.controllers;


import com.manikala.shop.dto.ProductDTO;
import com.manikala.shop.service.ProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductController2IT {

    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean //заглушка продукт сервиса через эту анатацию
    private ProductService productService;

    private ProductDTO expectedProduct = new ProductDTO(99L, 999, "Test Product", new BigDecimal(999),  new BigDecimal(99),"Test Product");

    @BeforeEach
    void setUp() {
        given(productService.getById(expectedProduct.getId()))
                .willReturn(expectedProduct);
    }

    @Test
    void getById() {
        //execute
        ResponseEntity<ProductDTO> entity =
                restTemplate
                        .getForEntity("/products/" + expectedProduct.getId(), ProductDTO.class); //передаем id и проверяем корректный ли статус
        //check
        Assertions.assertEquals(HttpStatus.OK, entity.getStatusCode());

        ProductDTO actualProduct = entity.getBody(); //получаем дто
        Assertions.assertEquals(expectedProduct, actualProduct); // и сравниваем корректно ли все получается
    }
    @Test
    void addProduct() {
        //execute
        ResponseEntity<Void> response =
                restTemplate.postForEntity("/api/v1/products/", expectedProduct, Void.class);//делаем метод пост на api и смотрим что возращает
        //check
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());

        verify(productService).addProduct(Mockito.eq(expectedProduct));//ставим заглушку на получаемый результат, тк мы добавляемый в несуществующий сервис продукт

        ArgumentCaptor<ProductDTO> captor = ArgumentCaptor.forClass(ProductDTO.class);
        verify(productService).addProduct(captor.capture());
// смотрим на аргуемнты дто чтобы они были равны
        Assertions.assertEquals(expectedProduct, captor.getValue());
    }
}
