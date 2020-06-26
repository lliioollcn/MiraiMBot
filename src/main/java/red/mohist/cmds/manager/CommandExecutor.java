package red.mohist.cmds.manager;

import net.mamoe.mirai.message.data.MessageChain;

import java.util.List;

public interface CommandExecutor {

    public boolean onCommand(CommandResult result);
}
