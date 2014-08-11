package camel.spring.inout;

import org.apache.camel.Exchange;
import org.apache.camel.ExchangePattern;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class RabbitmqToProcessorInOutRoute extends RouteBuilder{
    
    @Override
    public void configure() {
        
        from("rabbitmq://localhost/EX1?queue=input&routingKey=input&durable=true&autoDelete=false&username=guest&password=guest")
        .inOut("rabbitmq://localhost/EX1?queue=process&durable=true&autoDelete=false&username=guest&password=guest");
        
        from("rabbitmq://localhost/EX1?queue=process&durable=true&autoDelete=false&username=guest&password=guest")
        .process(
        		new Processor(){

					@Override
					public void process(Exchange exchange) throws Exception {
						System.out.println(Thread.currentThread().getId() + "Got message:");
						System.out.println(exchange.getIn());
						exchange.getOut().setBody("responding to " + exchange.getIn());
						
					}
        			
        		}
        );
    }    
}



