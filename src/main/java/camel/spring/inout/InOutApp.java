package camel.spring.inout;

import org.apache.camel.ProducerTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class InOutApp {
    public static void main(String[] args) {
        ApplicationContext context = 
            new ClassPathXmlApplicationContext("camel/spring/inout/camel-context.xml");
        
        ProducerTemplate template = (ProducerTemplate) context.getBean("producerTemplate");
        String responce = (String) template.requestBodyAndHeader("rabbitmq://localhost/EX1?username=guest&password=guest", "Request Body","ROUTING_KEY","input");
        System.out.println("Got responce: " + responce);
    }
}
