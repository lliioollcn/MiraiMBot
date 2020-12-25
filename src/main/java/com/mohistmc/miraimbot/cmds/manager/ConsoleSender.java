package com.mohistmc.miraimbot.cmds.manager;

import com.mohistmc.miraimbot.MiraiMBot;
import com.mohistmc.miraimbot.console.log4j.MiraiMBotLog;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import net.mamoe.mirai.Bot;
import net.mamoe.mirai.contact.Contact;
import net.mamoe.mirai.contact.User;
import net.mamoe.mirai.contact.UserOrBot;
import net.mamoe.mirai.event.events.EventCancelledException;
import net.mamoe.mirai.message.MessageReceipt;
import net.mamoe.mirai.message.action.Nudge;
import net.mamoe.mirai.message.action.UserNudge;
import net.mamoe.mirai.message.data.Image;
import net.mamoe.mirai.message.data.Message;
import net.mamoe.mirai.message.data.MessageUtils;
import net.mamoe.mirai.message.data.PlainText;
import net.mamoe.mirai.utils.ExternalImage;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ConsoleSender implements UserOrBot {
    public static final ConsoleSender INSTANCE = new ConsoleSender();

    @Override
    public long getId() {
        return 666666;
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
    public MessageReceipt<Contact> sendMessage(@NotNull Message message) throws EventCancelledException, IllegalStateException {
        MiraiMBotLog.LOGGER.info(message.contentToString());
        // return super.sendMessage(message);
        return null;
    }

    @NotNull
    public MessageReceipt<Contact> sendMessage(@NotNull String message) throws EventCancelledException, IllegalStateException {
        return sendMessage(MessageUtils.newChain(new PlainText(message)));
    }


    @NotNull
    @Override
    public String getAvatarUrl() {
        return "";
    }


    @NotNull
    @Override
    public UserNudge nudge() {
        return null;
    }

}
