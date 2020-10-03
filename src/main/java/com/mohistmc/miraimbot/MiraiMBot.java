package com.mohistmc.miraimbot;

import com.mohistmc.miraimbot.cmds.manager.CommandManager;
import com.mohistmc.miraimbot.listeners.MainListener;
import com.mohistmc.miraimbot.utils.JarUtils;
import com.mohistmc.miraimbot.utils.LogUtil;
import com.mohistmc.yaml.file.FileConfiguration;
import com.mohistmc.yaml.file.YamlConfiguration;
import net.mamoe.mirai.Bot;
import net.mamoe.mirai.BotFactoryJvm;
import net.mamoe.mirai.event.Events;
import net.mamoe.mirai.utils.BotConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MiraiMBot {

    public static File file;
    public static FileConfiguration yaml;
    public static Map<String, String> version = new ConcurrentHashMap<>();
    public static Bot bot;

    public static void main(String[] args) throws IOException {
        if (!LibCheck.hasQQAndroid()) {
            System.out.println("正在下载依赖： mirai-core-qqandroid-1.3.1");
            LibCheck.downloadFile();
        }
        file = new File("config/MiraiMBot.yml");
        yaml = YamlConfiguration.loadConfiguration(file);
        if (!file.exists()) {
            file.mkdir();
            yaml.set("version", 0.1);
            yaml.set("qq", 0L);
            yaml.set("password", "");
            yaml.save(file);
        }

        version.put("1.12.2", "1.12.2");
        version.put("1.16.3", "InternalTest");

        System.out.println(yaml.get("qq"));
        bot = BotFactoryJvm.newBot(Long.valueOf(yaml.getString("qq")).longValue(), yaml.getString("password"), new BotConfiguration() {
            {
                fileBasedDeviceInfo("deviceInfo.json");
            }
        });
        LogUtil.logger = bot.getLogger();
        JarUtils.scan("com.mohistmc.miraimbot");
        CommandManager.init();
        GitHubAuto.start();
        bot.login();
        Events.registerEvents(bot, new MainListener());

        bot.join();
    }
}          