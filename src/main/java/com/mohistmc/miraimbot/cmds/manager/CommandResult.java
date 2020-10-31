package com.mohistmc.miraimbot.cmds.manager;

import java.util.List;

import com.mohistmc.miraimbot.utils.Utils;
import lombok.Data;
import net.mamoe.mirai.contact.Group;
import net.mamoe.mirai.contact.Member;
import net.mamoe.mirai.contact.User;
import net.mamoe.mirai.message.data.MessageChain;

@Data
public class CommandResult {
    public User sender;
    public List<String> args;
    public MessageChain source;

    /**
     * 判断当前指令是否来自群组
     *
     * @return
     */
    public boolean isTroop() {
        return isGroup();
    }

    /**
     * 判断当前指令是否来自群组
     *
     * @return
     */
    public boolean isGroup() {
        return (sender instanceof Member);
    }

    public Group getGroupOrNull() {
        return isTroop() ? ((Member) sender).getGroup() : null;
    }

    public void sendMessage(String message) {
        Utils.sendMessage(sender, message);
    }
}
