package cn.dawnland.im.command.packet;

import cn.dawnland.im.command.Command;
import cn.dawnland.im.command.Packet;
import lombok.Data;

/**
 * @author Cap_Sub
 */
@Data
public class GroupMessageRequestPacket extends Packet {

    private String toGroupId;

    private String message;

    @Override
    public Byte getCommand() {
        return Command.GROUPMESSAGE_REQUEST;
    }
}
