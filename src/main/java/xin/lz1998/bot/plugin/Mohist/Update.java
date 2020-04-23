package xin.lz1998.bot.plugin.Mohist;

import red.mohist.Command;
import red.mohist.CommandManager;
import xin.lz1998.cq.event.message.CQGroupMessageEvent;
import xin.lz1998.cq.robot.CQPlugin;
import xin.lz1998.cq.robot.CoolQ;

public class Update extends CQPlugin {
    // 指令前缀
    //private String prefix = "#更新";
    private String prefix = "#";

    @Override
    public int onGroupMessage(CoolQ cq, CQGroupMessageEvent event) {
        String msg = event.getMessage();
        if (msg.startsWith(prefix) && (event.getGroupId() == 793311898 || event.getGroupId() == 782534813)) {
            // 指令以 prefix 开头，去除prefix，并继续执行下一个插件
            msg = msg.substring(prefix.length());
            CommandManager.call(new Command(msg, cq, event));
        }
        return MESSAGE_IGNORE;
    }


}
