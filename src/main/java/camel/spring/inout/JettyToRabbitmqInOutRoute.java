package camel.spring.inout;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class JettyToRabbitmqInOutRoute extends RouteBuilder{
    
    @Override
    public void configure() {
        from("jetty:http://localhost:9898").
        inOut("rabbitmq://localhost/EX1?username=guest&password=guest");    
    }    
}



