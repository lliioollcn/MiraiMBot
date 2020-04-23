package studio.trc.minecraft.serverpinglib.API;

import studio.trc.lib.json.JSONObject;
import studio.trc.minecraft.serverpinglib.Utils.ColorUtils;
import studio.trc.lib.json.parser.JSONParser;

public class MCServerMotd
{
    private final JSONObject Json;
    private final MCServerStatus status;
    
    MCServerMotd(final MCServerStatus status) {
        this.status = status;
        this.Json = status.getJson();
    }
    
    public String getMotdText() {
        final StringBuilder sb = new StringBuilder();
        final JSONParser jp = new JSONParser();
        try {
            for (final Object obj : this.Json.getJsonObject("description").getJsonArray("extra")) {
                sb.append(jp.parseToJsonObject(obj.toString()).get("text"));
            }
        }
        catch (Exception ex) {
            try {
                sb.append(ColorUtils.ignoreColorCharacter(this.Json.getJsonObject("description").getString("text")));
            }
            catch (Exception ex2) {
                sb.append(ColorUtils.ignoreColorCharacter(this.Json.getString("description")));
            }
        }
        return sb.toString();
    }
    
    public String getColorMotdText() {
        final StringBuilder sb = new StringBuilder();
        final JSONParser jp = new JSONParser();
        try {
            for (final Object obj : this.Json.getJsonObject("description").getJsonArray("extra")) {
                sb.append(ColorUtils.JsonToColorCharacter(jp.parseToJsonObject(obj.toString())));
            }
        }
        catch (Exception ex) {
            try {
                sb.append((this.Json.getJsonObject("description")).get("text"));
            }
            catch (Exception ex2) {
                sb.append((this.Json).get("description"));
            }
        }
        return sb.toString();
    }
    
    public String getLine1MotdText() {
        final String motd = this.getMotdText();
        if (motd.contains("\n")) {
            return motd.substring(0, motd.indexOf("\n"));
        }
        return motd;
    }
    
    public String getLine2MotdText() {
        final String motd = this.getMotdText();
        if (motd.contains("\n")) {
            return motd.substring(motd.indexOf("\n") + 1);
        }
        return "";
    }
    
    public String getLine1ColorMotd() {
        final String motd = this.getColorMotdText();
        if (motd.contains("\n")) {
            return motd.substring(0, motd.indexOf("\n"));
        }
        return motd;
    }
    
    public String getLine2ColorMotd() {
        final String motd = this.getColorMotdText();
        if (motd.contains("\n")) {
            return motd.substring(motd.indexOf("\n") + 1);
        }
        return "";
    }
    
    public MCServerStatus status() {
        return this.status;
    }
}
