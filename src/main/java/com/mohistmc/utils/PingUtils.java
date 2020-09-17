package com.mohistmc.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class PingUtils {
    public static int modsize(String str, String s) {
        int count = 0, len = str.length();
        while (str.indexOf(s) != -1) {
            str = str.substring(str.indexOf(s) + 1, str.length());
            count++;
        }
        return count;
    }

    public static String hasLatestVersion() {
        try {
            URL url = new URL("https://ci.codemc.io/job/Mohist-Community/job/Mohist-1.12.2/lastBuild/api/json");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("User-Agent", "Mohist");
            if (connection.getResponseCode() == 200) {
                InputStream is = connection.getInputStream();
                String commits = IOUtil.readContent(new InputStreamReader(is));
                String sha = "\"SHA1\"";

                String s0 = commits.substring(commits.indexOf(sha));

                String[] ss = s0.split("\n");
                String b = ss[0].trim().replace(" ", "").replace("\"", "");

                return "1.12.2-" + b.substring(5, 12);
            } else {
                System.out.println("Link access failed");
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return "未知版本";
    }
}
