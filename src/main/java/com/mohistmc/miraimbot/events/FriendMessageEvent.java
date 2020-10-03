package com.mohistmc.miraimbot.events;

import com.google.common.collect.Maps;
import java.lang.reflect.Method;
import java.util.Map;
import lombok.Getter;

public class FriendMessageEvent extends Event {
    private static Map<Method, Class<?>> handlers = Maps.newHashMap();
    @Getter
    public final net.mamoe.mirai.message.FriendMessageEvent source;

    public FriendMessageEvent(net.mamoe.mirai.message.FriendMessageEvent event) {
        this.source = event;
    }

    public Map<Method, Class<?>> getHandler() {
        return handlers;
    }


    public static Map<Method, Class<?>> getHandlers() {
        return handlers;
    }
}
