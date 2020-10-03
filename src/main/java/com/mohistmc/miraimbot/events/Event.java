package com.mohistmc.miraimbot.events;

import com.google.common.collect.Maps;

import java.lang.reflect.Method;
import java.util.Map;

public class Event {
    private static Map<Method,Class<?>> handlers = Maps.newHashMap();

    public Map<Method,Class<?>> getHandler(){
        return handlers;
    }


    public static Map<Method,Class<?>> getHandlers(){
        return handlers;
    }
}
