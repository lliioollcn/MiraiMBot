package com.mohistmc.miraimbot.cmds;

import com.google.common.collect.Lists;
import com.mohistmc.miraimbot.cmds.manager.CommandExecutor;
import com.mohistmc.miraimbot.cmds.manager.CommandManager;
import com.mohistmc.miraimbot.cmds.manager.CommandResult;
import com.mohistmc.miraimbot.cmds.manager.annotations.Command;

import java.util.*;
import java.util.stream.Collectors;

@Command(name = "help", description = "查看指令列表", alias = {"?", "帮助"}, usage = "用于获取指令列表")
public class CmdListCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandResult result) {
        StringBuilder msg = new StringBuilder();
        Set<Command> cmds = CommandManager.cmd_map;
        if (result.getArgs().size() == 1) {
            msg.append("======指令信息======").append("\n");
            String label = result.getArgs().get(0);
            for (Command executor : cmds) {
                if (!executor.show()) continue;
                if (executor.name().equals(label)) {
                    msg.append("名字: " + executor.name()).append("\n");
                    List<String> authors = new ArrayList<>();
                    Collections.addAll(authors, executor.alias());
                    msg.append("别称: " + authors.toString()).append("\n");
                    msg.append("简介: " + executor.description()).append("\n");
                    msg.append("用法: " + executor.usage()).append("\n");
                }
            }
        } else {
            List<String> list = Lists.newArrayList();
            for (Command cmd : cmds) {
                if (!cmd.show()) continue;
                list.add(cmd.name());
            }
            msg.append("======指令列表(" + list.size() + ")======").append("\n");
            msg.append(Arrays.toString(list.toArray()));
        }
        result.sendMessage(msg.toString());
        return true;
    }
}
