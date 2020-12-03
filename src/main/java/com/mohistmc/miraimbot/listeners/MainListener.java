package com.mohistmc.miraimbot.listeners;

import com.mohistmc.miraimbot.cmds.manager.CommandManager;
import com.mohistmc.miraimbot.cmds.manager.ConsoleSender;
import com.mohistmc.miraimbot.events.ConsoleMessageEvent;
import com.mohistmc.miraimbot.utils.LogUtil;
import kotlin.coroutines.CoroutineContext;
import net.mamoe.mirai.event.EventHandler;
import net.mamoe.mirai.event.Listener;
import net.mamoe.mirai.event.ListeningStatus;
import net.mamoe.mirai.event.SimpleListenerHost;
import net.mamoe.mirai.message.GroupMessageEvent;
import org.jetbrains.annotations.NotNull;

public class MainListener extends SimpleListenerHost {

    @EventHandler(priority = Listener.EventPriority.HIGHEST)
    public ListeningStatus onGroupMessage(GroupMessageEvent event) {
        String content = event.getMessage().contentToString();
        if (content.startsWith(LogUtil.command)) {
            CommandManager.call(event.getMessage(), event.getSender());
            event.intercept();
        }
        return ListeningStatus.LISTENING;
    }

    @EventHandler(priority = Listener.EventPriority.HIGHEST)
    public ListeningStatus onConsoleMessage(ConsoleMessageEvent event) {
        CommandManager.call(event.getMessage(), ConsoleSender.INSTANCE);
        return ListeningStatus.LISTENING;
    }


    @Override
    public void handleException(@NotNull CoroutineContext context, @NotNull Throwable exception) {
        throw new RuntimeException("在事件处理中发生异常", exception);
    }

}
