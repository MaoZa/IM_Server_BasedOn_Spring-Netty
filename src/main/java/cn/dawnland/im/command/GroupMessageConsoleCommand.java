package cn.dawnland.im.command;

import cn.dawnland.im.command.packet.GroupMessageRequestPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * @author Cap_Sub
 */
public class GroupMessageConsoleCommand implements ConsoleCommand {

    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.print("请输入群组id:");
        String groupId = scanner.next();
        System.out.print("请输入发送内容:");
        String meassage = scanner.next();
        GroupMessageRequestPacket groupMessageRequestPacket = new GroupMessageRequestPacket();
        groupMessageRequestPacket.setToGroupId(groupId);
        groupMessageRequestPacket.setMessage(meassage);
        channel.writeAndFlush(groupMessageRequestPacket);
    }
}
