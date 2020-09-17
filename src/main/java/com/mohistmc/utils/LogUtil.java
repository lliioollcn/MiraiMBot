package com.mohistmc.utils;

import lombok.Getter;
import net.mamoe.mirai.utils.MiraiLogger;

import java.util.ArrayList;
import java.util.List;

public class LogUtil {

    @Getter
    public static MiraiLogger logger;

    @Getter
    public static boolean debug = true;
    public static String command = "#";

    @Getter
    public static List<Long> groups = new ArrayList<Long>() {{
        add(618430930L);
        add(463370226L);
        add(655057127L);
    }};
}
