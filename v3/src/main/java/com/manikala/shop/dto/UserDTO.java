package com.manikala.shop.dto;
//DTO (Data Transfer Object) - это объект, предназначенный для транспортировки данных. Поэтому его основная задача — хранить эти данные. Никакой логики он не содержится.
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {
    private String username;
    private String password;
    private String matchingPassword;
}
