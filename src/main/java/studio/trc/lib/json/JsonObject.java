/*
 * $Id: JSONObject.java,v 1.1 2006/04/15 14:10:48 platform Exp $
 * Created on 2006-4-10
 */
package studio.trc.lib.json;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * A JSON object. Key value pairs are unordered. JSONObject supports java.util.Map interface.
 * 
 * @author FangYidong<fangyidong@yahoo.com.cn>
 */
public class JsonObject extends HashMap implements Map, JsonAware, JsonStreamAware {
	
	private static final long serialVersionUID = -503443796854799292L;
	
	
	public JsonObject() {
		super();
	}

	/**
	 * Allows creation of a JSONObject from a Map. After that, both the
	 * generated JSONObject and the Map can be modified independently.
	 * 
	 * @param map
	 */
	public JsonObject(Map map) {
		super(map);
	}


    /**
     * Encode a map into JSON text and write it to out.
     * If this map is also a JSONAware or JSONStreamAware, JSONAware or JSONStreamAware specific behaviours will be ignored at this top level.
     * 
     * @see JsonValue#writeJSONString(Object, Writer)
     * 
     * @param map
     * @param out
     */
	public static void writeJSONString(Map map, Writer out) throws IOException {
		if(map == null){
			out.write("null");
			return;
		}
		
		boolean first = true;
		Iterator iter=map.entrySet().iterator();
		
        out.write('{');
		while(iter.hasNext()){
            if(first)
                first = false;
            else
                out.write(',');
			Entry entry=(Entry)iter.next();
            out.write('\"');
            out.write(escape(String.valueOf(entry.getKey())));
            out.write('\"');
            out.write(':');
			JsonValue.writeJSONString(entry.getValue(), out);
		}
		out.write('}');
	}

	public void writeJSONString(Writer out) throws IOException{
		writeJSONString(this, out);
	}
	
	/**
	 * Convert a map to JSON text. The result is a JSON object. 
	 * If this map is also a JSONAware, JSONAware specific behaviours will be omitted at this top level.
	 * 
	 * @see JsonValue#toJSONString(Object)
	 * 
	 * @param map
	 * @return JSON text, or "null" if map is null.
	 */
	public static String toJSONString(Map map){
		final StringWriter writer = new StringWriter();
		
		try {
			writeJSONString(map, writer);
			return writer.toString();
		} catch (IOException e) {
			// This should never happen with a StringWriter
			throw new RuntimeException(e);
		}
	}
	
	public String toJSONString(){
		return toJSONString(this);
	}
	
	public String toString(){
		return toJSONString();
	}

	public static String toString(String key,Object value){
        StringBuffer sb = new StringBuffer();
        sb.append('\"');
        if(key == null)
            sb.append("null");
        else
            JsonValue.escape(key, sb);
		sb.append('\"').append(':');
		
		sb.append(JsonValue.toJSONString(value));
		
		return sb.toString();
	}
	
	/**
	 * Escape quotes, \, /, \r, \n, \b, \f, \t and other control characters (U+0000 through U+001F).
	 * It's the same as JSONValue.escape() only for compatibility here.
	 * 
	 * @see JsonValue#escape(String)
	 * 
	 * @param s
	 * @return
	 */
	public static String escape(String s){
		return JsonValue.escape(s);
	}

	public String getString(final Object o) {
		return ((HashMap<Object, Object>)this).get(o).toString();
	}

	public Long getLong(final Object o) {
		return ((HashMap<Object, Long>)this).get(o);
	}

	public Integer getInteger(final Object o) {
		return ((HashMap<Object, Integer>)this).get(o);
	}

	public int getInt(final Object o) {
		return Integer.parseInt(((HashMap<Object, Object>)this).get(o).toString());
	}

	public boolean getBoolean(final Object o) {
		return Boolean.valueOf(((HashMap<Object, Object>)this).get(o).toString());
	}

	public Short getShort(final Object o) {
		return ((HashMap<Object, Short>)this).get(o);
	}

	public Float getFloat(final Object o) {
		return ((HashMap<Object, Float>)this).get(o);
	}

	public Double getDouble(final Object o) {
		return ((HashMap<Object, Double>)this).get(o);
	}

	public JsonObject getJsonObject(final Object o) {
		return ((HashMap<Object, JsonObject>)this).get(o);
	}

	public JsonArray getJsonArray(final Object o) {
		return ((HashMap<Object, JsonArray>)this).get(o);
	}
}
