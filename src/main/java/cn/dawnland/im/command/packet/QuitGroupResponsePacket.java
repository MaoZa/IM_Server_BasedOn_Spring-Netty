package cn.dawnland.im.command.packet;

import cn.dawnland.im.command.Command;
import cn.dawnland.im.command.Packet;
import lombok.Data;

/**
 * @author Cap_Sub
 */
@Data
public class QuitGroupResponsePacket extends Packet {

    private String groupId;

    private Boolean success;

    @Override
    public Byte getCommand() {
        return Command.QUITGROUP_RESPONSE;
    }
}
