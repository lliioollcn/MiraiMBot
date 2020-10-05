package com.mohistmc.miraimbot.cmds;


import com.mohistmc.miraimbot.MiraiMBot;
import com.mohistmc.miraimbot.cmds.manager.CommandExecutor;
import com.mohistmc.miraimbot.cmds.manager.CommandResult;
import com.mohistmc.miraimbot.cmds.manager.ConsoleSender;
import com.mohistmc.miraimbot.cmds.manager.annotations.Command;
import com.mohistmc.miraimbot.utils.Utils;
import net.mamoe.mirai.BotFactoryJvm;

@Command(name = "login", description = "登陆机器人", alias = {"l", "登陆", "登录", "登入"}, usage = "用于登陆机器人")
public class LoginCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandResult result) {
        if (result.getSender() instanceof ConsoleSender) {
            if (result.getArgs().size() < 2) {
                Utils.sendMessage(result.getSender(), "用户名或密码不能为空");
            } else if (!Utils.isNumeric(result.getArgs().get(0))) {
                Utils.sendMessage(result.getSender(), "用户名必须是数字");
            } else
                MiraiMBot.bot = BotFactoryJvm.newBot(Long.parseLong(result.getArgs().get(0)), result.getArgs().get(1), Utils.defaultConfig());
        }
        return true;
    }
}
