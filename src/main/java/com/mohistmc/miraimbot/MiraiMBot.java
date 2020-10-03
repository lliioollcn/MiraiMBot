package com.mohistmc.miraimbot;

import com.mohistmc.miraimbot.cmds.LoginCommand;
import com.mohistmc.miraimbot.cmds.manager.CommandManager;
import com.mohistmc.miraimbot.cmds.manager.ConsoleSender;
import com.mohistmc.miraimbot.events.EventBus;
import com.mohistmc.miraimbot.listeners.MainListener;
import com.mohistmc.miraimbot.plugin.PluginLoader;
import com.mohistmc.miraimbot.plugin.PluginManager;
import com.mohistmc.miraimbot.utils.JarUtils;
import com.mohistmc.miraimbot.utils.LogUtil;
import com.mohistmc.yaml.file.FileConfiguration;
import com.mohistmc.yaml.file.YamlConfiguration;
import java.util.Scanner;
import net.mamoe.mirai.Bot;
import net.mamoe.mirai.BotFactoryJvm;
import net.mamoe.mirai.event.Events;
import net.mamoe.mirai.message.data.MessageUtils;
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

        new Thread(() -> {
            while (true) {
                Scanner scanner = new Scanner(System.in);
                if (scanner.hasNext()) {
                    String cmd = scanner.nextLine();
                    System.out.println("console executor a command: " + cmd);
                    CommandManager.call(MessageUtils.newChain(cmd), ConsoleSender.INSTANCE);
                }
            }
        }).start();
        JarUtils.scan("com.mohistmc.miraimbot");
        CommandManager.register(new LoginCommand());
        PluginManager.init();
        long qq = yaml.getLong("qq");
        String pass = yaml.getString("pass");

        if (qq == 0L || pass.equalsIgnoreCase("")) {
            System.out.println("监测到您没有设置qq号或密码，因此需要使用指令\"login qq 密码\"来进行登陆");
        } else {
            bot = BotFactoryJvm.newBot(Long.valueOf(yaml.getString("qq")).longValue(), yaml.getString("password"), new BotConfiguration() {
                {
                    fileBasedDeviceInfo("deviceInfo.json");
                }
            });
        }
        LogUtil.logger = bot.getLogger();
        bot.login();
        EventBus.init();
        Events.registerEvents(bot, new MainListener());
        PluginLoader.enablePlugins();
        CommandManager.init();
        bot.join();
    }
}          