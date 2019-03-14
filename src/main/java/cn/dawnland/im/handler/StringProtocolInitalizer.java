package cn.dawnland.im.handler;

/**
 * @author Cap_Sub
 */

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Just a dummy protocol mainly to show the ServerBootstrap being initialized.
 *
 * @author Abraham Menacherry
 *
 */
@Component
@Data
public class StringProtocolInitalizer extends ChannelInitializer<SocketChannel> {

    @Autowired PacketCodecHandler packetCodecHandler;
    @Autowired HeartBeatRequestHandler heartBeatRequestHandler;
    @Autowired LoginRequestHandler loginRequestHandler;
    @Autowired AuthHandler authHandler;
    @Autowired IMHandler imHandler;

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast(new IMIdleStateHandler());
        pipeline.addLast(new Spliter());
        pipeline.addLast(packetCodecHandler);
        pipeline.addLast(loginRequestHandler);
        pipeline.addLast(heartBeatRequestHandler);
        pipeline.addLast(authHandler);
        pipeline.addLast(imHandler);
    }

}
