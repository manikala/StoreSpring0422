import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootApplication
public class DocumentationSpring {
    public static void main(String[] args) {
        //ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
        //        "applicationContext.xml"
        //);
        // создаем пустой спринговый контекст, который будет искать свои бины по аннотациям в указанном пакете
        //ApplicationContext context =
        //       new AnnotationConfigApplicationContext("ru.javarush.info.fatfaggy.animals.entities");

            SpringApplication.run(DocumentationSpring.class, args);


        //context.close();
    }
}
