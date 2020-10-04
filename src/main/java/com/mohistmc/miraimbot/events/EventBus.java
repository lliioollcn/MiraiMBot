package com.mohistmc.miraimbot.events;

import com.mohistmc.miraimbot.MiraiMBot;
import com.mohistmc.miraimbot.cmds.manager.CommandExecutor;
import com.mohistmc.miraimbot.console.log4j.MiraiMBotLog;
import com.mohistmc.miraimbot.plugin.PluginClassLoader;
import com.mohistmc.miraimbot.utils.JarUtils;

import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;
import java.util.Set;

import lombok.SneakyThrows;
import net.mamoe.mirai.event.Event;
import net.mamoe.mirai.event.EventKt;
import net.mamoe.mirai.event.Events;
import net.mamoe.mirai.event.SimpleListenerHost;

public class EventBus {

    @SneakyThrows
    public static void broadcast(Event event) {
        EventKt.broadcast(event);
    }

    @SneakyThrows
    public static void registers(Class<?>... clazzs) {
        for (Class<?> clazz : clazzs) {
            register(clazz);
        }
    }

    public static void registers(String pack) {
        try {
            long start = System.currentTimeMillis();
            int load = 0;
            Enumeration<URL> c = PluginClassLoader.INSTANCE.getResources(pack.replace(".", "/"));
            while (c.hasMoreElements()) {
                URL u = c.nextElement();
                MiraiMBotLog.LOGGER.info(u.getPath());
                Class<?> clazz = PluginClassLoader.INSTANCE.loadClass(u.getPath().replace("\\", "."));
                if (register(clazz)) {
                    load++;
                }
            }
            MiraiMBotLog.LOGGER.info("加载了 " + load + " 个监听器，耗时 " + (System.currentTimeMillis() - start) + "(ms).");
        } catch (IOException | ClassNotFoundException e) {
            MiraiMBotLog.LOGGER.info("[ERROR] 自动注册监听器失败，请手动注册");
            e.printStackTrace();
        }
    }

    @SneakyThrows
    public static void init() {
        long start = System.currentTimeMillis();
        int load = 0;
        Set<Class<?>> classes = JarUtils.getAllLoadClasses();
        for (Class<?> clazz : classes) {
            if (register(clazz)) {
                load++;
            }
        }
        MiraiMBotLog.LOGGER.info("加载了 " + load + " 个监听器，耗时 " + (System.currentTimeMillis() - start) + "(ms).");
    }

    @SneakyThrows
    public static boolean register(Class<?> clazz) {
        if (clazz.getAnnotation(Listener.class) != null && clazz.getSuperclass() == SimpleListenerHost.class) {
            Events.registerEvents(MiraiMBot.bot, (SimpleListenerHost) clazz.newInstance());
            return true;
        }
        return false;
    }
}
