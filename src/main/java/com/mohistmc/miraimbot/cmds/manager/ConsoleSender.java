package com.mohistmc.miraimbot.cmds.manager;

import com.mohistmc.miraimbot.MiraiMBot;
import com.mohistmc.miraimbot.console.log4j.MiraiMBotLog;
import com.mohistmc.miraimbot.utils.LogUtil;
import java.util.concurrent.Future;
import kotlin.coroutines.CoroutineContext;
import net.mamoe.mirai.Bot;
import net.mamoe.mirai.contact.Contact;
import net.mamoe.mirai.contact.User;
import net.mamoe.mirai.event.events.EventCancelledException;
import net.mamoe.mirai.message.MessageReceipt;
import net.mamoe.mirai.message.action.Nudge;
import net.mamoe.mirai.message.data.Message;
import net.mamoe.mirai.message.data.MessageUtils;
import org.jetbrains.annotations.NotNull;

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
        MiraiMBotLog.LOGGER.info(message.contentToString());
        // return super.sendMessage(message);
        return null;
    }

    @NotNull
    @Override
    public MessageReceipt<Contact> sendMessage(@NotNull String message) throws EventCancelledException, IllegalStateException {
        return sendMessage(MessageUtils.newChain(message));
    }

    @NotNull
    @Override
    public Future<MessageReceipt<Contact>> sendMessageAsync(@NotNull Message message) {
        MiraiMBotLog.LOGGER.info(message.contentToString());
        // return sendMessage(MessageUtils.newChain(message));
        return null;
    }

    @NotNull
    @Override
    public Future<MessageReceipt<Contact>> sendMessageAsync(@NotNull String message) {
        return sendMessageAsync(MessageUtils.newChain(message));
    }

    @NotNull
    @Override
    public String getAvatarUrl() {
        return "";
    }
}
