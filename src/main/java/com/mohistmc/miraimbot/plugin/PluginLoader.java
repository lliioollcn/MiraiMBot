package com.mohistmc.miraimbot.plugin;

import com.google.common.collect.Sets;
import com.mohistmc.miraimbot.console.log4j.MiraiMBotLog;
import com.mohistmc.miraimbot.utils.JarUtils;
import com.mohistmc.yaml.file.YamlConfiguration;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Arrays;
import java.util.Set;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;

public class PluginLoader extends URLClassLoader {
    public static final PluginLoader INSTANCE = new PluginLoader();
    public static final Set<MohistPlugin> plugins = Sets.newHashSet();
    public static final Set<Plugin> plugin_map = Sets.newHashSet();

    public PluginLoader() {
        super(new URL[0], PluginClassLoader.INSTANCE);
    }

    public static void initPlugins() {
        Class<?>[] clazzs = PluginClassLoader.allPluginClasses.toArray(new Class<?>[0]);
        for (int i = 0; i < clazzs.length; i++)
            PluginLoader.initPlugin(clazzs[i]);
    }

    public static void initPlugin(Class<?> clazz) {
        if (clazz.getSuperclass() == MohistPlugin.class && clazz.getDeclaredAnnotation(Plugin.class) != null) {
            Plugin plugin = clazz.getAnnotation(Plugin.class);
            MiraiMBotLog.LOGGER.info("Loading plugin " + plugin.name() + " by " + (Arrays.toString(plugin.authors()))
                    .replace("[", "")
                    .replace("]", ""));
            try {
                plugins.add((MohistPlugin) clazz.newInstance());
                plugin_map.add(plugin);
            } catch (InstantiationException | IllegalAccessException e) {
                System.err.println("Loading plugin " + plugin.name() + " by " + (Arrays.toString(plugin.authors()))
                        .replace("[", "")
                        .replace("]", "") + " throw an error, it update to data?");
                e.printStackTrace();
            }
        }

    }

    public static void loadPlugins() {
        for (MohistPlugin plugin : plugins) {
            plugin.onLoad();
        }
    }

    public static void enablePlugins() {
        for (MohistPlugin plugin : plugins) {
            plugin.onEnable();
        }
    }

    public static void disablePlugins() {
        for (MohistPlugin plugin : plugins) {
            plugin.onDisable();
        }
    }


    public void loadPlugin(File file) throws IOException {
        String url = "jar:file:///" + file.getAbsolutePath() + "!/";
        this.addURL(new URL(url));
        MiraiMBotLog.Debug(url);
        JarFile jarFile = new JarFile(file, false);
        ZipEntry ze = jarFile.getEntry("plugin.yml");
        InputStream is = ze != null ? jarFile.getInputStream(ze) : null;
        if (is != null) {
            YamlConfiguration yml = YamlConfiguration.loadConfiguration(new InputStreamReader(is));
            String mainClass = yml.getString("main", null);
            if (mainClass != null) {

                PluginClassLoader.allPluginClasses.addAll(JarUtils.scanClasses(jarFile.entries(), PluginClassLoader.allPluginClasses, mainClass, this, true));
            } else {
                System.err.println("plugin.yml丢失main");
            }
        } else {
            System.err.println("插件丢失plugin.yml文件");
        }
    }
}
