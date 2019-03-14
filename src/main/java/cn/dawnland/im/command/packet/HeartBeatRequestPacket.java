package cn.dawnland.im.command.packet;

import cn.dawnland.im.command.Command;
import cn.dawnland.im.command.Packet;
import lombok.Data;

/**
 * @author Cap_Sub
 */
@Data
public class HeartBeatRequestPacket extends Packet {

    private Byte aByte = 1;

    @Override
    public Byte getCommand() {
        return Command.HEARTBEAT_REQUEST;
    }
}
