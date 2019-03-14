package cn.dawnland.im.handler;

import cn.dawnland.im.command.Command;
import cn.dawnland.im.command.Packet;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Cap_Sub
 */
@Component
@Qualifier("serverHandler")
@ChannelHandler.Sharable
public class IMHandler extends SimpleChannelInboundHandler<Packet> {

    @Autowired private MessageRequestHandler messageRequestHandler;
    @Autowired private CreateGroupRequestHandler createGroupRequestHandler;
    @Autowired private JoinGroupRequestHandler joinGroupRequestHandler;
    @Autowired private QuitGroupRequestHandler quitGroupRequestHandler;
    @Autowired private ListGroupMembersRequestHandler membersRequestHandler;
    @Autowired private GroupMessageRequestHandler groupMessageRequestHandler;

    private Map<Byte, SimpleChannelInboundHandler<? extends Packet>> handlerMap;

    public void init() {
        handlerMap = new HashMap<>();
        handlerMap.put(Command.MESSAGE_REQUEST, messageRequestHandler);
        handlerMap.put(Command.CREATEGROUP_REQUEST, createGroupRequestHandler);
        handlerMap.put(Command.JOINGROUP_REQUEST, joinGroupRequestHandler);
        handlerMap.put(Command.QUITGROUP_REQUEST, quitGroupRequestHandler);
        handlerMap.put(Command.LISTGROUP_REQUEST, membersRequestHandler);
        handlerMap.put(Command.GROUPMESSAGE_REQUEST, groupMessageRequestHandler);
    }

    @Override
    protected void messageReceived(ChannelHandlerContext channelHandlerContext, Packet packet) throws Exception {
        if(handlerMap == null){ init(); }
        SimpleChannelInboundHandler<? extends Packet> simpleChannelInboundHandler = handlerMap.get(packet.getCommand());
        simpleChannelInboundHandler.channelRead(channelHandlerContext, packet);
    }
}
