package com.mohistmc.miraimbot.console.log4j;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MiraiMBotLog {

    public static boolean isDebug = false;
    public static Logger LOGGER = LogManager.getLogger("MiraiMBot");

    public static void Debug(String msg) {
        if (isDebug) {
            LOGGER.info("[DEBUG] " + msg);
        }
    }

}
