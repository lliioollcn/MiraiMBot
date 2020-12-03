package com.mohistmc.miraimbot.cmds;

import com.mohistmc.miraimbot.cmds.manager.CommandExecutor;
import com.mohistmc.miraimbot.cmds.manager.CommandResult;
import com.mohistmc.miraimbot.cmds.manager.annotations.Command;
import com.mohistmc.miraimbot.permission.MPermission;
import com.mohistmc.miraimbot.utils.Utils;
import java.util.Arrays;

@Command(name = "permission", description = "权限管理", alias = {"per", "权限"}, usage = "#permission", show = false, onlyOp = true)
public class PermissionCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandResult result) {
        StringBuilder msg = new StringBuilder();
        if (result.getArgs().isEmpty()) {
            msg.append("=======[权限]=======").append("\n");
            msg.append("#permission add [用户] [权限]").append("\n");
            msg.append("给[用户]添加一条[权限]").append("\n");
            msg.append("#permission set [用户] [组]").append("\n");
            msg.append("将[用户]移动到[组]").append("\n");
            msg.append("#permission setOp [用户]").append("\n");
            msg.append("设置[用户]为机器人管理").append("\n");
            msg.append("#permission check [用户] [权限]").append("\n");
            msg.append("查看[用户]是否有[权限]").append("\n");
            msg.append("#permission checkAll [用户]");
            msg.append("查看[用户]所有权限").append("\n").append("\n");
            msg.append("#permission checkGroup [用户]").append("\n");
            msg.append("查看[用户]所在组").append("\n");
            msg.append("以上所有的[用户]在群内可以为@").append("\n");
        } else {
            String arg0 = result.getArgs().get(0);
            if ("add".equals(arg0)) {
                if (result.getArgs().size() < 2) {
                    msg.append("[用户]或[权限]不可为空");
                } else {
                    String arg1 = result.getArgs().get(1);
                    String arg2 = result.getArgs().get(2);
                    long id = Utils.getId(arg1, result);
                    if (MPermission.addPermission(id, arg2)) {
                        msg.append("成功");
                    } else {
                        msg.append("失败");
                    }
                }
            } else if ("set".equals(arg0)) {
                if (result.getArgs().size() < 2) {
                    msg.append("[用户]或[组]不可为空");
                } else {
                    String arg1 = result.getArgs().get(1);
                    String arg2 = result.getArgs().get(2);
                    long id = Utils.getId(arg1, result);
                    if (MPermission.setGroup(id, arg2)) {
                        msg.append("成功");
                    } else {
                        msg.append("失败");
                    }
                }
            } else if ("setOp".equals(arg0)) {
                if (result.getArgs().size() < 1) {
                    msg.append("[用户]不可为空");
                } else {
                    String arg1 = result.getArgs().get(1);
                    long id = Utils.getId(arg1, result);
                    if (MPermission.setOp(id)) {
                        msg.append("成功");
                    } else {
                        msg.append("失败");
                    }
                }
            } else if ("check".equals(arg0)) {
                if (result.getArgs().size() < 2) {
                    msg.append("[用户]或[权限]不可为空");
                } else {
                    String arg1 = result.getArgs().get(1);
                    String arg2 = result.getArgs().get(2);
                    long id = Utils.getId(arg1, result);
                    if (MPermission.hasPermission(id, arg2)) {
                        msg.append("用户拥有这个权限: ").append(arg2);
                    } else {
                        msg.append("用户不拥有这个权限: ").append(arg2);
                    }
                }
            } else if ("checkAll".equals(arg0)) {
                if (result.getArgs().size() < 1) {
                    msg.append("[用户]不可为空");
                } else {
                    msg.append("此用户拥有以下权限: ");
                    String arg1 = result.getArgs().get(1);
                    long id = Utils.getId(arg1, result);
                    msg.append(Arrays.toString(MPermission.getAllPermission(id).toArray()).replace("[", "")
                            .replace("]", "")
                            .replace(",", "\n"));
                }
            } else if ("checkGroup".equals(arg0)) {
                if (result.getArgs().size() < 1) {
                    msg.append("[用户]不可为空");
                } else {
                    msg.append("此用户在组 ");
                    String arg1 = result.getArgs().get(1);
                    long id = Utils.getId(arg1, result);
                    msg.append(MPermission.getGroup(id));
                }
            }
        }
        result.sendMessage(msg.toString());
        return true;
    }
}
