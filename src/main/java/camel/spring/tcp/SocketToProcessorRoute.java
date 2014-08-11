package camel.spring.tcp;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.handler.codec.frame.CorruptedFrameException;
import org.springframework.stereotype.Component;

@Component
public class SocketToProcessorRoute extends RouteBuilder{
    
    @Override
    public void configure() {
        from("netty:tcp://localhost:9999?decoder=#FrameDecoder&encoder=#FrameEncoder").process(
        		new Processor(){

					@Override
					public void process(Exchange exchange) throws Exception {
						System.out.print("Thread " + Thread.currentThread().getId() + " Got message: ");
						
						ChannelBuffer buffer = (ChannelBuffer) exchange.getIn().getBody();
						System.out.println( new String(buffer.array()));
						
						byte lastByte = buffer.getByte(buffer.readableBytes() - 1 );
						if(lastByte == 0)throw new RuntimeException();
						

						// response
						exchange.getOut().setBody( "Hey!");
					}
        			
        		}
        );
    }    
}



