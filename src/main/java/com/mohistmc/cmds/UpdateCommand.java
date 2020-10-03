package com.mohistmc.cmds;

import com.mohistmc.MiraiMBot;
import com.mohistmc.cmds.manager.CommandExecutor;
import com.mohistmc.cmds.manager.CommandResult;
import com.mohistmc.cmds.manager.annotations.Command;
import com.mohistmc.utils.UpdateUtils;

@Command(name = "update", usage = "#update")
public class UpdateCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandResult result) {
        if (result.getArgs().size() <= 0) {
            result.getSender().getGroup().sendMessage("======更新检测======\n检测失败,请输入分支哦~");
            return true;
        }
        String msg = result.getArgs().get(0);
        if (msg.equals("1.12.2") || msg.equals("1.16.3")) {
            try {
                result.getSender().getGroup().sendMessage("正在读取数据，请稍后~");
                result.getSender().getGroup().sendMessage(UpdateUtils.info(MiraiMBot.version.get(msg)));
                System.out.println("CI数据读取完毕");
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                result.getSender().getGroup().sendMessage("======更新检测======\n检测失败,内部错误哦~");
                return false;
            }
        }
        return true;
    }
}
