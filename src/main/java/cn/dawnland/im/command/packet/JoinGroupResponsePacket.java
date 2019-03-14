package cn.dawnland.im.command.packet;

import cn.dawnland.im.command.Command;
import cn.dawnland.im.command.Packet;
import lombok.Data;

/**
 * @author Cap_Sub
 */
@Data
public class JoinGroupResponsePacket extends Packet {

    private Boolean success;

    private String groupId;

    private String reason;

    @Override
    public Byte getCommand() {
        return Command.JOINGROUP_RESPONSE;
    }
}
