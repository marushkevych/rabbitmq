package camel.spring;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class JettyToRabbitmqRoute extends RouteBuilder{
    
    @Override
    public void configure() {
        from("jetty:http://localhost:9898").
        to("rabbitmq://localhost/EX1?username=guest&password=guest");    
    }    
}



