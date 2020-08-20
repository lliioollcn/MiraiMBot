package red.mohist;

import net.mamoe.mirai.console.plugin.jvm.JavaPlugin;
import net.mamoe.mirai.event.EventHandler;
import net.mamoe.mirai.event.Events;
import net.mamoe.mirai.event.SimpleListenerHost;
import net.mamoe.mirai.message.GroupMessageEvent;
import red.mohist.cmds.manager.CommandManager;
import red.mohist.utils.JarUtils;
import red.mohist.utils.LogUtil;

class MohistBot extends JavaPlugin {

    public void onEnable() {
        LogUtil.logger = getLogger();
        JarUtils.scanByPackage();
        CommandManager.init();

        Events.registerEvents(this, new SimpleListenerHost() {
            @EventHandler
            public void onGroupMessage(GroupMessageEvent event) {
                String content = event.getMessage().contentToString();
                if (content.startsWith(LogUtil.command)) {
                    CommandManager.call(event.getMessage(), event.getSender());
                }
            }
        });
        getLogger().info("插件启动完毕。");
    }

}          