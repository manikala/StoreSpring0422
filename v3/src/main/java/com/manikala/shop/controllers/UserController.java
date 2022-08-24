package com.manikala.shop.controllers;

import com.manikala.shop.dto.UserDTO;
import com.manikala.shop.obj.User;
import com.manikala.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


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

    @PreAuthorize("hasAuthority('ADMIN')") //доступ сюда только админу
    @GetMapping("/new")
    public String newUser (Model model) {
        System.out.println("called method newUser");
        model.addAttribute("user", new UserDTO());
        return "user";
    }
    //всем авторизованным пользователем у которых имя равно текущему пользователю
    @PostAuthorize("isAuthenticated() and #username == authentication.principal.username") //# - spel выражение. берем тикущий юзернейм и принципал
    @GetMapping("/{name}/roles") //{} тоже spel выражение // просто обращение к полям
    @ResponseBody
    public String getRoles(@PathVariable("name") String username) {
        System.out.println("called method getRoles");
        User byName = userService.findByName(username);
        return byName.getRole().name();
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
    @PreAuthorize("isAuthenticated()")
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
