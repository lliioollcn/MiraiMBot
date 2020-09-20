package com.mohistmc;

import com.mohistmc.utils.LogUtil;
import com.mohistmc.utils.UpdateUtils;
import com.mohistmc.yaml.file.FileConfiguration;
import com.mohistmc.yaml.file.YamlConfiguration;
import kotlin.coroutines.CoroutineContext;
import net.mamoe.mirai.Bot;
import net.mamoe.mirai.BotFactoryJvm;
import net.mamoe.mirai.event.EventHandler;
import net.mamoe.mirai.event.Events;
import net.mamoe.mirai.event.ListeningStatus;
import net.mamoe.mirai.event.SimpleListenerHost;
import net.mamoe.mirai.message.GroupMessageEvent;
import net.mamoe.mirai.utils.BotConfiguration;
import org.jetbrains.annotations.NotNull;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MiraiMBot {

    public static File file;
    public static FileConfiguration yaml;
    public static Map<String, String> version = new ConcurrentHashMap<>();

    public static void main(String[] args) throws IOException {
        if (!LibCheck.hasQQAndroid()) {
            System.out.println("正在下载依赖： mirai-core-qqandroid-1.3.0");
            LibCheck.downloadFile();
        }
        file = new File("config/MiraiMBot.yml");
        yaml = YamlConfiguration.loadConfiguration(file);
        if (!file.exists()) {
            file.mkdir();
            yaml.set("version", 0.1);
            yaml.set("qq", 0L);
            yaml.set("password", "");
        }
        yaml.save(file);

        version.put("1.12.2", "1.12.2");
        version.put("1.16.3", "InternalTest");

        Bot bot = login(yaml.getLong("qq"), yaml.getString("password"));
        LogUtil.logger = bot.getLogger();

        bot.login();
        Events.registerEvents(bot, new SimpleListenerHost() {
            @EventHandler
            public ListeningStatus onGroupMessage(GroupMessageEvent event) {
                String content = event.getMessage().contentToString();
                if (version.containsKey(content)) {
                    try {
                        event.getSender().getGroup().sendMessage(UpdateUtils.info(version.get(content)));
                    } catch (Exception e) {
                        e.printStackTrace();
                        event.getSender().getGroup().sendMessage("======更新检测======\n检测失败,内部错误哦~");
                    }
                }
                return ListeningStatus.LISTENING;
            }

            @Override
            public void handleException(@NotNull CoroutineContext context, @NotNull Throwable exception) {
                throw new RuntimeException("在事件处理中发生异常", exception);
            }
        });

        bot.join();
    }

    public static Bot login(Long qq, String password){
        return BotFactoryJvm.newBot(0, "", new BotConfiguration() {
            {
                fileBasedDeviceInfo("deviceInfo.json");
            }
        });
    }
}          