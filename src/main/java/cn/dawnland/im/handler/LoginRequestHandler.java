package cn.dawnland.im.handler;

import cn.dawnland.im.command.PacketCodeC;
import cn.dawnland.im.command.packet.LoginRequestPacket;
import cn.dawnland.im.command.packet.LoginResponsePacket;
import cn.dawnland.im.model.Session;
import cn.dawnland.im.utils.IDUtil;
import cn.dawnland.im.utils.LoginUtil;
import cn.dawnland.im.utils.SessionUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author Cap_Sub
 */
@ChannelHandler.Sharable
public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginRequestPacket> {

    public static final LoginRequestHandler INSTANCE = new LoginRequestHandler();

    protected LoginRequestHandler() { }

    @Override
    protected void messageReceived(ChannelHandlerContext channelHandlerContext, LoginRequestPacket loginRequestPacket) throws Exception {
        /**
         * 登录校验
         */
        LoginResponsePacket loginResponsePacket = new LoginResponsePacket();
        loginResponsePacket.setVersion(loginRequestPacket.getVersion());
        if (valid(loginRequestPacket)) {
            loginResponsePacket.setSuccess(true);
            String userId = IDUtil.randomUserId();
            loginResponsePacket.setUserId(userId);
            SessionUtil.bindSession(new Session(userId, loginRequestPacket.getUsername()), channelHandlerContext.channel());
            LoginUtil.markAsLogin(channelHandlerContext.channel());
            System.out.print("[" + loginRequestPacket.getUsername() + "]登录成功");
            System.out.println("[userId]" + loginResponsePacket.getUserId());
        } else {
            loginResponsePacket.setReason("账号密码校验失败");
            loginResponsePacket.setSuccess(false);
            System.out.println("[" + loginRequestPacket.getUsername() + "]登录失败");
        }
        // 编码
        ByteBuf responseByteBuf = PacketCodeC.INSTANCE.encode(channelHandlerContext.alloc(), loginResponsePacket);
        channelHandlerContext.channel().writeAndFlush(responseByteBuf);
    }

    // 用户断线之后取消绑定
    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        System.out.println("用户下线，取消Map绑定");
        SessionUtil.unBindSession(ctx.channel());
    }

    private boolean valid(LoginRequestPacket loginRequestPacket) {
        if(loginRequestPacket.getUsername().equals("Cap_Sub") && loginRequestPacket.getPassword().equals("123456")){
            return true;
        }
        return false;
    }

}
