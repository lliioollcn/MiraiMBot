package com.mohistmc.miraimbot.plugin;

import com.google.common.collect.Sets;
import com.mohistmc.miraimbot.utils.JarUtils;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Arrays;
import java.util.Set;

public class PluginLoader extends URLClassLoader {
    public static final PluginLoader INSTANCE = new PluginLoader();
    public static final Set<MohistPlugin> plugins = Sets.newHashSet();

    public PluginLoader() {
        super(new URL[0], new PluginClassLoader());
    }

    public static void initPlugins() {
        Set<Class<?>> classes = JarUtils.getAllLoadClasses();
        for (Class<?> clazz : classes) {
            if (clazz.getSuperclass() == MohistPlugin.class && clazz.getDeclaredAnnotation(Plugin.class) != null) {
                Plugin plugin = clazz.getAnnotation(Plugin.class);
                System.out.println("Loading plugin " + plugin.name() + " by " + (Arrays.toString(plugin.authors()))
                        .replace("[", "")
                        .replace("]", ""));
                try {
                    plugins.add((MohistPlugin) clazz.newInstance());
                } catch (InstantiationException | IllegalAccessException e) {
                    System.err.println("Loading plugin " + plugin.name() + " by " + (Arrays.toString(plugin.authors()))
                            .replace("[", "")
                            .replace("]", "") + " throw an error, it update to data?");
                    e.printStackTrace();
                }
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


    public void loadPlugin(String pluginPath) throws MalformedURLException {
        this.addURL(new URL("jar:file:///" + pluginPath + "!/"));
    }
}
