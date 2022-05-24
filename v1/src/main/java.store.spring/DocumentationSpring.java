import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DocumentationSpring {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                "applicationContext.xml"
        );
        // создаем пустой спринговый контекст, который будет искать свои бины по аннотациям в указанном пакете
        //ApplicationContext context =
        //       new AnnotationConfigApplicationContext("ru.javarush.info.fatfaggy.animals.entities");


        context.close();
    }
}
