package com.mohistmc.utils;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class TimeUtil {
    public static String UTCToCST(String utc) {
        String formatPattern = "yyyy-MM-dd HH:mm:ss";
        ZonedDateTime zdt = ZonedDateTime.parse(utc);
        LocalDateTime localDateTime = zdt.toLocalDateTime();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formatPattern);
        String gst = formatter.format(localDateTime.plusHours(8));
        return gst;
    }
}
