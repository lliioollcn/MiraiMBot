package xin.lz1998.bot.plugin.Mohist;

import org.apache.logging.log4j.LogManager;
import xin.lz1998.cq.event.message.CQDiscussMessageEvent;
import xin.lz1998.cq.event.message.CQGroupMessageEvent;
import xin.lz1998.cq.event.message.CQPrivateMessageEvent;
import xin.lz1998.cq.event.notice.CQGroupUploadNoticeEvent;
import xin.lz1998.cq.robot.CQPlugin;
import xin.lz1998.cq.robot.CoolQ;

public class Log extends CQPlugin {

    @Override
    public int onPrivateMessage(CoolQ cq, CQPrivateMessageEvent event) {
        LogManager.getLogger("MohistBot").info("类型: 私聊消息 账号: {} 内容: {}", event.getSender(), event.getMessage());
        return MESSAGE_IGNORE;
    }

    @Override
    public int onGroupMessage(CoolQ cq, CQGroupMessageEvent event) {
        LogManager.getLogger("MohistBot").info("类型: 群聊消息 群号: {} 账号: {} 内容: {}", event.getGroupId(), event.getSender(), event.getMessage());
        return MESSAGE_IGNORE;
    }

    @Override
    public int onDiscussMessage(CoolQ cq, CQDiscussMessageEvent event) {
        LogManager.getLogger("MohistBot").info("类型: 讨论组消息 讨论组号: {} 账号: {} 内容: {}", event.getDiscussId(), event.getSender(), event.getMessage());
        return MESSAGE_IGNORE;
    }

    @Override
    public int onGroupUploadNotice(CoolQ cq, CQGroupUploadNoticeEvent event) {
        LogManager.getLogger("MohistBot").info("类型: 群聊上传文件 群号: {} 文件: {}", event.getGroupId(), event.getFile().getName());
        return MESSAGE_IGNORE;
    }
}
