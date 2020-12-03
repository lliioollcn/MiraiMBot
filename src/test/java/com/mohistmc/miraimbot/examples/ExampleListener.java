package com.mohistmc.miraimbot.examples;


import net.mamoe.mirai.event.EventHandler;
import net.mamoe.mirai.event.ListeningStatus;
import net.mamoe.mirai.event.SimpleListenerHost;
import net.mamoe.mirai.message.GroupMessageEvent;

public class ExampleListener extends SimpleListenerHost {

    @EventHandler
    public ListeningStatus onGroup(GroupMessageEvent event) {
        // TODO:
        return ListeningStatus.LISTENING;
    }
}
