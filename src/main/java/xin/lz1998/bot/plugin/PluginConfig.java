package xin.lz1998.bot.plugin;

import java.util.ArrayList;
import java.util.List;

import xin.lz1998.bot.plugin.Mohist.Log;
import xin.lz1998.bot.plugin.Mohist.Update;
import xin.lz1998.bot.plugin.status.StatusPlugin;
import xin.lz1998.cq.robot.CQPlugin;

public class PluginConfig {
    public static List<CQPlugin> pluginList = new ArrayList<>();

    static {
        pluginList.add(new Update());
        pluginList.add(new Log());
        pluginList.add(new StatusPlugin()); // 状态监控插件
        //pluginList.add(new LogPlugin()); // 日志插件
    }

}
