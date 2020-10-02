package com.mohistmc;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.TimeUnit;

public class GitHubAuto implements Runnable {

    String sha;

    @Override
    public void run() {
        URLConnection request;
        try {
            request = new URL("https://ci.codemc.io/job/Mohist-Community/job/Mohist-1.12.2/lastSuccessfulBuild/api/json").openConnection();
            request.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:25.0) Gecko/20100101 Firefox/25.0");
            request.connect();
            System.out.println(((HttpURLConnection) request).getResponseCode());
            JsonElement json = new JsonParser().parse(new InputStreamReader((InputStream) request.getContent()));
            String versionCode = json.getAsJsonObject().get("number").toString();
            String time = json.getAsJsonObject().get("changeSet").getAsJsonObject().get("items").getAsJsonArray().get(0).getAsJsonObject().get("date").toString().replace("+0800", "").replaceAll("\"", "");
            String message = json.getAsJsonObject().get("changeSet").getAsJsonObject().get("items").getAsJsonArray().get(0).getAsJsonObject().get("msg").toString().replaceAll("\"", "");
            if (sha == null) sha = versionCode;
            if (!sha.equals(versionCode)) {
                String sendMsg = "======Mohist更新推送======" + "\n" +
                        "分支: #branche#" + "\n" +
                        "构建号: #versionCode#" + "\n" +
                        "提交时间: #time#" + "\n" +
                        "提交信息: #msg#" + "\n";
                sendMsg = sendMsg
                        .replace("#branche#", "1.12.2")
                        .replace("#versionCode#", versionCode)
                        .replace("#time#", time)
                        .replace("#msg#", message);
                MiraiMBot.bot.getGroup(793311898L).sendMessage(sendMsg);
                MiraiMBot.bot.getGroup(782534813L).sendMessage(sendMsg);
                sha = versionCode;
            }

        } catch (IOException e) {
            e.printStackTrace();
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