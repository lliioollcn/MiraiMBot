package com.mohistmc.miraimbot.events;

import com.google.common.collect.Maps;
import com.mohistmc.miraimbot.cmds.manager.ConsoleSender;
import java.lang.reflect.Method;
import java.util.Map;
import lombok.Getter;

public class ConsoleMessageEvent extends Event {
    private static Map<Method, Class<?>> handlers = Maps.newHashMap();
    @Getter
    public final ConsoleSender sender;
    @Getter
    public final String msg;

    public ConsoleMessageEvent(ConsoleSender instance, String cmd) {
        this.sender = instance;
        this.msg = cmd;
    }

    public Map<Method, Class<?>> getHandler() {
        return handlers;
    }


    public static Map<Method, Class<?>> getHandlers() {
        return handlers;
    }
}
