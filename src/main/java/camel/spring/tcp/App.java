package camel.spring.tcp;

import org.apache.camel.ProducerTemplate;
import org.jboss.netty.buffer.ChannelBuffer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("camel/spring/tcp/camel-context.xml");

        ProducerTemplate producerTemplate = (ProducerTemplate) context.getBean("producerTemplate");
        
        String URL = "netty:tcp://localhost:1338?decoder=#FrameDecoder&encoder=#FrameEncoder";
        
        ChannelBuffer buffer = (ChannelBuffer)producerTemplate.requestBody(URL, "Hello server");
        System.out.println("Got responce: " + new String(buffer.array()));
    }
    
    
}
