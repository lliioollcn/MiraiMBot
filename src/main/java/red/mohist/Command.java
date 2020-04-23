package red.mohist;

import lombok.Data;
import xin.lz1998.cq.entity.CQGroupUser;
import xin.lz1998.cq.event.CQEvent;
import xin.lz1998.cq.event.message.CQGroupMessageEvent;
import xin.lz1998.cq.robot.CoolQ;

@Data
public class Command {
    private final String label;
    private final String[] args;
    private final CQGroupMessageEvent event;
    private final CoolQ cq;

    public Command(String msg, CoolQ cq, CQGroupMessageEvent event) {
        String[] cmd = msg.split(" ");
        this.label = cmd[0];
        this.event = event;
        this.cq = cq;
        String[] args = new String[cmd.length - 1];
        for (int i = 0; i < args.length; i++) {
            args[i] = cmd[i + 1];
        }
        this.args = args;
    }
}
