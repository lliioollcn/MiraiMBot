package com.mohistmc.miraimbot.plugin;

import com.mohistmc.miraimbot.console.log4j.MiraiMBotLog;
import lombok.SneakyThrows;

import java.io.File;
import java.io.IOException;

public class PluginManager {
    @SneakyThrows
    public static void init() {
        File dir = new File("./plugins/");
        if (!dir.exists()) dir.mkdir();
        for (File plugin : dir.listFiles()) {
            if (plugin.getName().endsWith(".jar") || plugin.getName().endsWith(".zip")) {
                try {
                    PluginLoader.INSTANCE.loadPlugin(plugin);
                } catch (IOException e) {
                    MiraiMBotLog.LOGGER.info("加载失败: " + plugin.getAbsolutePath());
                    e.printStackTrace();
                }
            }
        }
        PluginLoader.initPlugins();
        PluginLoader.loadPlugins();
    }
}
