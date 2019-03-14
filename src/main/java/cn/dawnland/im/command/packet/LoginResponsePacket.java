package cn.dawnland.im.command.packet;

import cn.dawnland.im.command.Command;
import cn.dawnland.im.command.Packet;
import lombok.Data;

/**
 * @author Cap_Sub
 */
@Data
public class LoginResponsePacket extends Packet {

    private String reason;

    private Boolean success;

    private Byte version;

    private String userId;

    @Override
    public Byte getCommand() {
        return Command.LOGIN_RESPONSE;
    }
}
