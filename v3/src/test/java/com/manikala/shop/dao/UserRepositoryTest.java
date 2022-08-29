package com.manikala.shop.dao;

import com.manikala.shop.obj.Role;
import com.manikala.shop.obj.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@SqlGroup({@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:initUsers.sql")}) //загружаем sql скрипт и используем его перел методами
class UserRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    @Test
    void checkFindByName() {
        //have
        User user = new User();
        user.setName("TestUser");
        user.setPassword("password");

// сохраняем нашего юзера
        entityManager.persist(user);

        //execute
        User actualUser = userRepository.findFirstByName("TestUser"); //находим юзера

        //check
        Assertions.assertNotNull(actualUser);//и проверяем юзера
        Assertions.assertEquals(user.getName(), actualUser.getName());
        Assertions.assertEquals(user.getPassword(), actualUser.getPassword());

    }

    @Test
    void checkFindByNameAfterSql() {
        //execute
        User actualUser = userRepository.findFirstByName("man"); //проверяем пользователя из нашего скрипта 

        //check
        Assertions.assertNotNull(actualUser);
        Assertions.assertEquals(3, actualUser.getId());
        Assertions.assertEquals("man", actualUser.getName());
        Assertions.assertEquals("val", actualUser.getLastname());
        Assertions.assertEquals("man", actualUser.getFirstname());
        Assertions.assertEquals("man", actualUser.getPassword());
        Assertions.assertEquals(Role.ADMIN, actualUser.getRole());

    }
}
