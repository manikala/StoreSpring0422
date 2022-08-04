package com.manikala.shop.controllers;

import com.manikala.shop.dto.UserDTO;
import com.manikala.shop.obj.User;
import com.manikala.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import java.security.Principal;
import java.util.Objects;

@Controller
@RequestMapping ("/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String userList (Model model) {
        model.addAttribute("users", userService.getAll());
        return "userList";
    }

    @GetMapping("/new")
    public String newUser (Model model) {
        model.addAttribute("user", new UserDTO());
        return "user";
    }

    @PostMapping ("/new")
    public  String saveUser (UserDTO dto, Model model) {
        if (userService.save(dto)){ // Проверяем что сохранен юзер
            return "redirect:/users";
        } else {
            model.addAttribute("user", dto);
            return "user";
        }

    }

    @GetMapping("/profile")
    public String profileUser (Model model, Principal principal) { //принципал это авторизованный пользователь с точки зрения секюрити
        if (principal == null) {
            throw new RuntimeException("You are not authorize");
        }
        User user = userService.findByName (principal.getName());

        UserDTO dto = UserDTO.builder()
                .username(user.getName())
                .build();
        model.addAttribute("user", dto);
        return "profile";
    }

    @PostMapping("/profile")
    public String updateProfileUser (UserDTO dto, Model model, Principal principal) {
        if (principal == null || !Objects.equals(principal.getName(), dto.getUsername())) { //Чтобы пользователь не менял свое имя
            throw new RuntimeException("You are not authorize");
        }
        if (dto.getPassword() != null
                && !dto.getPassword().isEmpty()
                && !Objects.equals(dto.getPassword(), dto.getMatchingPassword())) {
            model.addAttribute("user", dto);
            //Should add anything message
            return "profile";
        }

        userService.updateProfile(dto);
        return "redirect:/users/profile";

    }



}
