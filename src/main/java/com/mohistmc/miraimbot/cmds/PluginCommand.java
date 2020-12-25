package com.mohistmc.miraimbot.cmds;

import com.mohistmc.miraimbot.cmds.manager.CommandExecutor;
import com.mohistmc.miraimbot.cmds.manager.CommandResult;
import com.mohistmc.miraimbot.annotations.Command;
import com.mohistmc.miraimbot.annotations.Plugin;
import com.mohistmc.miraimbot.plugin.PluginLoader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Command(name = "plugin", description = "查看插件列表及信息", alias = {"pl", "插件"}, usage = "#plugin [pluginName]", show = false)
public class PluginCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandResult result) {
        StringBuilder msg = new StringBuilder();
        Set<Plugin> plugin_map = PluginLoader.plugin_map;
        if (result.getArgs().size() == 1) {
            msg.append("======插件信息======").append("\n");
            for (Plugin executor : plugin_map) {
                if (executor.value().equals(result.getArgs().get(0))) {
                    msg.append("名字: ").append(executor.value()).append("\n");
                    msg.append("版本: ").append(executor.version()).append("\n");
                    List<String> authors = new ArrayList<>();
                    Collections.addAll(authors, executor.authors());
                    msg.append("作者: ").append(authors.toString()).append("\n");
                    msg.append("简介: ").append(executor.description()).append("\n");
                }
            }
        } else {
            msg.append("======插件列表(").append(plugin_map.size()).append(")======").append("\n");
            List<String> pl = plugin_map.stream().map(Plugin::value).collect(Collectors.toList());
            msg.append(pl.toString());
        }
        result.sendMessage(msg.toString());
        return true;
    }
}
