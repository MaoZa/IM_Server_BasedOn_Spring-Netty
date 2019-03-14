package cn.dawnland.im.command;

import cn.dawnland.im.command.packet.MessageRequestPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * @author Cap_Sub
 */
public class SendToUserConsoleCommand implements ConsoleCommand {
    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.println("开始发送消息");
        System.out.print("请输入目标userId: ");
        String toUserId = scanner.next();
        System.out.print("请输入内容: ");
        String message = scanner.next();
        channel.writeAndFlush(new MessageRequestPacket(toUserId, message));
    }
}
