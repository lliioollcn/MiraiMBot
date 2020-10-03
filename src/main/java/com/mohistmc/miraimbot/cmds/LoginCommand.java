package com.mohistmc.miraimbot.cmds;


import com.mohistmc.miraimbot.cmds.manager.CommandExecutor;
import com.mohistmc.miraimbot.cmds.manager.CommandResult;
import com.mohistmc.miraimbot.cmds.manager.annotations.Command;
@Command(name = "login", description = "登陆机器人", alias = {"l", "登陆", "登录", "登入"}, usage = "用于登陆机器人")
public class LoginCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandResult result) {

        return true;
    }
}
