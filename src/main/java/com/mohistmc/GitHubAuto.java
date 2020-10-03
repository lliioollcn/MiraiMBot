package com.mohistmc;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

public class GitHubAuto implements Runnable {

    public static Map<String, String> version_map = new ConcurrentHashMap<>();
    public static List<String> ver = new ArrayList<>();

    static {
        ver.add("1.12.2");
        ver.add("InternalTest");
    }

    @Override
    public void run() {
        for (String s : ver) {
            URLConnection request;
            try {
                request = new URL("https://ci.codemc.io/job/Mohist-Community/job/Mohist-"+ s + "/lastSuccessfulBuild/api/json").openConnection();
                request.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:25.0) Gecko/20100101 Firefox/25.0");
                request.connect();
                JsonElement json = new JsonParser().parse(new InputStreamReader((InputStream) request.getContent()));
                String number = json.getAsJsonObject().get("number").toString();
                String time = json.getAsJsonObject().get("changeSet").getAsJsonObject().get("items").getAsJsonArray().get(0).getAsJsonObject().get("date").toString().replace("+0800", "").replaceAll("\"", "");
                String message = json.getAsJsonObject().get("changeSet").getAsJsonObject().get("items").getAsJsonArray().get(0).getAsJsonObject().get("msg").toString().replaceAll("\"", "");
                if (version_map.get(s) == null) version_map.put(s, number);
                if (!version_map.get(s).equals(number)) {
                    String sendMsg = "======Mohist更新推送======" + "\n" +
                            "分支: #branche#" + "\n" +
                            "构建号: #number#" + "\n" +
                            "提交时间: #time#" + "\n" +
                            "提交信息: #msg#" + "\n";
                    sendMsg = sendMsg
                            .replace("#branche#", s)
                            .replace("#number#", number)
                            .replace("#time#", time)
                            .replace("#msg#", message);
                    MiraiMBot.bot.getGroup(793311898L).sendMessage(sendMsg);
                    MiraiMBot.bot.getGroup(782534813L).sendMessage(sendMsg);
                    version_map.put(s, number);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void start() {
        System.out.println("开始运行更新推送程序");
        MohistThreadBox.GitHubAuto.scheduleAtFixedRate(new GitHubAuto(), 1000 * 1, 1000 * 30, TimeUnit.MILLISECONDS);
    }

    public static void stop() {
        MohistThreadBox.GitHubAuto.shutdown();
    }
}