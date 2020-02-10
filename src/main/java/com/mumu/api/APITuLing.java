package com.mumu.api;

import jsonij.Value;
import jsonij.parser.JSONParser;
import jsonij.parser.ParserException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class APITuLing
{
    private static String AIPKEY = "6ce0ef17e54244d9834087cc5f676ca5";
    private static String HTTPURL = "http://www.tuling123.com/openapi/api";
    private static int c_text = 100000;
    private static int c_URL = 200000;
    private static int c_new = 302000;
    private static int c_food = 308000;
    private static int c_max = 40004;
    private String arg;
    
    public static String getDate(String arg, String userid) {
        String result = "";
        BufferedReader in = null;
        String url = null;
        try {
            url = "http://www.tuling123.com/openapi/api?key=6ce0ef17e54244d9834087cc5f676ca5&info=" + URLEncoder.encode(arg, "UTF-8") + "&userid=" + URLEncoder.encode(userid, "utf-8");
        }
        catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        Label_0230: {
            try {
                URL u = new URL(url);
                try {
                    URLConnection connection = u.openConnection();
                    connection.setRequestProperty("accept", "*/*");
                    connection.setRequestProperty("connection", "Keep-Alive");
                    connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
                    connection.connect();
                    in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
                    String tmp;
                    while ((tmp = in.readLine()) != null) {
                        result = String.valueOf(result) + tmp;
                    }
                }
                catch (IOException e2) {
                    e2.printStackTrace();
                    try {
                        in.close();
                    }
                    catch (IOException e3) {
                        e3.printStackTrace();
                    }
                    break Label_0230;
                }
                finally {
                    try {
                        in.close();
                    }
                    catch (IOException e3) {
                        e3.printStackTrace();
                    }
                }
                try {
                    in.close();
                }
                catch (IOException e3) {
                    e3.printStackTrace();
                }
            }
            catch (MalformedURLException e4) {
                e4.printStackTrace();
            }
            try {
                JSONParser jsonParser = new JSONParser();
                Value json = jsonParser.parse(result);
                int code = Integer.parseInt(String.format("%s", json.get("code")));
                switch (code) {
                    case 40004: {
                        result = "MI木今天已经很累了 ，我们明天再聊吧";
                        break;
                    }
                    case 308000: {
                        result = "看你妹菜谱";
                        break;
                    }
                    case 100000: {
                        result = String.format("%s", json.get("text"));
                        break;
                    }
                    case 302000: {
                        result = "看你妹新闻";
                        break;
                    }
                    case 200000: {
                        result = String.valueOf(String.format("%s", json.get("text"))) + String.format("%s", json.get("url"));
                        break;
                    }
                }
            }
            catch (ParserException e5) {
                e5.printStackTrace();
            }
        }
        return result;
    }
}
