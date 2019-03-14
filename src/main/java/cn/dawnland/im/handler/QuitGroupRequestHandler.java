package cn.dawnland.im.handler;

import cn.dawnland.im.command.packet.QuitGroupRequestPacket;
import cn.dawnland.im.command.packet.QuitGroupResponsePacket;
import cn.dawnland.im.utils.SessionUtil;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;

/**
 * @author Cap_Sub
 */
@ChannelHandler.Sharable
public class QuitGroupRequestHandler extends SimpleChannelInboundHandler<QuitGroupRequestPacket> {

    public static final QuitGroupRequestHandler INSTANCE = new QuitGroupRequestHandler();

    protected QuitGroupRequestHandler() { }

    @Override
    protected void messageReceived(ChannelHandlerContext channelHandlerContext, QuitGroupRequestPacket quitGroupRequestPacket) throws Exception {
        // 1. 获取群对应的 channelGroup，然后将当前用户的 channel 移除
        String groupId = quitGroupRequestPacket.getGroupId();
        ChannelGroup channelGroup = SessionUtil.getChannelGroup(groupId);
        channelGroup.remove(channelHandlerContext.channel());

        // 2. 构造退群响应发送给客户端
        QuitGroupResponsePacket responsePacket = new QuitGroupResponsePacket();

        responsePacket.setGroupId(quitGroupRequestPacket.getGroupId());
        responsePacket.setSuccess(true);
        channelHandlerContext.channel().writeAndFlush(responsePacket);
    }
}
