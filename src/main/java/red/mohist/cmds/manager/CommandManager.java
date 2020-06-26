package red.mohist.cmds.manager;

import com.google.common.collect.Maps;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.SneakyThrows;
import net.mamoe.mirai.contact.Member;
import net.mamoe.mirai.message.data.*;
import red.mohist.cmds.manager.annotations.Command;
import red.mohist.utils.JarUtils;
import red.mohist.utils.LogUtil;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class CommandManager {

    private static ExecutorService cmds = new ThreadPoolExecutor(5, 200,
            0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<>(1024), new ThreadFactoryBuilder().setNameFormat("CommandTask-%d").build(), new ThreadPoolExecutor.AbortPolicy());
    public static Map<String, Class<?>> executors = Maps.newHashMap();
    public static Map<Class<?>, String> usages = Maps.newHashMap();

    @SneakyThrows
    public static void init() {
        long start = System.currentTimeMillis();
        int load = 0;
        Set<Class<?>> classes = JarUtils.getAllLoadClasses();
        for (Class<?> loadClass : classes) {
            if (loadClass.getAnnotation(Command.class) != null) {
                Command c = loadClass.getAnnotation(Command.class);
                executors.put(c.name(), loadClass);// 注册主要指令
                LogUtil.getLogger().info("注册指令 " + loadClass.getName() + "(" + c.name() + ")");
                for (String alia : c.alias()) {
                    executors.put(alia, loadClass);
                    LogUtil.getLogger().info("注册别称 " + loadClass.getName() + "(" + alia + ")");
                }
                usages.put(loadClass, c.usage());
                load++;
            }
        }
        LogUtil.getLogger().info("加载了 " + load + " 个指令，耗时 " + (System.currentTimeMillis() - start) + "(ms).");
    }

    static long last = System.currentTimeMillis();

    public static void call(MessageChain messages, Member sender) {
        if (System.currentTimeMillis() - last < 3000) {
            sender.sendMessageAsync("指令发送太快了哦");
        } else {
            //cmds.execute(() -> callA(messages, sender));
            callA(messages,sender);
        }
        last = System.currentTimeMillis();
    }

    @SneakyThrows
    private static void callA(MessageChain messages, Member sender) {
        String msg = messages.contentToString().replaceFirst(LogUtil.command, "");// 获得带有mirai码的字符串，让用户自己解析。
        while (true) {
            if (!msg.contains("  ")) break;
            msg.replace("  ", " ");// 吧两个空格替换为一个
        }
        String[] sourceCmd = msg.split(" ");// 通过空格来切割
        if (sourceCmd.length > 0) {// 保证不为空
            String label = sourceCmd[0];
            LogUtil.getLogger().info("触发指令: " + label);
            LogUtil.getLogger().info("是否包含: " + executors.containsKey(label));
            if (executors.containsKey(label)) {
                String[] args = new String[sourceCmd.length - 1];
                System.arraycopy(sourceCmd, 1, args, 0, sourceCmd.length - 1);
                Class<?> executor = executors.get(label);
                CommandResult cr = new CommandResult();
                cr.setArgs(Arrays.asList(args));
                cr.setSender(sender);
                cr.setSource(messages);
                if (!(Arrays.asList(executor.getInterfaces()).contains(CommandExecutor.class) && (boolean) executor.getMethod("onCommand", CommandResult.class).invoke(executor.newInstance(), cr))) {
                    sender.getGroup().sendMessageAsync("指令执行失败。用法：" + usages.get(executor));
                }
            } else {
                sender.getGroup().sendMessageAsync("未知的指令.请使用 " + LogUtil.command + "cmdlist 来获得指令列表");
            }
        }
    }
}
