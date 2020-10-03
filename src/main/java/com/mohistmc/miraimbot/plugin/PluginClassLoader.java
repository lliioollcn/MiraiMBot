package com.mohistmc.miraimbot.plugin;

import com.google.common.collect.Sets;
import java.util.Set;
import lombok.Getter;

public class PluginClassLoader extends ClassLoader {
    public static final PluginClassLoader INSTANCE = new PluginClassLoader();
    @Getter
    public static final Set<Class<?>> allPluginClasses = Sets.newHashSet();

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        System.out.println("[DEBUG] loading class " + name);
        Class<?> clazz = super.loadClass(name);
        PluginClassLoader.allPluginClasses.add(clazz);
        return clazz;
    }

    @Override
    public Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        System.out.println("[DEBUG] loading class " + name + " @" + resolve);
        Class<?> clazz = super.loadClass(name, resolve);
        PluginClassLoader.allPluginClasses.add(clazz);
        return clazz;
    }

    @Override
    public Class<?> findClass(String name) throws ClassNotFoundException {
        System.out.println("[DEBUG] finding class " + name);
        Class<?> clazz = super.findClass(name);
        PluginClassLoader.allPluginClasses.add(clazz);
        return clazz;
    }
}