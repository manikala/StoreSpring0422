package com.manikala.shop.controllers;

import com.manikala.shop.dto.ProductDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) //тестирование не от junit а от spring //и указываем что будем использовать рандомный порт
class ProductControllerIT { //интеграционнный тест

    @Autowired
    private TestRestTemplate restTemplate; //для тестирования REST

    @Test
    void checkGetProductById() {
        ResponseEntity<ProductDTO> entity = restTemplate.getForEntity("/products/" + 1, ProductDTO.class);//какое содержание ответа должно быть //корректно ли возратится продукт
        Assertions.assertEquals(HttpStatus.OK, entity.getStatusCode()); //сравниваем хороший статус и нет
    }
}
