package com.manikala.shop.controllers;

import com.manikala.shop.service.SessionObjectHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.HttpSessionRequiredException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.model.IModel;

import javax.servlet.http.HttpSession;
import java.util.UUID;

@Controller
public class MainController {

    private final SessionObjectHolder sessionObjectHolder; //создаем нашу сессию (объект в рамках сессии

    public MainController(SessionObjectHolder sessionObjectHolder) {
        this.sessionObjectHolder = sessionObjectHolder;
    }

    @RequestMapping({"", "/"})
    public String index (Model model, HttpSession httpSession /*для вывода id сессии*/) {
        model.addAttribute ("amountClicks", sessionObjectHolder.getAmountClicks()); //добавляем клики на главный экран
        if (httpSession.getAttribute("myID") == null) {
            String uuid = UUID.randomUUID().toString(); // генерируем айди сессии
            httpSession.setAttribute("myID", uuid); //добавляем инфу к http сессии чтобы потом было легко взять //uuid - url user id
            System.out.println("Generated UUID -> " + uuid);
        }
        model.addAttribute("uuid", httpSession.getAttribute("myID")); //из http сессии взяли этот id
        return "index";
    }

    @RequestMapping("/login")
    public String login () {
        return "login";
    }

    @RequestMapping("/login-error") // Чтобы пользователь попал на 484-page
    public String loginError (Model model) {
        model.addAttribute("loginError", true);
        return "login";
    }



}
