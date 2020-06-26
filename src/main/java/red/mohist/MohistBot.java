package red.mohist;

import net.mamoe.mirai.console.plugins.PluginBase;
import net.mamoe.mirai.message.GroupMessageEvent;
import red.mohist.cmds.manager.CommandManager;
import red.mohist.utils.JarUtils;
import red.mohist.utils.LogUtil;

class MohistBot extends PluginBase {

    public void onEnable() {
        LogUtil.logger = getLogger();
        JarUtils.scanByPackage();
        CommandManager.init();
        this.getEventListener().subscribeAlways(GroupMessageEvent.class, (GroupMessageEvent event) -> {
            String content = event.getMessage().contentToString();
            if (content.startsWith(LogUtil.command)) {
                CommandManager.call(event.getMessage(), event.getSender());
            }
        });
        getLogger().info("插件启动完毕。");
    }

}          