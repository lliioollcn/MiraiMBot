package com.mohistmc.miraimbot.command;

import com.mohistmc.miraimbot.utils.Utils;
import lombok.Builder;
import lombok.Data;
import net.mamoe.mirai.Bot;
import net.mamoe.mirai.contact.Group;
import net.mamoe.mirai.contact.Member;
import net.mamoe.mirai.contact.UserOrBot;
import net.mamoe.mirai.message.data.MessageChain;

import java.util.List;

@Data
@Builder
public class CommandResult {
    public UserOrBot sender;
    public Bot bot;
    public List<String> args;
    public MessageChain source;
    public CharSequence label;


    /**
     * 判断当前指令是否来自群组
     *
     * @return
     */
    public boolean isGroup() {
        return Utils.isGroup(sender);
    }

    public Group getGroupOrNull() {
        return isGroup() ? ((Member) sender).getGroup() : null;
    }

}
