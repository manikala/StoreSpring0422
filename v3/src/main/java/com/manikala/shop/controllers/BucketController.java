package com.manikala.shop.controllers;

import com.manikala.shop.dto.BucketDTO;
import com.manikala.shop.service.BucketService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
public class BucketController {
    private final BucketService bucketService;

    public BucketController(BucketService bucketService) {
        this.bucketService = bucketService;
    }

    @GetMapping("/bucket")
    public String aboutBucket (Model model, Principal principal) {
        if (principal == null) {
            model.addAttribute("bucket", new BucketDTO());
        }
        else {
            BucketDTO bucketDTO = bucketService.getBucketByUser (principal.getName()); //бакет сервис ищет карзину по юзеру
            model.addAttribute("bucket", bucketDTO);
        }
        return "bucket";
    }

    @PostMapping("/bucket")
    public String commitBucket (Principal principal) {//при переходи на бакет передаем юзера
        if (principal != null) {
            bucketService.commitBucketToOrder(principal.getName());
        }
        return "redirect:/bucket";
    }



}
