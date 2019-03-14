package cn.dawnland.im.command.packet;

import cn.dawnland.im.command.Command;
import cn.dawnland.im.command.Packet;
import cn.dawnland.im.model.Session;
import lombok.Data;

/**
 * @author Cap_Sub
 */
@Data
public class GroupMessageResponsePacket extends Packet {

    private String fromGroupId;

    private String message;

    private Session fromUser;

    @Override
    public Byte getCommand() {
        return Command.GROUPMESSAGE_RESPONSE;
    }
}
