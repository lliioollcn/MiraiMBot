package com.mohistmc.miraimbot.plugin;

import com.mohistmc.miraimbot.MiraiMBot;
import com.mohistmc.miraimbot.cmds.manager.CommandExecutor;
import com.mohistmc.miraimbot.cmds.manager.CommandManager;
import com.mohistmc.miraimbot.console.log4j.MiraiMBotLog;
import net.mamoe.mirai.event.Events;
import net.mamoe.mirai.event.ListenerHost;
import org.apache.logging.log4j.Logger;

public class MohistPlugin {

    public void onLoad() {

    }

    public void onEnable() {

    }

    public void onDisable() {

    }

    public Logger getLogger() {
        return MiraiMBotLog.LOGGER;
    }

    public void registerEvents(ListenerHost listenerHost) {
        Events.registerEvents(MiraiMBot.bot, listenerHost);
    }

    public void registerCommands(CommandExecutor newInstance) {
        CommandManager.register(newInstance);
    }

    public void sendGroupMessage(Long group, String msg) {
        MiraiMBot.bot.getGroup(group).sendMessage(msg);
    }

}
