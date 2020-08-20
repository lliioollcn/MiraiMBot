package studio.trc.minecraft.serverpinglib.API;

import studio.trc.lib.json.JsonObject;
import studio.trc.lib.json.parser.JsonParser;
import studio.trc.lib.json.parser.ParseException;
import studio.trc.minecraft.serverpinglib.Utils.ColorUtils;

import java.util.ArrayList;
import java.util.List;

public class MCServerSample
{
    private final JsonObject Json;
    private final MCServerStatus status;
    
    MCServerSample(final MCServerStatus status) {
        this.status = status;
        this.Json = status.getJson();
    }
    
    public List<SampleText> getSampleList() {
        final JsonParser jp = new JsonParser();
        final List<SampleText> list = new ArrayList<SampleText>();
        for (final Object obj : (this.Json.getJsonObject("players").get("sample") != null) ? this.Json.getJsonObject("players").getJsonArray("sample") : new ArrayList<Object>()) {
            JsonObject samples = null;
            try {
                samples = jp.parseToJsonObject(obj.toString());
            }
            catch (ParseException ex) {}
            list.add(new SampleText(samples));
        }
        return list;
    }
    
    public boolean hasSample() {
        return !this.getSampleList().isEmpty();
    }
    
    public MCServerStatus status() {
        return this.status;
    }
    
    public enum SampleEnum
    {
        ID, 
        NAME;
    }
    
    public class SampleText
    {
        private final JsonObject Json;
        
        SampleText(final JsonObject Json) {
            this.Json = Json;
        }
        
        public String getID() {
            return ColorUtils.ignoreColorCharacter(this.Json.getString("id"));
        }
        
        public String getName() {
            return ColorUtils.ignoreColorCharacter(this.Json.getString("name"));
        }
        
        public String get(final SampleEnum object) {
            if (object.equals(SampleEnum.ID)) {
                return this.getID();
            }
            if (object.equals(SampleEnum.NAME)) {
                return this.getName();
            }
            return null;
        }
        
        public String getColorID() {
            return this.Json.getString("id");
        }
        
        public String getColorName() {
            return this.Json.getString("name");
        }
        
        public String getColor(final SampleEnum object) {
            if (object.equals(SampleEnum.ID)) {
                return this.getColorID();
            }
            if (object.equals(SampleEnum.NAME)) {
                return this.getColorName();
            }
            return null;
        }
    }
}
