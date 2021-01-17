package com.mohistmc.miraimbot.command;

import com.mohistmc.miraimbot.annotations.Command;
import lombok.Data;


@Data
public abstract class CommandExecutor {

    public String label;
    public String usage;
    public String description;
    public boolean noshow;
    public boolean opCan;
    public boolean onlyOp;
    public boolean permissionEnable;
    public String permission;
    public Command.Type type;

    public abstract boolean onCommand(CommandResult result);
}
