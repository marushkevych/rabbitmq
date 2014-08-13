package camel.spring.basic;

import java.util.Map;

import org.apache.camel.Exchange;
import org.apache.camel.ExchangePattern;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class RabbitmqToProcessorRoute extends RouteBuilder{
    
    @Override
    public void configure() {
        from("rabbitmq://localhost/EX1?queue=task_queue&durable=true&autoDelete=false&username=guest&password=guest").process(
        from("rabbitmq://localhost/EX1?queue=my_queue&durable=true&autoDelete=false&username=guest&password=guest")
        .setExchangePattern(ExchangePattern.InOut)
        .process(
        		new Processor(){

					@Override
					public void process(Exchange exchange) throws Exception {
						System.out.println(Thread.currentThread().getId() + "Got message:");
						System.out.println(Thread.currentThread().getId() + " Got message:");
						System.out.println(exchange.getIn());
						
						exchange.getOut().setBody("bar!!!");
					}
        			
        		}
        );
    }    
}



