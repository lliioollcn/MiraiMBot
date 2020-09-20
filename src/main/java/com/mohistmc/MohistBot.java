package com.mohistmc;

import com.mohistmc.utils.LogUtil;
import com.mohistmc.utils.UpdateUtils;
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

public class MohistBot {

    public static void main(String[] args) {
        Bot bot = BotFactoryJvm.newBot(2703566153L, "WOHENAINI940.0", new BotConfiguration() {
            {
                fileBasedDeviceInfo("deviceInfo.json");
            }
        });
        LogUtil.logger = bot.getLogger();

        bot.login();
        Events.registerEvents(bot, new SimpleListenerHost() {
            @EventHandler
            public ListeningStatus onGroupMessage(GroupMessageEvent event) {
                String content = event.getMessage().contentToString();
                if (content.equals("1.12.2")) {
                    try {
                        event.getSender().getGroup().sendMessage(UpdateUtils.info("1.12.2"));
                    } catch (Exception e) {
                        e.printStackTrace();
                        event.getSender().getGroup().sendMessage("======更新检测======\n检测失败,内部错误哦~");
                    }
                }
                if (content.equals("1.16.3")) {
                    try {
                        event.getSender().getGroup().sendMessage(UpdateUtils.info("InternalTest"));
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
}          