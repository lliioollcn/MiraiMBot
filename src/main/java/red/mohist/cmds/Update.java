package red.mohist.cmds;

import red.mohist.Command;
import red.mohist.CommandExecutor;
import red.mohist.UpdateUtils;
import xin.lz1998.cq.event.message.CQGroupMessageEvent;
import xin.lz1998.cq.robot.CoolQ;

public class Update implements CommandExecutor {
    @Override
    public boolean onCommand(Command cmd) {
        CoolQ cq = cmd.getCq();
        CQGroupMessageEvent event = cmd.getEvent();
        if (cmd.getArgs().length <= 0) {
            cq.sendGroupMsg(event.getGroupId(),
                            "======更新检测======\n" +
                            "检测失败,请输入分支哦~", false);
            return true;
        }
        String msg = cmd.getArgs()[0];
        if (msg.equals("1.12.2") || msg.equals("1.15.2")) {
            try {
                cq.sendGroupMsg(event.getGroupId(), UpdateUtils.info(msg), false);
                System.out.println("CI数据读取完毕");
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                cq.sendGroupMsg(event.getGroupId(),
                                "======更新检测======\n" +
                                "检测失败,内部错误哦~", false);

                return false;
            }
        }
        return true;
    }
}
