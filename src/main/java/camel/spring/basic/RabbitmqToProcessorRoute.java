package camel.spring.basic;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class RabbitmqToProcessorRoute extends RouteBuilder{
    
    @Override
    public void configure() {
        from("rabbitmq://localhost/EX1?queue=task_queue&durable=true&autoDelete=false&username=guest&password=guest").process(
        		new Processor(){

					@Override
					public void process(Exchange exchange) throws Exception {
						System.out.println(Thread.currentThread().getId() + "Got message:");
						System.out.println(exchange.getIn());
						
					}
        			
        		}
        );
    }    
}



