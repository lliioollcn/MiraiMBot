package com.mohistmc.miraimbot.cmds;


import com.mohistmc.miraimbot.MiraiMBot;
import com.mohistmc.miraimbot.cmds.manager.CommandExecutor;
import com.mohistmc.miraimbot.cmds.manager.CommandResult;
import com.mohistmc.miraimbot.cmds.manager.ConsoleSender;
import com.mohistmc.miraimbot.cmds.manager.annotations.Command;
import com.mohistmc.miraimbot.utils.Utils;
import lombok.SneakyThrows;
import net.mamoe.mirai.BotFactoryJvm;

@Command(name = "login", description = "登陆机器人", alias = {"l", "登陆", "登录", "登入"}, usage = "用于登陆机器人", show = false, onlyOp = true)
public class LoginCommand implements CommandExecutor {
    @SneakyThrows
    @Override
    public boolean onCommand(CommandResult result) {
        if (result.getSender() instanceof ConsoleSender) {
            if (result.getArgs().size() < 2) {
                Utils.sendMessage(result.getSender(), "用户名或密码不能为空");
            } else if (!Utils.isNumeric(result.getArgs().get(0))) {
                Utils.sendMessage(result.getSender(), "用户名必须是数字");
               // Utils.sendMessage(result.getSender(), result.getArgs().get(0));
            } else {
                MiraiMBot.bot = BotFactoryJvm.newBot(Long.parseLong(result.getArgs().get(0)), result.getArgs().get(1), Utils.defaultConfig());
                if (!MiraiMBot.file.exists()) {
                    MiraiMBot.file.mkdir();
                }
                MiraiMBot.yaml.set("qq", Long.parseLong(result.getArgs().get(0)));
                MiraiMBot.yaml.set("password", result.getArgs().get(1));
                MiraiMBot.yaml.save(MiraiMBot.file);

            }
        }
        return true;
    }
}
