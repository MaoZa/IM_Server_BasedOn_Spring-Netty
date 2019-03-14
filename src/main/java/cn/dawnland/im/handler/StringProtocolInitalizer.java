package cn.dawnland.im.handler;

/**
 * @author Cap_Sub
 */

import cn.dawnland.im.coder.PacketDecoder;
import cn.dawnland.im.coder.PacketEncoder;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * Just a dummy protocol mainly to show the ServerBootstrap being initialized.
 *
 * @author Abraham Menacherry
 *
 */
@Component
@Qualifier("springProtocolInitializer")
@Data
public class StringProtocolInitalizer extends ChannelInitializer<SocketChannel> {

    @Autowired
    PacketDecoder packetDecoder;

    @Autowired
    PacketEncoder packetEncoder;

    @Autowired
    IMHandler imHandler;

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast(new IMIdleStateHandler());
        pipeline.addLast(new Spliter());
        pipeline.addLast(PacketCodecHandler.INSTANCE);
        pipeline.addLast(LoginRequestHandler.INSTANCE);
        pipeline.addLast(HeartBeatRequestHandler.INSTANCE);
        pipeline.addLast(AuthHandler.INSTANCE);
        pipeline.addLast(IMHandler.INSTANCE);
    }

}
