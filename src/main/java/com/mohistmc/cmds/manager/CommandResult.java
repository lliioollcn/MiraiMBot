package com.mohistmc.cmds.manager;

import lombok.Data;
import net.mamoe.mirai.contact.Member;
import net.mamoe.mirai.message.data.MessageChain;

import java.util.List;

@Data
public class CommandResult {
    public Member sender;
    public List<String> args;
    public MessageChain source;
}
