package red.mohist;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class MohistUpdate {

    public static String hasLatestVersion(String branches) {
        String str = "https://api.github.com/repos/Mohist-Community/Mohist/branches/" + branches;
        String commits = getUrlString(str);

        JSONObject jsonArray = JSON.parseObject(commits);// 读取整体json
        JSONObject json = jsonArray.getJSONObject("commit");// 获取最新的commit
        String sha = json.getString("sha");// 读取sha

        JSONObject commit = json.getJSONObject("commit");// 获取json
        String message = commit.getString("message");// 获取消息

        JSONObject author = commit.getJSONObject("author");// 获取json
        String name = author.getString("name");// 获取消息
        String date = author.getString("date");// 获取消息
        StringBuilder sb = new StringBuilder();
        sb.append("======Mohist更新检测======").append("\n");
        sb.append("分支: " + branches).append("\n");
        sb.append("构件号: " + sha.substring(0, 7)).append("\n");
        String dwn12 = "https://ci.codemc.io/job/Mohist-Community/job/Mohist-"+branches+"/lastSuccessfulBuild/artifact/build/distributions/Mohist-"+sha.substring(0, 7)+"-server.jar";
        String dwn7 = "https://ci.codemc.io/job/Mohist-Community/job/Mohist-"+branches+"/lastSuccessfulBuild/artifact/build/distributions/Mohist-"+branches + "-"+sha.substring(0, 7)+"-server.jar";
        sb.append("提交人: " + name).append("\n");
        sb.append("提交时间: " +  UTCToCST(date)).append("\n");
        sb.append("提交信息: " + message).append("\n");
        if(branches.equals("1.12.2")){
            sb.append("下载: " + dwn12);
        } else {
            sb.append("下载: " + dwn7);
        }

        return sb.toString().replace("\n", "\\n");
    }

    public static String UTCToCST(String utc) {
        String formatPattern = "yyyy-MM-dd HH:mm:ss";
        ZonedDateTime zdt  = ZonedDateTime.parse(utc);
        LocalDateTime localDateTime = zdt.toLocalDateTime();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formatPattern);
        String gst = formatter.format(localDateTime.plusHours(8));
        return gst;
    }

    public static String getUrlString(String urlkey) {
        try {
            URL url = new URL(urlkey);
            URLConnection conn = url.openConnection();
            conn.setConnectTimeout(10*1000);
            conn.setReadTimeout(10*1000);
            InputStream is = conn.getInputStream();

            String s = IOUtil.readContent(is, "UTF-8");
            is.close();
            return s;

        } catch (IOException e) {
            System.out.println("");
        }
        return "error";
    }
}
