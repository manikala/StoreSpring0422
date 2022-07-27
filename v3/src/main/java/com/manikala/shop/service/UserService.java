package com.manikala.shop.service;

import com.manikala.shop.dto.UserDTO;
import com.manikala.shop.obj.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService { //security
    boolean save(UserDTO userDTO);
    void save(User user);
    List<UserDTO> getAll();

    User findByName (String name);
    void updateProfile(UserDTO userDTO);
}
