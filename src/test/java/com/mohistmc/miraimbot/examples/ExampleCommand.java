package com.mohistmc.miraimbot.examples;


import com.mohistmc.miraimbot.cmds.manager.CommandExecutor;
import com.mohistmc.miraimbot.cmds.manager.CommandResult;
import com.mohistmc.miraimbot.annotations.Command;

@Command(name = "example", alias = {"示例"}, usage = "示例", description = "示例")
public class ExampleCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandResult result) {
        // TODO:
        return false;
    }
}
