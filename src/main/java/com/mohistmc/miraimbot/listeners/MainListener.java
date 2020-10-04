package com.mohistmc.miraimbot.listeners;

import com.alibaba.fastjson.JSON;
import com.mohistmc.miraimbot.MiraiMBot;
import com.mohistmc.miraimbot.cmds.manager.CommandManager;
import com.mohistmc.miraimbot.cmds.manager.ConsoleSender;
import com.mohistmc.miraimbot.events.ConsoleMessageEvent;
import com.mohistmc.miraimbot.utils.HasteUtils;
import com.mohistmc.miraimbot.utils.LogUtil;

import java.io.IOException;

import kotlin.coroutines.CoroutineContext;
import net.mamoe.mirai.contact.MemberPermission;
import net.mamoe.mirai.event.EventHandler;
import net.mamoe.mirai.event.Listener;
import net.mamoe.mirai.event.ListeningStatus;
import net.mamoe.mirai.event.SimpleListenerHost;
import net.mamoe.mirai.message.GroupMessageEvent;
import net.mamoe.mirai.message.data.At;
import org.jetbrains.annotations.NotNull;

public class MainListener extends SimpleListenerHost {
    @EventHandler(priority = Listener.EventPriority.HIGHEST)
    public ListeningStatus onGroupMessage(GroupMessageEvent event) throws IOException {
        String content = event.getMessage().contentToString();
        if (content.length() < MiraiMBot.yaml.getInt("message.max")) {
            if (content.startsWith(LogUtil.command)) {
                CommandManager.call(event.getMessage(), event.getSender());
                event.intercept();
            }
        }
     /*
        if ((content.length() >= MiraiMBot.yaml.getInt("message.max")) && (event.getSender().getPermission() == MemberPermission.MEMBER)) {
            if (!JSON.isValid(content) || !content.startsWith("<?xml")) {
                event.getGroup().sendMessage(new At(event.getSender()).plus(" 您的消息过长，正在转移～"));
                MiraiMBot.bot.recall(event.getMessage());
                event.getGroup().sendMessage("您的消息已经被转移到此：" + HasteUtils.paste(content));
            }
        }

      */
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
