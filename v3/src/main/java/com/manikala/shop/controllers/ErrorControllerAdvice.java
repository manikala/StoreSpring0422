package com.manikala.shop.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice //данный контроллер отрабатывает на все приложение
public class ErrorControllerAdvice {

    @ExceptionHandler(Exception.class) //ловим исключения
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) //ошибка на сервере
    public String exception (Exception exception, Model model){
        String errorMessage = (exception != null ? exception.getMessage() : "Unknown error");
        model.addAttribute("errorMessage", errorMessage); // выводм сообщение об ошибке
        return "error";
    }

}
