package com.mohistmc.miraimbot;

import com.mohistmc.miraimbot.cmds.CmdListCommand;
import com.mohistmc.miraimbot.cmds.LoginCommand;
import com.mohistmc.miraimbot.cmds.PluginCommand;
import com.mohistmc.miraimbot.cmds.manager.CommandManager;
import com.mohistmc.miraimbot.cmds.manager.ConsoleSender;
import com.mohistmc.miraimbot.console.log4j.MiraiMBotLog;
import com.mohistmc.miraimbot.events.ConsoleMessageEvent;
import com.mohistmc.miraimbot.listeners.MainListener;
import com.mohistmc.miraimbot.plugin.PluginLoader;
import com.mohistmc.miraimbot.plugin.PluginManager;
import com.mohistmc.miraimbot.utils.JarUtils;
import com.mohistmc.miraimbot.utils.Utils;
import com.mohistmc.yaml.file.FileConfiguration;
import com.mohistmc.yaml.file.YamlConfiguration;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import net.mamoe.mirai.Bot;
import net.mamoe.mirai.BotFactoryJvm;
import net.mamoe.mirai.event.EventKt;
import net.mamoe.mirai.event.Events;
import net.mamoe.mirai.message.data.MessageChain;
import net.mamoe.mirai.message.data.MessageUtils;

public class MiraiMBot {

    public static File file;
    public static FileConfiguration yaml;
    public static Bot bot;

    public static void main(String[] args) throws IOException {
        if (System.getProperty("log4j.configurationFile") == null) {
            System.setProperty("log4j.configurationFile", "log4j2.xml");
        }
        if (!LibCheck.hasQQAndroid()) {
            MiraiMBotLog.LOGGER.info("正在下载依赖： mirai-core-qqandroid-1.3.1");
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

        CommandManager.register(new LoginCommand());
        new Thread(() -> {
            while (true) {
                Scanner scanner = new Scanner(System.in);
                if (scanner.hasNext()) {
                    String cmd = scanner.next();
                    MiraiMBotLog.LOGGER.info("console executor a command: " + cmd);
                    MessageChain msg = MessageUtils.newChain(cmd);
                    if (cmd.startsWith("login")) {
                        CommandManager.call(msg, ConsoleSender.INSTANCE);
                    }
                    EventKt.broadcast(new ConsoleMessageEvent(msg));
                }
            }
        }).start();
        long qq = yaml.getLong("qq");
        String pass = yaml.getString("password");

        if (qq == 0L || pass.equalsIgnoreCase("")) {
            MiraiMBotLog.LOGGER.info("检测到您没有设置qq号或密码，因此需要使用指令\"login qq 密码\"来进行登陆");
            while (bot == null) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } else {
            bot = BotFactoryJvm.newBot(Long.valueOf(yaml.getString("qq")).longValue(), yaml.getString("password"), Utils.defaultConfig());
        }
        JarUtils.scan("com.mohistmc.miraimbot.cmds");
        JarUtils.scan("com.mohistmc.miraimbot.listeners");
        CommandManager.register(new CmdListCommand());
        CommandManager.register(new PluginCommand());
        PluginManager.init();
        bot.login();
        Events.registerEvents(bot, new MainListener());
        PluginLoader.enablePlugins();
        CommandManager.init();
        bot.join();
    }

    public static void saveYaml(FileConfiguration yaml, File file) {
        try {
            yaml.save(file);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}          
