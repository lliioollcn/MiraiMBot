package com.mohistmc.cmds;

import com.mohistmc.cmds.manager.CommandExecutor;
import com.mohistmc.cmds.manager.CommandManager;
import com.mohistmc.cmds.manager.CommandResult;
import com.mohistmc.cmds.manager.annotations.Command;

import java.util.Arrays;
import java.util.Set;

@Command(name = "cmdlist", description = "查看指令列表", alias = {"help", "帮助"}, usage = "用于获取指令列表")
public class CmdListCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandResult result) {
        StringBuilder msg = new StringBuilder();
        if (result.getArgs().size() < 1) {
            Set<Class<?>> executors = CommandManager.usages.keySet();
            for (Class<?> executor : executors) {
                Command c = executor.getAnnotation(Command.class);
                msg.append("指令:")
                        .append(c.name())
                        .append(" 用法:")
                        .append(c.usage())
                        .append(" 别称:")
                        .append(Arrays.toString(c.alias()))
                        .append(" 描述:")
                        .append(c.description())
                        .append("\n");
            }
        } else {
            String label = result.getArgs().get(0);
            if (CommandManager.executors.containsKey(label)) {
                Command c = CommandManager.executors.get(label).getAnnotation(Command.class);
                msg.append("指令:")
                        .append(c.name())
                        .append(" 用法:")
                        .append(c.usage())
                        .append(" 别称:")
                        .append(Arrays.toString(c.alias()))
                        .append(" 描述:")
                        .append(c.description())
                        .append("\n");
            } else {
                msg.append("不存在的指令。");
            }
        }
        result.getSender().getGroup().sendMessageAsync(msg.toString());
        return true;
    }
}
