package com.mohistmc.miraimbot.command;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.mohistmc.miraimbot.plugin.MohistPlugin;
import com.mohistmc.miraimbot.plugin.PluginClassLoader;
import com.mohistmc.miraimbot.plugin.PluginLoader;
import com.mohistmc.yaml.util.Charsets;
import lombok.SneakyThrows;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;

public class CommandManager {

    private static final Logger log = LogManager.getLogger("CommandManager");
    private static final ConcurrentMap<String, CommandExecutor> executors = Maps.newConcurrentMap();

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
                    executors.put(json.getString("label"), executor);
                    for (String alia : json.getJSONArray("alias").toArray(new String[0])) {
                        executors.put(alia, executor);
                    }
                    log.info("注册了指令 {}", executor.label);
                }
            } else {
                log.error("插件 {} 损坏", plugin.getName());
            }
            is.close();
        }else {
            log.debug("从 {} 没有找到commands.json", jarFile.getName());
        }
    }
}
