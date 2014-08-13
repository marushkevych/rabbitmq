package camel.spring.basic;

import org.apache.camel.ExchangePattern;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

/**
 * Use ROUTING_KEY parameter when requesting jetty webservice:
 * 
 * http://localhost:9898?ROUTING_KEY
 *
 */
@Component
//@Component
public class JettyToRabbitmqRoute extends RouteBuilder{
    
    @Override
    public void configure() {
        from("jetty:http://localhost:9898").
        to("rabbitmq://localhost/EX1?username=guest&password=guest");    
//        from("jetty:http://localhost:9898").
//        inOut("rabbitmq://localhost/EX1?username=guest&password=guest&exchangePattern=InOut");    
    	
    	from("direct:testSetToInOutThenTo")
        .setExchangePattern(ExchangePattern.InOut)
        .to("rabbitmq://localhost/EX1?username=guest&password=guest");
    }    
}



