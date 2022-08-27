package com.manikala.shop.service;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

@Component
@Scope (value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS) //бин будет работать в сессии
public class SessionObjectHolder {
    //просто поработал с сесией
    private long amountClicks = 0;
    public  SessionObjectHolder() {
        System.out.println("Session object created.");
    }

    public long getAmountClicks () {
        return amountClicks;
    }

    public void addClick() {
        amountClicks++;
    }

}
