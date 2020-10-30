package com.mohistmc.miraimbot.utils;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

public class LogUtil {

    @Getter
    public static boolean debug = false;
    /**
     * 指令前缀
     */
    public static String command = "#";

    @Getter
    public static List<Long> groups = new ArrayList<Long>() {{
        add(618430930L);
        add(463370226L);
        add(655057127L);
    }};
}
