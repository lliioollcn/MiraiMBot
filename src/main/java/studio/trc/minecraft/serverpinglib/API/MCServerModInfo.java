package studio.trc.minecraft.serverpinglib.API;

import studio.trc.lib.json.JsonObject;
import studio.trc.lib.json.parser.JsonParser;
import studio.trc.lib.json.parser.ParseException;

import java.util.ArrayList;
import java.util.List;

public class MCServerModInfo
{
    private final JsonObject Json;
    private final MCServerStatus status;
    
    MCServerModInfo(final MCServerStatus status) {
        this.Json = status.getJson();
        this.status = status;
    }
    
    public boolean hasModInfo() {
        return this.Json.get("modinfo") != null;
    }
    
    public boolean hasMod() {
        return !this.getModList().isEmpty();
    }
    
    public String getModType() {
        return ((this.Json).getJsonObject("modinfo").getString("type") != null) ? this.Json.getJsonObject("modinfo").getString("type") : null;
    }
    
    public List<MCMod> getModList() {
        final List<MCMod> modlist = new ArrayList<MCMod>();
        if (!this.hasModInfo()) {
            return modlist;
        }
        final JsonParser jp = new JsonParser();
        for (final Object obj : (this.Json.getJsonObject("modinfo").get("modList") != null) ? this.Json.getJsonObject("modinfo").getJsonArray("modList") : new ArrayList<Object>()) {
            JsonObject mods = null;
            try {
                mods = (JsonObject)jp.parse(obj.toString());
            }
            catch (ParseException ex) {}
            modlist.add(new MCMod(mods));
        }
        return modlist;
    }
    
    public MCServerStatus status() {
        return this.status;
    }
    
    public class MCMod
    {
        private final JsonObject Json;
        
        MCMod(final JsonObject Json) {
            this.Json = Json;
        }
        
        public String getVersion() {
            return (this.Json.get("version") != null) ? this.Json.getString("version") : null;
        }
        
        public String getModId() {
            return (this.Json.get("modid") != null) ? this.Json.getString("modid") : null;
        }
    }
}
