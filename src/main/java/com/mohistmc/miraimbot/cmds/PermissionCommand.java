package com.mohistmc.miraimbot.cmds;

import com.mohistmc.miraimbot.cmds.manager.CommandExecutor;
import com.mohistmc.miraimbot.cmds.manager.CommandResult;
import com.mohistmc.miraimbot.cmds.manager.annotations.Command;
import com.mohistmc.miraimbot.permission.MPermission;
import com.mohistmc.miraimbot.plugin.Plugin;
import com.mohistmc.miraimbot.plugin.PluginLoader;
import com.mohistmc.miraimbot.utils.Utils;
import net.mamoe.mirai.message.data.At;
import net.mamoe.mirai.message.data.Message;
import net.mamoe.mirai.qqandroid.network.protocol.data.jce._339;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Command(name = "permission", description = "权限管理", alias = {"per", "权限"}, usage = "#permission", show = false, onlyOp = true)
public class PermissionCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandResult result) {
        StringBuilder msg = new StringBuilder();
        if (result.getArgs().size() < 1) {
            msg.append("==========[权限]==========");
            msg.append("#permission add [用户] [权限]");
            msg.append("给[用户]添加一条[权限]");
            msg.append("#permission set [用户] [组]");
            msg.append("将[用户]移动到[组]");
            msg.append("#permission setOp [用户]");
            msg.append("设置[用户]为机器人管理");
            msg.append("#permission check [用户] [权限]");
            msg.append("查看[用户]是否有[权限]");
            msg.append("#permission checkAll [用户]");
            msg.append("查看[用户]所有权限");
            msg.append("#permission checkGroup [用户]");
            msg.append("查看[用户]所在组");
            msg.append("以上所有的[用户]在群内可以为@");
        } else {
            String arg0 = result.getArgs().get(0);
            if ("add".equals(arg0)) {
                if (result.getArgs().size() < 2) {
                    msg.append("[用户]或[权限]不可为空");
                } else {
                    String arg1 = result.getArgs().get(1);
                    String arg2 = result.getArgs().get(2);
                    long id = 0L;
                    if (arg1.startsWith("@")) {
                        if (!result.isGroup()) {
                            msg.append("不在群组中");
                            return true;
                        }
                        id = result.getSource().first(At.Key).getTarget();

                    } else {
                        if (!Utils.isNumeric(arg1)) {
                            msg.append("[用户]为无效数字");
                            return true;
                        }
                        id = Long.parseLong(arg1);
                    }
                    MPermission.addPermission(id, arg2);
                }
            } else if ("set".equals(arg0)) {
                if (result.getArgs().size() < 2) {
                    msg.append("[用户]或[组]不可为空");
                } else {
                    String arg1 = result.getArgs().get(1);
                    String arg2 = result.getArgs().get(2);
                    long id = 0L;
                    if (arg1.startsWith("@")) {
                        if (!result.isGroup()) {
                            msg.append("不在群组中");
                            return true;
                        }
                        id = result.getSource().first(At.Key).getTarget();

                    } else {
                        if (!Utils.isNumeric(arg1)) {
                            msg.append("[用户]为无效数字");
                            return true;
                        }
                        id = Long.parseLong(arg1);
                    }
                    MPermission.setGroup(id,arg2);
                }
            } else if ("setOp".equals(arg0)) {

            } else if ("check".equals(arg0)) {

            } else if ("checkAll".equals(arg0)) {

            } else if ("checkGroup".equals(arg0)) {

            }
        }
        result.sendMessage(msg.toString());
        return true;
    }
}
