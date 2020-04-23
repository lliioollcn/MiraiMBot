package studio.trc.minecraft.serverpinglib.Utils;

import java.util.HashMap;
import studio.trc.lib.json.JSONObject;

public class ColorUtils
{
    public static String ignoreColorCharacter(String text) {
        while (text.contains("§")) {
            final int i = text.indexOf("§");
            if (text.length() - 1 == i) {
                text = text.replace("§", "");
            }
            else {
                text = text.replace(text.substring(i, i + 2), "");
            }
        }
        return text;
    }
    
    public static String JsonToColorCharacter(final JSONObject Json) {
        String text = ((HashMap<String, String>)Json).get("text");
        String colorcharacter = "";
        if (Json.get("color") != null) {
            colorcharacter += getColorCharacter(getColor(Json.getString("color")));
        }
        if (Json.get("bold") != null && Json.getBoolean("bold")) {
            colorcharacter += "§l";
        }
        if (Json.get("italic") != null && Json.getBoolean("italic")) {
            colorcharacter += "§o";
        }
        if (Json.get("underlined") != null && Json.getBoolean("underlined")) {
            colorcharacter += "§n";
        }
        if (Json.get("strikethrough") != null && Json.getBoolean("strikethrough")) {
            colorcharacter += "§m";
        }
        if (Json.get("obfuscated") != null && Json.getBoolean("obfuscated")) {
            colorcharacter += "§k";
        }
        text = colorcharacter + text;
        return text;
    }
    
    public static Color getColor(final String colorname) {
        for (final Color c : Color.values()) {
            if (c.equals(Color.valueOf(colorname.toUpperCase()))) {
                return c;
            }
        }
        return null;
    }
    
    public static String getColorCharacter(final Color color) {
        switch (color) {
            case AQUA: {
                return "§b";
            }
            case BLACK: {
                return "§0";
            }
            case BLUE: {
                return "§9";
            }
            case DARK_AQUA: {
                return "§3";
            }
            case DARK_BLUE: {
                return "§1";
            }
            case DARK_GRAY: {
                return "§8";
            }
            case DARK_GREEN: {
                return "§2";
            }
            case DARK_PURPLE: {
                return "§5";
            }
            case DARK_RED: {
                return "§4";
            }
            case GOLD: {
                return "§6";
            }
            case GRAY: {
                return "§7";
            }
            case GREEN: {
                return "§a";
            }
            case LIGHT_PURPLE: {
                return "§d";
            }
            case RED: {
                return "§c";
            }
            case WHITE: {
                return "§f";
            }
            case YELLOW: {
                return "§e";
            }
            default: {
                return "§r";
            }
        }
    }
    
    public enum Color
    {
        BLACK, 
        DARK_BLUE, 
        DARK_GREEN, 
        DARK_AQUA, 
        DARK_RED, 
        DARK_PURPLE, 
        GOLD, 
        GRAY, 
        DARK_GRAY, 
        BLUE, 
        GREEN, 
        AQUA, 
        RED, 
        LIGHT_PURPLE, 
        YELLOW, 
        WHITE;
    }
}
