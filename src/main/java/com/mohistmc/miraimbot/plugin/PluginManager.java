package com.mohistmc.miraimbot.plugin;

import java.io.File;
import java.net.MalformedURLException;

public class PluginManager {
    public static void init() {
        File dir = new File("./plugins/");
        if (!dir.exists()) dir.mkdir();
        for (File plugin : dir.listFiles()) {
            if (plugin.getName().endsWith(".jar") || plugin.getName().endsWith(".zip")) {
                try {
                    PluginLoader.INSTANCE.loadPlugin(plugin.getAbsolutePath());
                } catch (MalformedURLException e) {
                    System.out.println("加载失败: " + plugin.getAbsolutePath());
                    e.printStackTrace();
                }
            }
        }
        PluginLoader.initPlugins();
        PluginLoader.loadPlugins();
    }
}
