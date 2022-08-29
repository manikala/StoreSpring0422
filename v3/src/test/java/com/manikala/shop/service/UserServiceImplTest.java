package com.manikala.shop.service;

import com.manikala.shop.dao.UserRepository;
import com.manikala.shop.dto.UserDTO;
import com.manikala.shop.obj.User;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.function.Executable;
import org.mockito.Mockito;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.UUID;

public class UserServiceImplTest {

    private UserServiceImpl userService;
    private PasswordEncoder passwordEncoder;
    private UserRepository userRepository;

    @BeforeAll // блок инициализации который будет использоваться перед всеми тестами
    static void beforeAll() {
        System.out.println("Before All tests");
    }

    @BeforeEach // будет выполняться до каждого теста
    void setUp() {
        System.out.println("Before each test");
        passwordEncoder = Mockito.mock(PasswordEncoder.class);
        userRepository = Mockito.mock(UserRepository.class);

        userService = new UserServiceImpl(userRepository, passwordEncoder);
    }

    @AfterEach //после каждого теста
    void afterEach(){
        System.out.println("After each test");
    }

    @AfterAll // после всего
    static void afterAll(){
        System.out.println("After All test");
    }

    @Test
    void checkFindByName() { //проверяем найдет ли пользователя по имени
        //have
        String name = "petr";
        User expectedUser = User.builder().id(1L).name(name).build(); //билдер на юзера

        Mockito.when(userRepository.findFirstByName(Mockito.anyString())).thenReturn(expectedUser); //мокито это заглушка

        //execute
        User actualUser = userService.findByName(name); // находим юзера

        //check
        Assertions.assertNotNull(actualUser); // проверяем что юзер не нул
        Assertions.assertEquals(expectedUser, actualUser); // проверяем что актуальный и ожидаемый юзер правда

    }

    //По сути тоже самое
    @Test
    void checkFindByNameExact() { //проврека по id
        //have
        String name = "petr";
        User expectedUser = User.builder().id(1L).name(name).build();

        Mockito.when(userRepository.findFirstByName(Mockito.eq(name))).thenReturn(expectedUser);

        //execute
        User actualUser = userService.findByName(name); //проверка по имени
        User rndUser = userService.findByName(UUID.randomUUID().toString()); //и по id

        //check
        Assertions.assertNotNull(actualUser);
        Assertions.assertEquals(expectedUser, actualUser);

        Assertions.assertNull(rndUser); //проверяем что по постуму юзеру возращаестя нул а не что то еще

    }
    @Test//тестируем дто
    void checkSaveIncorrectPassword(){
        //have
        UserDTO userDTO = UserDTO.builder()
                .password("password")
                .matchingPassword("another") //даем другой пароль
                .build();

        //execute
        Assertions.assertThrows(RuntimeException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                userService.save(userDTO); // ждем чтобы выбрасилось исключение при разных паролях
            }
        });

    }

    @Test//тестируем дто
    void checkSave(){ // можем ли мы сохранить другого пользователя
        //have
        UserDTO userDTO = UserDTO.builder()
                .username("name")
                .password("password")
                .matchingPassword("password")
                .build();

        Mockito.when(passwordEncoder.encode(Mockito.anyString())).thenReturn("password");//ставим заглушку и делаем условие

        //execute
        boolean result = userService.save(userDTO); //проверяем пытаемся что то сохранить

        //check
        Assertions.assertTrue(result);
        Mockito.verify(passwordEncoder).encode(Mockito.anyString());//макита верефицирует через библиотеку по енкодингу и он будет сравниваться с какойто стракой
        Mockito.verify(userRepository).save(Mockito.any()); // сохраняем заглушетельный оъект в юзеррепозиторий

    }
}
