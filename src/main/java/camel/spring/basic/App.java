package camel.spring;

import org.apache.camel.Exchange;
import org.apache.camel.ExchangePattern;
import org.apache.camel.ProducerTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.apache.camel.Processor;



public class App {
    public static void main(String[] args) {
        ApplicationContext context = 
            new ClassPathXmlApplicationContext("camel-context.xml");
        
        ProducerTemplate producer = (ProducerTemplate) context.getBean("ProducerTemplate");
//        Object reply = producer.requestBody("rabbitmq://localhost/EX1?username=guest&password=guest&exchangePattern=InOut","foo");
//        Object reply = producer.requestBody("direct:testSetToInOutThenTo","foo");
        
        Exchange exchange = producer.send("rabbitmq://localhost/EX1?username=guest&password=guest", ExchangePattern.InOut, new Processor() {
        	@Override
            public void process(Exchange exchange) throws Exception {
                exchange.getIn().setBody("This is my in/out message text.");
            }
        });
        
        System.out.println("got reaponce: " + exchange.getOut());
    }
}
