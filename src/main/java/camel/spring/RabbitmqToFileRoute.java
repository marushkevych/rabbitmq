package camel.spring;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class RabbitmqToFileRoute extends RouteBuilder{
    
    @Override
    public void configure() {
        from("rabbitmq://localhost/EX1?queue=task_queue&durable=true&autoDelete=false&username=guest&password=guest").to("file:data/outbox");
    }    
}



