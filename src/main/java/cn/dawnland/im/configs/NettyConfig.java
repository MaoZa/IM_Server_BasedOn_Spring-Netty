package cn.dawnland.im.configs;

import cn.dawnland.im.coder.PacketDecoder;
import cn.dawnland.im.coder.PacketEncoder;
import cn.dawnland.im.handler.StringProtocolInitalizer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Cap_Sub
 */
@Configuration
public class NettyConfig {
    @Value("${boss.thread.count}")
    private int bossCount;

    @Value("${worker.thread.count}")
    private int workerCount;

    @Value("${tcp.port}")
    private int tcpPort;

    @Value("${so.keepalive}")
    private boolean keepAlive;

    @Value("${so.backlog}")
    private int backlog;

    @Autowired private StringProtocolInitalizer stringProtocolInitalizer;
    @Autowired PacketDecoder packetDecoder;
    @Autowired PacketEncoder packetEncoder;

    @SuppressWarnings("unchecked")
    @Bean(name = "serverBootstrap")
    public ServerBootstrap bootstrap() {
        ServerBootstrap serverBootstrap = new ServerBootstrap();

        /**
         * 用于监听新链接的线程组
         */
        NioEventLoopGroup boos = new NioEventLoopGroup();

        /**
         * 用于处理每一条链接读写的线程组
         */
        NioEventLoopGroup worker = new NioEventLoopGroup();
        serverBootstrap
                /**
                 * 配置线程模型
                 */
                .group(boos, worker)
                /**
                 * 配置io模型为NIO
                 */
                .channel(NioServerSocketChannel.class)
                /**
                 * 这里主要就是定义后续每条连接的数据读写，业务处理逻辑
                 */
                .childHandler(stringProtocolInitalizer)
                /**
                 * 设置开启TCP心跳
                 */
                .childOption(ChannelOption.SO_KEEPALIVE, true);

        /**
         * 绑定方法
         */
        bind(serverBootstrap, 8000);

        /**
         * 初始化方法,用于服务启动时逻辑
         */
        serverBootstrap.handler(new ChannelInitializer<NioServerSocketChannel>() {
            @Override
            protected void initChannel(NioServerSocketChannel ch) {
                System.out.println("服务端启动中");
            }
        });
        return serverBootstrap;
    }

    @Bean(name = "bossGroup", destroyMethod = "shutdownGracefully")
    public NioEventLoopGroup bossGroup() {
        return new NioEventLoopGroup(bossCount);
    }

    @Bean(name = "workerGroup", destroyMethod = "shutdownGracefully")
    public NioEventLoopGroup workerGroup() {
        return new NioEventLoopGroup(workerCount);
    }
    @Bean(name = "tcpSocketAddress")
    public InetSocketAddress tcpPort() {
        return new InetSocketAddress(tcpPort);
    }

    @Bean(name = "tcpChannelOptions")
    public Map<ChannelOption<?>, Object> tcpChannelOptions() {
        Map<ChannelOption<?>, Object> options = new HashMap<ChannelOption<?>, Object>();
        options.put(ChannelOption.SO_KEEPALIVE, keepAlive);
        options.put(ChannelOption.SO_BACKLOG, backlog);
        return options;
    }

    public PacketEncoder packetEncoder() {
        return packetEncoder;
    }

    public PacketDecoder packetDecoder() {
        return packetDecoder;
    }

    /**
     * Necessary to make the Value annotations work.
     *
     * @return
     */
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    private void bind(final ServerBootstrap serverBootstrap, final int port){
        serverBootstrap.bind(8000).addListener(new GenericFutureListener<Future<? super Void>>() {
            @Override
            public void operationComplete(Future<? super Void> future) throws Exception {
                if (future.isSuccess()) {
                    System.out.println("绑定" + port + "端口成功!");
                } else {
                    System.out.println("绑定" + port + "端口失败!");
                    if(port + 1 <= 65535){
                        bind(serverBootstrap, port + 1);
                    }
                }
            }
        });
    }

}
