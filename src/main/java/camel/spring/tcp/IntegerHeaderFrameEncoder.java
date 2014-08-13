package camel.spring.tcp;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandler.Sharable;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.oneone.OneToOneEncoder;

@Sharable
public class IntegerHeaderFrameEncoder extends OneToOneEncoder {

    @Override
    protected Object encode(ChannelHandlerContext ctx, Channel channel,
            Object msg) throws Exception {

        if (!(msg instanceof String)) {
            return msg;
        }

        
        return encode((String) msg);
    }

    // encode message in byte array with length prefix
    private ChannelBuffer encode(String message){
        byte[] payload = message.getBytes(Charset.forName("UTF-8"));
        
        //encode length of the response
        ByteBuffer byteBuffer = ByteBuffer.allocate(4);
        byteBuffer.putInt(payload.length);
        byte[] lengthPrefix = byteBuffer.array();
        
        return ChannelBuffers.wrappedBuffer(lengthPrefix,payload);             
    }    
}
