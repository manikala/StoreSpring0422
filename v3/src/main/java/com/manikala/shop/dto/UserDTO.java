package com.manikala.shop.dto;
//DTO (Data Transfer Object) - это объект, предназначенный для транспортировки данных. Поэтому его основная задача — хранить эти данные. Никакой логики он не содержится.
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data //генерирует конструктор, геттеры, сеттеры, методы equals, hashCode, toString.
@NoArgsConstructor
@AllArgsConstructor
@Builder //генерирует методы, которыми мы инициализируем объект по цепочке. Это удобно когда мы не хотим использовать конструктор со всеми параметрами
public class UserDTO {
    private String username;
    private String password;
    private String matchingPassword; //подтверждение пароля
}
