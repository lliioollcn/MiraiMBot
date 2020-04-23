package red.mohist;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author Mgazul
 * @date 2020/4/22 23:25
 */
public class UpdateUtils {

    public static String info(String msg) throws IOException {
            System.out.println("开始读取CI数据");
            URLConnection request = new URL("https://ci.codemc.io/job/Mohist-Community/job/Mohist-" + msg + "/lastSuccessfulBuild/api/json").openConnection();
            request.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:25.0) Gecko/20100101 Firefox/25.0");
            request.connect();
            System.out.println(((HttpURLConnection) request).getResponseCode());
            JsonElement json = new JsonParser().parse(new InputStreamReader((InputStream) request.getContent()));
            String versionCode = json.getAsJsonObject().get("changeSet").getAsJsonObject().get("items").getAsJsonArray().get(0).getAsJsonObject().get("commitId").toString().replaceAll("\"", "").substring(0, 7);
            String time = json.getAsJsonObject().get("changeSet").getAsJsonObject().get("items").getAsJsonArray().get(0).getAsJsonObject().get("date").toString().replace("+0800", "").replaceAll("\"", "");
            String message = json.getAsJsonObject().get("changeSet").getAsJsonObject().get("items").getAsJsonArray().get(0).getAsJsonObject().get("msg").toString().replaceAll("\"", "");

            String sendMsg = "======Mohist更新检测======" + "\n" +
                    "分支: #branche#" + "\n" +
                    "构建号: #versionCode#" + "\n" +
                    "提交时间: #time#" + "\n" +
                    "提交信息: #msg#" + "\n";
            sendMsg = sendMsg
                    .replace("#branche#", msg)
                    .replace("#versionCode#", versionCode)
                    .replace("#time#", time)
                    .replace("#msg#", message);
        return sendMsg;
    }
}
