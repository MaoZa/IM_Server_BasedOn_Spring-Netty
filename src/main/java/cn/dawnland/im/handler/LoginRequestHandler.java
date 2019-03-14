package cn.dawnland.im.handler;

import cn.dawnland.im.command.PacketCodeC;
import cn.dawnland.im.command.packet.LoginRequestPacket;
import cn.dawnland.im.command.packet.LoginResponsePacket;
import cn.dawnland.im.model.Session;
import cn.dawnland.im.model.User;
import cn.dawnland.im.service.UserService;
import cn.dawnland.im.utils.IDUtil;
import cn.dawnland.im.utils.LoginUtil;
import cn.dawnland.im.utils.SessionUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Cap_Sub
 */
@ChannelHandler.Sharable
@Component
public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginRequestPacket> {

    private Logger logger = LoggerFactory.getLogger(LoginRequestHandler.class);

    @Autowired
    private UserService userService;

    @Override
    protected void messageReceived(ChannelHandlerContext channelHandlerContext, LoginRequestPacket loginRequestPacket) throws Exception {
        /**
         * 登录校验
         */
        LoginResponsePacket valid = valid(loginRequestPacket);
        valid.setVersion(loginRequestPacket.getVersion());
        if (valid.getSuccess()) {
            SessionUtil.bindSession(new Session(valid.getUserId() + "", loginRequestPacket.getEmail()), channelHandlerContext.channel());
            LoginUtil.markAsLogin(channelHandlerContext.channel());
            logger.info("[" + loginRequestPacket.getEmail() + "]登录成功" + "[userId]" + valid.getUserId());
        } else {
            System.out.println("[" + loginRequestPacket.getEmail() + "]登录失败");
        }
        // 编码
        ByteBuf responseByteBuf = PacketCodeC.INSTANCE.encode(channelHandlerContext.alloc(), valid);
        channelHandlerContext.channel().writeAndFlush(valid);
    }

    /**
     * 用户断线之后取消绑定
     * @param ctx
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        System.out.println("用户下线，取消Map绑定");
        SessionUtil.unBindSession(ctx.channel());
    }

    private LoginResponsePacket valid(LoginRequestPacket loginRequestPacket) {
        User user = userService.selectByParams(null, loginRequestPacket.getEmail(), null);
        LoginResponsePacket loginResponsePacket = new LoginResponsePacket();
        if(user == null){
            loginResponsePacket.setSuccess(false);
            loginResponsePacket.setReason("登录失败,邮箱不存在");
        }else{
            if(user.getPassword().equals(loginRequestPacket.getPassword())){
                loginResponsePacket.setSuccess(true);
                loginResponsePacket.setReason("登录成功");
                loginResponsePacket.setUserId(user.getUid() + "");
            }else{
                loginResponsePacket.setSuccess(false);
                loginResponsePacket.setReason("登录失败,密码错误");
            }
        }
        return loginResponsePacket;
    }

}
