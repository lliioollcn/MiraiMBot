package com.mohistmc.miraimbot.plugin;

import com.mohistmc.miraimbot.utils.JarUtils;

public class PluginClassLoader extends ClassLoader {
    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        System.out.println("[DEBUG] loading class " + name);
        Class<?> clazz = super.loadClass(name);
        JarUtils.allLoadClasses.add(clazz);
        return clazz;
    }

    @Override
    protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        System.out.println("[DEBUG] loading class " + name + " @" + resolve);
        Class<?> clazz = super.loadClass(name, resolve);
        JarUtils.allLoadClasses.add(clazz);
        return clazz;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        System.out.println("[DEBUG] finding class " + name);
        Class<?> clazz = super.findClass(name);
        JarUtils.allLoadClasses.add(clazz);
        return clazz;
    }


}
