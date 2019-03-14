package cn.dawnland.im.command;

import cn.dawnland.im.model.Session;
import io.netty.util.AttributeKey;

/**
 * @author Cap_Sub
 */
public interface Attributes {

    AttributeKey<Boolean> LOGIN = AttributeKey.newInstance("login");

    AttributeKey<Session> SESSION = AttributeKey.newInstance("session");

}
