package com.mohistmc.miraimbot.utils;

import com.mohistmc.miraimbot.console.log4j.MiraiMBotLog;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class URLUtil {
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
            MiraiMBotLog.LOGGER.info("");
        }
        return "error";
    }
}
