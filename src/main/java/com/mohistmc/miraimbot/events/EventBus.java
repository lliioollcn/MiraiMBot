package com.mohistmc.miraimbot.events;

import com.mohistmc.miraimbot.cmds.manager.CommandExecutor;
import com.mohistmc.miraimbot.plugin.PluginClassLoader;
import com.mohistmc.miraimbot.utils.JarUtils;

import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Map;
import java.util.Set;

import lombok.SneakyThrows;

public class EventBus {

    @SneakyThrows
    public static void broadcast(Event event) {
        for (Method method : event.getHandler().keySet()) {
            if (method.getParameterCount() == 1) {
                method.invoke(event.getHandler().get(method).getDeclaredConstructor().newInstance(), event);
            }
        }
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
                System.out.println(u.getPath());
                Class<?> clazz = PluginClassLoader.INSTANCE.loadClass(u.getPath().replace("\\", "."));
                if (Arrays.asList(clazz.getInterfaces()).contains(CommandExecutor.class)) {
                    register(clazz);
                    load++;
                }
            }
            System.out.println("加载了 " + load + " 个监听器，耗时 " + (System.currentTimeMillis() - start) + "(ms).");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("[ERROR] 自动注册监听器失败，请手动注册");
            e.printStackTrace();
        }
    }

    @SneakyThrows
    public static void init() {
        long start = System.currentTimeMillis();
        int load = 0;
        Set<Class<?>> classes = JarUtils.getAllLoadClasses();
        for (Class<?> clazz : classes) {
            register(clazz);
            load++;
        }
        System.out.println("加载了 " + load + " 个监听器，耗时 " + (System.currentTimeMillis() - start) + "(ms).");
    }

    @SneakyThrows
    public static void register(Class<?> clazz) {
        if (clazz.getAnnotation(Listener.class) != null) {
            for (Method method : clazz.getDeclaredMethods()) {
                if (method.getDeclaredAnnotation(MohistHandler.class) != null && method.getParameterCount() == 1 && method.getParameterTypes()[0].getSuperclass() == Event.class) {
                    Class<?> c = method.getParameterTypes()[0];
                    Map<Method, Class<?>> handlers = (Map<Method, Class<?>>) c.getMethod("getHandlers").invoke(null);
                    handlers.put(method, clazz);
                    System.out.println("加载监听器 " + clazz.getName() + " (" + method.getName() + ")");
                }
            }
        }
    }
}
