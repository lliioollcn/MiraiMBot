package com.mohistmc.miraimbot.command;

import com.mohistmc.miraimbot.utils.Utils;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import net.mamoe.mirai.contact.Group;
import net.mamoe.mirai.contact.Member;
import net.mamoe.mirai.contact.UserOrBot;
import net.mamoe.mirai.message.data.MessageChain;

@EqualsAndHashCode(callSuper = false)
@Data
public class CommandResult extends Utils {
    public UserOrBot sender;
    public List<String> args;
    public MessageChain source;
    public CharSequence label;


    /**
     * 判断当前指令是否来自群组
     *
     * @return
     */
    public boolean isGroup() {
        return (sender instanceof Member);
    }

    public Group getGroupOrNull() {
        return isGroup() ? ((Member) sender).getGroup() : null;
    }

}
