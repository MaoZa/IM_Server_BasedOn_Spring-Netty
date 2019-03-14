package cn.dawnland.im.handler;

import cn.dawnland.im.command.PacketCodeC;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author Cap_Sub
 */
public class Spliter extends LengthFieldBasedFrameDecoder {
    private static final int LENGTH_FIELD_OFFSET = 7;
    private static final int LENGTH_FIELD_LENGTH = 4;

    private static Logger logger = LoggerFactory.getLogger(Spliter.class);

    public Spliter() {
        super(Integer.MAX_VALUE, LENGTH_FIELD_OFFSET, LENGTH_FIELD_LENGTH);
    }

    @Override
    protected Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
        // 屏蔽非本协议的客户端
        try{
            if (in.getInt(in.readerIndex()) != PacketCodeC.MAGIC_NUMBER) {
                ctx.channel().close();
                return null;
            }
        }
        catch (Exception e){
            logger.error("Spliter异常[]" + e.getMessage());
        }

        return super.decode(ctx, in);
    }
}
