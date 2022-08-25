package com.manikala.shop.controllers;

import com.manikala.shop.dto.ProductDTO;
import com.manikala.shop.service.ProductService;
import com.manikala.shop.service.UserService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.setup.StandaloneMockMvcBuilder;

import java.math.BigDecimal;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ProductRestController.class)
//@AutoConfigureMockMvc //входит в вебмвстест так что можно удалить
//@SpringBootTest //тоже вроде не обязательно
class ProductRestControllerTest {

    @Autowired
    private MockMvc mockMvc;


    @MockBean
    private ProductService productService;
    @MockBean
    private UserService userService;

    private ProductDTO dto = new ProductDTO(99L, 999, "Test Product", new BigDecimal(999),  new BigDecimal(99),"Test Product");

    @BeforeEach
    void setUp() {
        given(productService.getById(dto.getId())).willReturn(dto);
    }

    @Test
    void getById() throws Exception {
        mockMvc.perform(
                        MockMvcRequestBuilders.get("/api/v1/products/{id}", dto.getId())
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(99)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.vendor", Matchers.is(999)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title", Matchers.is("Test Product")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price", Matchers.is(999)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.amount", Matchers.is(999)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description", Matchers.is("Test Product")));


    }
}

