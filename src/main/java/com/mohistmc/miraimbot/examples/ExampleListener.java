package com.mohistmc.miraimbot.examples;


import com.mohistmc.miraimbot.events.Listener;
import net.mamoe.mirai.event.EventHandler;
import net.mamoe.mirai.event.SimpleListenerHost;
import net.mamoe.mirai.message.GroupMessageEvent;

@Listener
public class ExampleListener extends SimpleListenerHost {

    @EventHandler
    public void onGroup(GroupMessageEvent event) {
        // TODO:
    }
}
