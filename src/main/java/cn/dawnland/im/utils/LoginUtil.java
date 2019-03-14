package cn.dawnland.im.utils;

import cn.dawnland.im.command.Attributes;
import io.netty.channel.Channel;
import io.netty.util.Attribute;

/**
 * @author Cap_Sub
 */
public class LoginUtil {

    public static void markAsLogin(Channel channel) {
        channel.attr(Attributes.LOGIN).set(true);
    }

    public static boolean hasLogin(Channel channel) {
        Attribute<Boolean> loginAttr = channel.attr(Attributes.LOGIN);
        return loginAttr.get() != null;
    }


}
