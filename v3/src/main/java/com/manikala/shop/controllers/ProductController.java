package com.manikala.shop.controllers;

import com.manikala.shop.service.ProductService;
import com.manikala.shop.dto.ProductDTO;
import com.manikala.shop.service.SessionObjectHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;
    private final SessionObjectHolder sessionObjectHolder;

    public ProductController(ProductService productService, SessionObjectHolder sessionObjectHolder) {
        this.productService = productService;
        this.sessionObjectHolder = sessionObjectHolder; //создали сессию
    }



    @GetMapping
    public String list(Model model) {
        sessionObjectHolder.addClick();//добавляем клик к сессии при переходе сюда
        List<ProductDTO> list = productService.getAll();
        model.addAttribute("products", list);
        return "products";
    }

    @GetMapping("/{id}/bucket")
    public String addBucket (@PathVariable Long id, Principal principal) { //@PathVariable Long id извлекаем из url id
        sessionObjectHolder.addClick();
        if (principal == null) {
            return "redirect:/products";
        }
        productService.addToUserBucket(id, principal.getName()); //добавляем к бзеру по имени карзину
        return  "redirect:/products";
    }

    @PostMapping
    public ResponseEntity<Void> addProduct(ProductDTO dto){
        productService.addProduct(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
// События группируются в топики (topic). Топик похож на папку, а события — на файлы в этой папке. У топика может быть ноль, один или много издателей и подписчиков.
    //Сокет — это программная (логическая) конечная точка, устанавливающая двунаправленную коммуникацию между сервером и одной или несколькими клиентскими программами
    @MessageMapping("/products") // мы будем отправлять сообщения на нашу страничку для JS и работы с сокетами
    public void messageAddProduct(ProductDTO dto){
        productService.addProduct(dto);
    }//отправляем общий спсиок продуктов для всех

    @GetMapping("/{id}") //поиск по айди
    @ResponseBody
    public ProductDTO getById(@PathVariable Long id){
        return productService.getById(id);
    }

}
