package com.mohistmc.miraimbot.cmds.manager;

import lombok.Data;
import net.mamoe.mirai.contact.User;
import net.mamoe.mirai.message.data.MessageChain;

import java.util.List;

@Data
public class CommandResult {
    public User sender;
    public List<String> args;
    public MessageChain source;
}
