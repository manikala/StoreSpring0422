import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DocumentationSpring {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                "applicationContext.xml"
        );


        context.close();
    }
}
