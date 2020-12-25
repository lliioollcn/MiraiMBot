package com.mohistmc.miraimbot.events;

import com.mohistmc.miraimbot.MiraiMBot;
import com.mohistmc.miraimbot.cmds.manager.ConsoleSender;
import net.mamoe.mirai.Bot;
import net.mamoe.mirai.contact.UserOrBot;
import net.mamoe.mirai.event.AbstractEvent;
import net.mamoe.mirai.message.data.MessageChain;
import org.jetbrains.annotations.NotNull;

public class ConsoleMessageEvent extends AbstractEvent {
    private final MessageChain message;

    public ConsoleMessageEvent(MessageChain msg) {
        this.message = msg;
    }

    @NotNull
    public Bot getBot() {
        return MiraiMBot.bot;
    }

    @NotNull
    public MessageChain getMessage() {
        return this.message;
    }

    @NotNull
    public UserOrBot getSender() {
        return ConsoleSender.INSTANCE;
    }

    @NotNull
    public String getSenderName() {
        return "ConsoleSender";
    }



    @Override
    public boolean isIntercepted() {
        return false;
    }

    @Override
    public void intercept() {}
}
