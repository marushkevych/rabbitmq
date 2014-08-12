package camel.spring.basic;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

/**
 * Use ROUTING_KEY parameter when requesting jetty webservice:
 * 
 * http://localhost:9898?ROUTING_KEY
 *
 */
@Component
public class JettyToRabbitmqRoute extends RouteBuilder{
    
    @Override
    public void configure() {
        from("jetty:http://localhost:9898").
        to("rabbitmq://localhost/EX1?username=guest&password=guest");    
    }    
}



