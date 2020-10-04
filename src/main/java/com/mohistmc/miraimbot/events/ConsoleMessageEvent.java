package com.mohistmc.miraimbot.events;

import com.mohistmc.miraimbot.MiraiMBot;
import com.mohistmc.miraimbot.cmds.manager.ConsoleSender;
import kotlin.coroutines.CoroutineContext;
import net.mamoe.mirai.Bot;
import net.mamoe.mirai.contact.Contact;
import net.mamoe.mirai.contact.User;
import net.mamoe.mirai.message.MessageEvent;
import net.mamoe.mirai.message.data.MessageChain;
import org.jetbrains.annotations.NotNull;

public class ConsoleMessageEvent extends MessageEvent {
    private MessageChain message;

    public ConsoleMessageEvent(MessageChain msg) {
        this.message = msg;
    }

    @NotNull
    @Override
    public Bot getBot() {
        return MiraiMBot.bot;
    }

    @NotNull
    @Override
    public MessageChain getMessage() {
        return this.message;
    }

    @NotNull
    @Override
    public User getSender() {
        return ConsoleSender.INSTANCE;
    }

    @NotNull
    @Override
    public String getSenderName() {
        return ConsoleSender.INSTANCE.getNick();
    }

    @Override
    public Contact getSubject() {
        return new Contact() {
            @NotNull
            @Override
            public Bot getBot() {
                return MiraiMBot.bot;
            }

            @Override
            public long getId() {
                return ConsoleSender.INSTANCE.getId();
            }

            @NotNull
            @Override
            public String toString() {
                return "Console";
            }

            @NotNull
            @Override
            public CoroutineContext getCoroutineContext() {
                return getBot().getCoroutineContext();
            }
        };
    }

    @Override
    public int getTime() {
        return (int) (System.currentTimeMillis() / 1000);
    }
}
