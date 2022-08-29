package com.manikala.shop.config;


import com.manikala.shop.obj.Role;
import com.manikala.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.persistence.Basic;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true, jsr250Enabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter { // implements SecurityFilterChain  extends WebSecurityConfigurerAdapter

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider()); //аутификация
    }
    @Basic // для корректного обращения к нашим данным и сущнастям
    private AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userService);
        auth.setPasswordEncoder(passwordEncoder());
        return auth;

    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception { // основные настройки
        http.authorizeHttpRequests()
                .antMatchers("/ws").permitAll()
                .antMatchers("/users").hasAnyAuthority(Role.ADMIN.name()) //Кто может просматривать users
                //.antMatchers("/users/new").hasAuthority(Role.ADMIN.name())//создание юзеров (Здесь убрал и добавил @PreAuthorize в контроллере) тот же функционал
                .anyRequest().permitAll()//все остальное доступно для всех юзеров
                .and()
                    .formLogin()
                    .loginPage("/login")
                    .failureUrl("/login-error")
                    .loginProcessingUrl("/auth")//аунтификация
                    .permitAll()// все это расрешено для всех пользователей
                .and()
                    .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))//разъедениение logout
                    .logoutSuccessUrl("/").deleteCookies("JSESSIONID")//удаление куков
                    .invalidateHttpSession(true)//проверка валидации сессии
                .and()
                    .csrf().disable();//подмена


    }
}
