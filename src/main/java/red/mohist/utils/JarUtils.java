package red.mohist.utils;


import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import lombok.Getter;
import lombok.SneakyThrows;

import java.util.Collection;
import java.util.Enumeration;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class JarUtils {

    /**
     * 扫描全部类
     *
     * @return
     */
    @SneakyThrows
    public static Collection<Class<?>> scanByPackage() {
        Collection<Class<?>> classes = Lists.newArrayList();
        String path = JarUtils.class.getProtectionDomain().getCodeSource().getLocation().getFile();// 获得当前jar包位置
        JarFile jarFile = new JarFile(path, false);
        scanClasses(jarFile.entries(), allLoadClasses);
        return classes;
    }

    @Getter
    private static final Set<Class<?>> allLoadClasses = Sets.newHashSet();

    private static Collection<Class<?>> scanClasses(Enumeration<JarEntry> entries, Collection<Class<?>> classes) {
        Collection<String> loadClasses = Lists.newArrayList();
        while (entries.hasMoreElements()) {
            JarEntry jarEntry = entries.nextElement();
            String name = jarEntry.getName();
            if (name.endsWith(".class")) {
                String className = name.replaceAll("/", ".").replace(".class", "");
                if (
                        className.startsWith("com.alibaba")
                                || className.startsWith("com.google")
                                || className.startsWith("org.joda")
                                || className.startsWith("org.apache")
                                || className.startsWith("org.slf4j")
                                || className.startsWith("net.mamoe.mirai")
                                || className.startsWith("kotlin")
                                || className.startsWith("kotlinx")
                                || className.startsWith("java")
                                || className.startsWith("javax")
                                || className.startsWith("org.jetbrains")
                                || className.startsWith("lombok")
                                || className.startsWith("module-info")
                                || className.startsWith("META-INF")
                ) {
                    continue;
                }

                try {
                    if (LogUtil.isDebug()) LogUtil.getLogger().info("加载类: " + className);
                    classes.add(Class.forName(className));
                } catch (ClassNotFoundException e) {
                    LogUtil.getLogger().error("加载失败的类: " + className);
                }
                loadClasses.add(className);
            }
        }
        return classes;
    }
}
