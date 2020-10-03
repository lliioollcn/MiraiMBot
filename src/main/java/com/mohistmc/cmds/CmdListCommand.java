package com.mohistmc.cmds;

import com.mohistmc.cmds.manager.CommandExecutor;
import com.mohistmc.cmds.manager.CommandManager;
import com.mohistmc.cmds.manager.CommandResult;
import com.mohistmc.cmds.manager.annotations.Command;
import java.util.Set;

@Command(name = "cmdlist", description = "查看指令列表", alias = {"help", "帮助"}, usage = "用于获取指令列表")
public class CmdListCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandResult result) {
        StringBuilder msg = new StringBuilder();
        if (result.getArgs().size() < 1) {
            Set<String> executors = CommandManager.usages.keySet();
            for (String executor : executors) {
                msg.append(executor).append("\n");
            }
        } else {
            String label = result.getArgs().get(0);
            if (CommandManager.executors.containsKey(label)) {
                CommandExecutor c = CommandManager.executors.get(label);
                msg.append("指令:")
                        .append(c)
                        .append("\n");
            } else {
                msg.append("不存在的指令。");
            }
        }
        result.getSender().getGroup().sendMessage(msg.toString());
        return true;
    }
}
