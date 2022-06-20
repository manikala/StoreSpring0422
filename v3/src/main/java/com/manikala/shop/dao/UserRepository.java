package com.manikala.shop.dao;
//DAO (Data Access Object) — это объект, основная задача которого сохранять данные в базу данные, а также извлекать их из неё

import com.manikala.shop.obj.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findFirstByName(String name);
}
