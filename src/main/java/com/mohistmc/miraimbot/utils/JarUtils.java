package com.mohistmc.miraimbot.utils;


import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import lombok.Getter;
import lombok.SneakyThrows;

import java.io.File;
import java.net.URL;
import java.util.Collection;
import java.util.Enumeration;
import java.util.List;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class JarUtils {

    @Getter
    public static final Set<Class<?>> allLoadClasses = Sets.newHashSet();

    /**
     * 扫描全部类
     *
     * @return
     */
    @SneakyThrows
    public static Collection<Class<?>> scan(String pack) {
        Enumeration<URL> us = JarUtils.class.getClassLoader().getResources(pack.replace(".", "/"));
        Collection<Class<?>> classes = Lists.newArrayList();
        while (us.hasMoreElements()) {
            URL u = us.nextElement();
            if (u.getProtocol().equalsIgnoreCase("jar")) {
                String path = JarUtils.class.getProtectionDomain().getCodeSource().getLocation().getFile();// 获得当前jar包位置
                JarFile jarFile = new JarFile(path, false);
                return scanClasses(jarFile.entries(), allLoadClasses, pack);
            } else if (u.getProtocol().equalsIgnoreCase("file")) {
                return scanByFile(pack, u.getPath());
            }
        }
        return classes;
    }

    private static Collection<Class<?>> scanClasses(Enumeration<JarEntry> entries, Collection<Class<?>> classes, String pack) {
        while (entries.hasMoreElements()) {
            JarEntry jarEntry = entries.nextElement();
            String name = jarEntry.getName();
            if (name.endsWith(".class")) {
                String className = name.replaceAll("/", ".").replace(".class", "");
                if (className.startsWith(pack)) {
                    try {
                        if (LogUtil.isDebug()) LogUtil.getLogger().info("加载类: " + className);
                        classes.add(Class.forName(className));
                    } catch (ClassNotFoundException e) {
                        LogUtil.getLogger().error("加载失败的类: " + className);
                    }
                }


            }
        }
        return classes;
    }

    public static List<Class<?>> scanByFile(String pack, String path) {
        List<Class<?>> clazzs = Lists.newArrayList();
        File dir = new File(path);
        if (dir.exists() && dir.isDirectory()) {
            clazzs.addAll(scanByFile(pack, dir));
        }
        return clazzs;
    }

    @SneakyThrows
    private static List<Class<?>> scanByFile(String dir, File file) {
        List<Class<?>> clazzs = Lists.newArrayList();
        if (file.exists() && file.isDirectory()) {
            for (File f : file.listFiles()) {
                if (f.isDirectory()) {
                    scanByFile(dir + f.getName(), f);
                }
                String className = dir + "." + f.getName().replace(".class", "");
                if (LogUtil.isDebug()) LogUtil.getLogger().info("加载类: " + className);
                clazzs.add(Class.forName(className));
            }
        }
        return clazzs;
    }
}
