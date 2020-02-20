package xin.lz1998.bot.plugin.prefix;

import cn.lliiooll.IOUtil;
import com.alibaba.fastjson.JSONObject;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import xin.lz1998.cq.event.message.CQGroupMessageEvent;
import xin.lz1998.cq.robot.CQPlugin;
import xin.lz1998.cq.robot.CoolQ;

// 这个插件用于预处理消息，如指令前缀
public class PrefixPlugin extends CQPlugin {
    // 指令前缀 /
    private String prefix = "#更新";

    @Override
    public int onGroupMessage(CoolQ cq, CQGroupMessageEvent event) {
        String msg = event.getMessage();
        if (msg.startsWith(prefix) && (event.getGroupId() == 793311898 || event.getGroupId() == 782534813 )) {
            // 指令以 prefix 开头，去除prefix，并继续执行下一个插件
            msg = msg.substring(prefix.length());
            try {
                JSONObject json = JSONObject.parseObject(getUrlString("https://api.github.com/repos/Mohist-Community/Mohist/branches/" + msg));
                StringBuilder sb = new StringBuilder();
                sb.append("[CQ:image,file=mohist/logo.png]").append("\n");
                sb.append("======Mohist更新检测======").append("\n");
                sb.append("分支: #branche#").append("\n");
                sb.append("构建号: #versionCode#").append("\n");
                String dwn12 = "https://ci.codemc.io/job/Mohist-Community/job/Mohist-#branche#/lastSuccessfulBuild/artifact/build/distributions/Mohist-#versionCode#-server.jar";
                String dwn7 = "https://ci.codemc.io/job/Mohist-Community/job/Mohist-#branche#/lastSuccessfulBuild/artifact/build/distributions/Mohist-#branche#-#versionCode#-server.jar";
                sb.append("提交人: #putMan#").append("\n");
                sb.append("提交时间: #time#").append("\n");
                sb.append("提交信息: #msg#").append("\n");
                if(msg.equals("1.12.2")){
                    sb.append("下载: " + dwn12);
                } else if (msg.equals("1.7.10")) {
                    sb.append("下载: " + dwn7);
                } else {
                    sb.append("下载: 开发中");
                }
                String branche = msg;
                String versionCode = json.getJSONObject("commit").getString("sha").substring(0, 7);
                String putMan = json.getJSONObject("commit").getJSONObject("commit").getJSONObject("committer").getString("name");
                String time = UTCToCST(json.getJSONObject("commit").getJSONObject("commit").getJSONObject("committer").getString("date"));
                String message = json.getJSONObject("commit").getJSONObject("commit").getString("message");

                if (json.getString("message") != null && json.getString("message").equalsIgnoreCase("Branch not found")) {
                    cq.sendGroupMsg(event.getGroupId(), "[CQ:image,file=mohist/logo.png]\n" +
                            "======更新检测======\n" +
                            "检测失败,分支不存在喵~", false);
                    return MESSAGE_IGNORE;
                }
                String sendMsg = sb.toString();
                sendMsg = sendMsg
                        .replace("#branche#", branche)
                        .replace("#versionCode#", versionCode)
                        .replace("#putMan#", putMan)
                        .replace("#time#", time)
                        .replace("#msg#", message);
                cq.sendGroupMsg(event.getGroupId(), sendMsg, false);
                //cq.sendPrivateMsg(3483706632L, sendMsg, false);
            } catch (Exception e) {
                e.printStackTrace();
                cq.sendGroupMsg(event.getGroupId(), "[CQ:image,file=mohist/logo.png]\n" +
                        "======更新检测======\n" +
                        "检测失败,内部错误喵~", false);
                return MESSAGE_IGNORE;
            }
        }
        return MESSAGE_IGNORE;
    }


    public static String getUrlString(String urlkey) {
        try {
            URL url = new URL(urlkey);
            URLConnection conn = url.openConnection();
            conn.setConnectTimeout(10 * 1000);
            conn.setReadTimeout(10 * 1000);
            InputStream is = conn.getInputStream();

            String s = IOUtil.readContent(is, "UTF-8");
            is.close();
            return s;

        } catch (IOException e) {
            System.out.println("");
        }
        return "error";
    }


    public static String UTCToCST(String utc) {
        String formatPattern = "yyyy-MM-dd HH:mm:ss";
        ZonedDateTime zdt = ZonedDateTime.parse(utc);
        LocalDateTime localDateTime = zdt.toLocalDateTime();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formatPattern);
        String gst = formatter.format(localDateTime.plusHours(8));
        return gst;
    }

}
