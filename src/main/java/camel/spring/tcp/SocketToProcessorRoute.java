package camel.spring.tcp;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.jboss.netty.buffer.ChannelBuffer;
import org.springframework.stereotype.Component;

@Component
public class SocketToProcessorRoute extends RouteBuilder{
    
    @Override
    public void configure() {
        from("netty:tcp://192.168.6.51:1338?decoder=#FrameDecoder&encoder=#FrameEncoder").process(
        		new Processor(){

					@Override
					public void process(Exchange exchange) throws Exception {
						System.out.print("Thread " + Thread.currentThread().getId() + " Got message: ");
						
						ChannelBuffer buffer = (ChannelBuffer) exchange.getIn().getBody();
						System.out.println( new String(buffer.array()));
						
						byte lastByte = buffer.getByte(buffer.readableBytes() - 1 );
						if(lastByte == 0)throw new RuntimeException();
						

						// response
						String responce = "<GetLoyaltyOnlineStatusResponse>"
    +"<ResponseHeader>"
    +"<POSLoyaltyInterfaceVersion>1.0</POSLoyaltyInterfaceVersion>"
        +"<VendorName>Bulloch</VendorName>"
        +"<VendorModelVersion>BULPOS</VendorModelVersion>"
        +"<POSSequenceID>BULOSV10</POSSequenceID>"
        +"<LoyaltySequenceID>12345</LoyaltySequenceID>"
    +"</ResponseHeader>"
    +"<PromptForLoyaltyFlag value=\"true\"/>"
+"</GetLoyaltyOnlineStatusResponse>";
						
						exchange.getOut().setBody(responce);
					}
        			
        		}
        );
    }    
}



