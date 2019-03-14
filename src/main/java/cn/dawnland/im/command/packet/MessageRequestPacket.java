package cn.dawnland.im.command.packet;

import cn.dawnland.im.command.Command;
import cn.dawnland.im.command.Packet;
import lombok.Data;

/**
 * @author Cap_Sub
 */
@Data
public class MessageRequestPacket extends Packet {

    private String message;

    private String toUserId;

    @Override
    public Byte getCommand() {
        return Command.MESSAGE_REQUEST;
    }

    public MessageRequestPacket(String toUserId, String message) {
        this.toUserId = toUserId;
        this.message = message;
    }

    public MessageRequestPacket() {
    }
}
