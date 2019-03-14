package cn.dawnland.im.command.packet;

import cn.dawnland.im.command.Command;
import cn.dawnland.im.command.Packet;
import cn.dawnland.im.model.Session;
import lombok.Data;

import java.util.List;

/**
 * @author Cap_Sub
 */
@Data
public class ListGroupMembersResponsePacket extends Packet {

    private String groupId;

    private List<Session> sessionList;

    @Override
    public Byte getCommand() {
        return Command.LISTGROUP_RESPONSE;
    }
}
