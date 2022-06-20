package com.manikala.shop.service;

import com.manikala.shop.dto.UserDTO;
import org.springframework.security.core.userdetails.UserDetailsService;
//security
public interface UserService extends UserDetailsService {
    boolean save(UserDTO userDTO);
}
