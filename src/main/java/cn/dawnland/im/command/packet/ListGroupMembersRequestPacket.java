package cn.dawnland.im.command.packet;

import cn.dawnland.im.command.Command;
import cn.dawnland.im.command.Packet;
import lombok.Data;

/**
 * @author Cap_Sub
 */
@Data
public class ListGroupMembersRequestPacket extends Packet {

    private String groupId;

    @Override
    public Byte getCommand() {
        return Command.LISTGROUP_REQUEST;
    }
}
