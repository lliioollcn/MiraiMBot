package com.mohistmc.miraimbot.console.log4j;

import com.mohistmc.miraimbot.utils.LogUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MiraiMBotLog {

    public static Logger LOGGER = LogManager.getLogger("MiraiMBot");

    public static void Debug(String msg) {
        if (LogUtil.isDebug()) {
            LOGGER.info("[DEBUG] " + msg);
        }
    }

}
