package com.mohistmc.miraimbot.command;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.mohistmc.miraimbot.config.ConfigManager;
import com.mohistmc.miraimbot.plugin.MohistPlugin;
import com.mohistmc.miraimbot.plugin.PluginClassLoader;
import com.mohistmc.miraimbot.plugin.PluginLoader;
import com.mohistmc.miraimbot.utils.Utils;
import com.mohistmc.yaml.util.Charsets;
import lombok.SneakyThrows;
import net.mamoe.mirai.contact.User;
import net.mamoe.mirai.event.events.MessageEvent;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutorService;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;

public class CommandManager {

    private static final Logger log = LogManager.getLogger("CommandManager");
    private static final ConcurrentMap<String, CommandExecutor> executors = Maps.newConcurrentMap();
    private static final ExecutorService tasks = Utils.createThreadPool(1, 100, "Command-%d");

    @SneakyThrows
    public static void register(MohistPlugin plugin) {
        JarFile jarFile = plugin.getSourceFile();
        ZipEntry ze = jarFile.getEntry("commands.json");
        InputStream is = ze != null ? jarFile.getInputStream(ze) : null;
        if (is != null) {
            log.debug("从 {} 找到了commands.json", jarFile.getName());
            String jstr = IOUtils.toString(is, Charsets.UTF_8);
            log.debug("从 {} 读取到的commands.json: {}", jarFile.getName(), jstr);
            if (JSON.isValid(jstr)) {
                JSONArray ja = JSON.parseArray(jstr);
                for (JSONObject json : ja.toArray(new JSONObject[0])) {
                    Class<?> executorClass = PluginLoader.INSTANCE.loadClass(json.getString("class"));
                    if (executorClass.getSuperclass() != CommandExecutor.class) {
                        log.error("指令处理器没有继承CommandExecutor ({})", json.getString("class"));
                        return;
                    }
                    CommandExecutor executor = (CommandExecutor) executorClass.newInstance();
                    executor.label = json.getString("label");
                    executor.usage = json.getString("usage");
                    executor.description = json.getString("description");
                    executor.noshow = json.getJSONObject("noshow").getBooleanValue("enable");
                    executor.opCan = json.getJSONObject("noshow").getBooleanValue("opCan");
                    executor.onlyOp = json.getBooleanValue("onlyOp");
                    executor.permissionEnable = json.getJSONObject("permission").getBooleanValue("enable");
                    executor.permission = json.getJSONObject("permission").getString("permission");
                    register(executor, json.getJSONArray("alias").toArray(new String[0]));
                    log.info("注册了指令 {}", executor.label);
                }
            } else {
                log.error("插件 {} 损坏", plugin.getName());
            }
            is.close();
        } else {
            log.debug("从 {} 没有找到commands.json", jarFile.getName());
        }
    }

    public static void register(CommandExecutor executor, String... aliases) {
        executors.put(executor.getLabel(), executor);
        for (String alia : aliases) {
            executors.put(alia, executor);
        }
    }

    public static void registers(CommandExecutor... executors) {
        for (CommandExecutor executor : executors) register(executor);
    }

    public static void parseAndCall(MessageEvent event) {
        log.debug("处理并触发指令 {}", event.getMessage().contentToString());
        call(parse(event));
    }

    public static void call(CommandResult result) {
        if (executors.containsKey(result.getLabel())) {
            log.debug("指令 {} 存在", result.getLabel());
            tasks.execute(() -> executors.get(result.getLabel()).onCommand(result));
        } else {
            log.debug("指令 {} 不存在", result.getLabel());
            if (result.isGroup()) {
                result.getGroupOrNull().sendMessage(ConfigManager.getConfig().getString("path_command_unknown", "未知指令。请使用\"#help\"来获得帮助"));
            } else {
                Utils.sendMessage(result.getSender(), ConfigManager.getConfig().getString("path_command_unknown", "未知指令。请使用\"#help\"来获得帮助"));
            }
        }
    }

    public static CommandResult parse(MessageEvent event) {
        List<String> args = Lists.newArrayList();
        String label = "";
        String msg = event.getMessage().contentToString();
        String[] waitParse = msg.split(" ");
        label = waitParse[0];
        log.debug("转换指令 {}", label);
        args.addAll(Arrays.asList(waitParse).subList(1, waitParse.length));
        return CommandResult.builder()
                .args(args)
                .label(label)
                .source(event.getMessage())
                .sender(event.getSender())
                .bot(event.getBot())
                .build();
    }
}