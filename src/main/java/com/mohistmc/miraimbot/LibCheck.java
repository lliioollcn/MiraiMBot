package com.mohistmc.miraimbot;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class LibCheck {

    public static boolean hasQQAndroid() {
        //TODO md5
        File file = new File("lib", "mirai-core-qqandroid-1.3.1.jar");
        return file.exists();
    }

    public static void downloadFile() throws IOException {
        String u = "https://github.com/project-mirai/mirai-repo/blob/master/shadow/mirai-core-qqandroid/mirai-core-qqandroid-1.3.0.jar";
        File f = new File("lib", "mirai-core-qqandroid-1.3.1.jar");
        if (!f.exists()) {
            f.getParentFile().mkdirs();
            FileChannel.open(Paths.get(f.getAbsolutePath()), StandardOpenOption.CREATE, StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING).transferFrom(Channels.newChannel(getInput(u)), 0L, Long.MAX_VALUE);

        }
    }

    public static URLConnection getConn(String u) {
        URLConnection conn = null;
        try {
            conn = new URL(u).openConnection();
            conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:31.0) Gecko/20100101 Firefox/31.0");
        } catch (IOException e) { e.printStackTrace(); }
        return conn;
    }

    public static InputStream getInput(String URL) throws IOException { return getConn(URL).getInputStream(); }
}
