package com.manikala.shop.service;

import com.manikala.shop.ws.greeting.Greeting;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.XMLGregorianCalendar;
import java.time.LocalDate;

public class GreetingServiceTest {

    @Test
    void generateGreeting() throws DatatypeConfigurationException {
        //have - то что мы имеем и иницеализируем
        GreetingService service = new GreetingService(); //создаем экземпляр для теста

        String name = "Petr";
        LocalDate expectedDate = LocalDate.now();  //саоздаем локальную дату

        //execute - выполняем
        Greeting greeting = service.generateGreeting(name); // выполняем приветствие по данному имени
        //check - проверяем
        Assertions.assertNotNull(greeting); // проверка что не равно нул
        Assertions.assertEquals("Hello, " + name, greeting.getText()); // проверяем что равно данной строчке

        XMLGregorianCalendar date = greeting.getDate(); // проверка даты
        Assertions.assertEquals(expectedDate.getYear(), date.getYear());
        Assertions.assertEquals(expectedDate.getMonthValue(), date.getMonth());
        Assertions.assertEquals(expectedDate.getDayOfMonth(), date.getDay());
    }
}
