package com.mohistmc.miraimbot.cmds.manager;

import com.mohistmc.miraimbot.MiraiMBot;
import com.mohistmc.miraimbot.utils.LogUtil;
import kotlin.coroutines.CoroutineContext;
import net.mamoe.mirai.Bot;
import net.mamoe.mirai.contact.Contact;
import net.mamoe.mirai.contact.User;
import net.mamoe.mirai.event.events.EventCancelledException;
import net.mamoe.mirai.message.MessageReceipt;
import net.mamoe.mirai.message.action.Nudge;
import net.mamoe.mirai.message.data.Message;
import org.jetbrains.annotations.NotNull;
import java.util.concurrent.*;

public class ConsoleSender extends User {
    public static final ConsoleSender INSTANCE = new ConsoleSender();

    @Override
    public long getId() {
        return 666666;
    }

    @NotNull
    @Override
    public String getNick() {
        return "ConsoleSender";
    }

    @NotNull
    @Override
    public Nudge nudge() {
        return MiraiMBot.bot.nudge();
    }

    @NotNull
    @Override
    public String toString() {
        return getNick();
    }

    @NotNull
    @Override
    public Bot getBot() {
        return MiraiMBot.bot;
    }

    @NotNull
    @Override
    public CoroutineContext getCoroutineContext() {
        return MiraiMBot.bot.getCoroutineContext();
    }

    @NotNull
    @Override
    public MessageReceipt<Contact> sendMessage(@NotNull Message message) throws EventCancelledException, IllegalStateException {
        LogUtil.getLogger().info(message.contentToString());
        return super.sendMessage(message);
    }

    @NotNull
    @Override
    public MessageReceipt<Contact> sendMessage(@NotNull String message) throws EventCancelledException, IllegalStateException {
        LogUtil.getLogger().info(message);
        return super.sendMessage(message);
    }

    @NotNull
    @Override
    public Future<MessageReceipt<Contact>> sendMessageAsync(@NotNull Message message) {
        LogUtil.getLogger().info(message.contentToString());
        return super.sendMessageAsync(message);
    }

    @NotNull
    @Override
    public Future<MessageReceipt<Contact>> sendMessageAsync(@NotNull String message) {
        LogUtil.getLogger().info(message);
        return super.sendMessageAsync(message);
    }

    @NotNull
    @Override
    public String getAvatarUrl() {
        return "";
    }
}
