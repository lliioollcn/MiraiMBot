package studio.trc.minecraft.serverpinglib.API;

import java.io.Reader;
import java.io.IOException;
import java.io.InputStreamReader;
import studio.trc.lib.json.JSONObject;
import studio.trc.lib.json.parser.JSONParser;
import studio.trc.minecraft.serverpinglib.Utils.Encoding;
import studio.trc.minecraft.serverpinglib.Protocol.ProtocolNumber;
import studio.trc.minecraft.serverpinglib.Protocol.ProtocolPacket;
import java.io.DataOutputStream;
import studio.trc.minecraft.serverpinglib.Protocol.ProtocolVersion;

public class MCServerStatus
{
    private boolean isMCServer;
    private JSONObject Jsontext;
    private int protocolnumber;
    private String protocolversion;
    private final MCServerSocket mcsocket;

    private MCServerStatus(final MCServerSocket mcsocket) {
        this.mcsocket = mcsocket;
        final JSONParser Json = new JSONParser();
        final String text = this.sendPacket();
        if (text == null) {
            this.isMCServer = false;
            return;
        }
        try {
            this.Jsontext = Json.parseToJsonObject(text);
            this.isMCServer = true;
        }
        catch (Exception ex) {
            try {
                this.Jsontext = Json.parseToJsonObject(text + "}");
                this.isMCServer = true;
            }
            catch (Exception ex2) {
                this.Jsontext = null;
                this.isMCServer = false;
            }
        }
    }

    private MCServerStatus(final MCServerSocket mcsocket, final ProtocolVersion version) {
        this.mcsocket = mcsocket;
        final JSONParser Json = new JSONParser();
        final String text = this.sendPacket(version);
        if (text == null) {
            this.isMCServer = false;
            return;
        }
        try {
            this.Jsontext = Json.parseToJsonObject(text);
            this.isMCServer = true;
        }
        catch (Exception ex) {
            try {
                this.Jsontext = Json.parseToJsonObject(text + "}");
                this.isMCServer = true;
            }
            catch (Exception ex2) {
                this.Jsontext = null;
                this.isMCServer = false;
            }
        }
    }

    public int getMaxPlayers() {
        return this.Jsontext.getJsonObject("players").getInt("max");
    }

    public int getOnlinePlayers() {
        return this.Jsontext.getJsonObject("players").getInt("online");
    }

    public int getServerProtocolNumber() {
        return this.Jsontext.getJsonObject("version").getInt("protocol");
    }

    public int getProtocolNumber() {
        return this.protocolnumber;
    }

    public String getVersion() {
        return this.Jsontext.getJsonObject("version").getString("name");
    }

    public String getProtocolVersion() {
        return this.protocolversion;
    }

    public String getStringJson() {
        return this.Jsontext.toString();
    }

    public MCServerModInfo getModInfo() {
        return new MCServerModInfo(this);
    }

    public MCServerMotd getMotd() {
        return new MCServerMotd(this);
    }

    public MCServerSample getSample() {
        return new MCServerSample(this);
    }

    public MCServerIcon getIcon() {
        return new MCServerIcon(this);
    }

    public boolean isMCServer() {
        return this.isMCServer;
    }

    public boolean supportVersion() {
        return this.getServerProtocolNumber() == this.protocolnumber;
    }

    JSONObject getJson() {
        return this.Jsontext;
    }

    private String sendPacket() {
        String text = null;
        try (final DataOutputStream out = new DataOutputStream(this.mcsocket.getSocket().getOutputStream())) {
            final ProtocolVersion version = ProtocolVersion.latest;
            out.write(ProtocolPacket.getVersionProtocol(version));
            out.flush();
            this.protocolnumber = ProtocolNumber.getProtocolNumber(version);
            this.protocolversion = version.toString().replace("_to_", " - ").replace("latest", "1.15.1").replace("v", "").replace("_", ".");
            try (final Reader in = new InputStreamReader(this.mcsocket.getSocket().getInputStream(), Encoding.characterEncoding)) {
                final StringBuilder sb = new StringBuilder();
                int frountbraces = 0;
                int rearbraces = 0;
                boolean bl = true;
                int b;
                while ((b = in.read()) != -1) {
                    sb.append((char)b);
                    if (sb.toString().contains("{\"")) {
                        if (bl) {
                            ++frountbraces;
                            bl = false;
                        }
                        if (String.valueOf((char)b).equalsIgnoreCase("{")) {
                            ++frountbraces;
                        }
                        if (String.valueOf((char)b).equalsIgnoreCase("}")) {
                            ++rearbraces;
                        }
                    }
                    if (frountbraces == rearbraces && frountbraces >= 2 && rearbraces >= 2) {
                        break;
                    }
                }
                try {
                    text = sb.toString().substring(sb.toString().indexOf("{\""));
                }
                catch (Exception ex) {
                    this.isMCServer = false;
                }
            }
        }
        catch (IOException ex2) {}
        return text;
    }

    private String sendPacket(final ProtocolVersion version) {
        String text = null;
        try (final DataOutputStream out = new DataOutputStream(this.mcsocket.getSocket().getOutputStream())) {
            out.write(ProtocolPacket.getVersionProtocol(version));
            out.flush();
            this.protocolnumber = ProtocolNumber.getProtocolNumber(version);
            this.protocolversion = version.toString().replace("_to_", " - ").replace("latest", "1.15.1").replace("v", "").replace("_", ".");
            try (final Reader in = new InputStreamReader(this.mcsocket.getSocket().getInputStream(), Encoding.characterEncoding)) {
                final StringBuilder sb = new StringBuilder();
                int frountbraces = 0;
                int rearbraces = 0;
                boolean bl = true;
                int b;
                while ((b = in.read()) != -1) {
                    sb.append((char)b);
                    if (sb.toString().contains("{\"")) {
                        if (bl) {
                            ++frountbraces;
                            bl = false;
                        }
                        if (String.valueOf((char)b).equalsIgnoreCase("{")) {
                            ++frountbraces;
                        }
                        if (String.valueOf((char)b).equalsIgnoreCase("}")) {
                            ++rearbraces;
                        }
                    }
                    if (frountbraces == rearbraces && frountbraces >= 2 && rearbraces >= 2) {
                        break;
                    }
                }
                try {
                    text = sb.toString().substring(sb.toString().indexOf("{\""));
                }
                catch (Exception ex) {
                    this.isMCServer = false;
                }
            }
        }
        catch (IOException ex2) {}
        return text;
    }

    public static byte[] longToByteArray(final long l) {
        final byte[] b = { (byte)(l >>> 56), (byte)(l >>> 48), (byte)(l >>> 40), (byte)(l >>> 32), (byte)(l >>> 24), (byte)(l >>> 16), (byte)(l >>> 8), (byte)(l >>> 0) };
        return b;
    }

    public static long byteArrayToLong(final byte[] b) {
        return ((long)b[0] << 56) + ((long)(b[1] & 0xFF) << 48) + ((long)(b[2] & 0xFF) << 40) + ((long)(b[3] & 0xFF) << 32) + ((long)(b[4] & 0xFF) << 24) + ((b[5] & 0xFF) << 16) + ((b[6] & 0xFF) << 8) + ((b[7] & 0xFF) << 0);
    }

    @Deprecated
    public static MCServerStatus getStatus(final MCServerSocket mcsocket) {
        return (mcsocket == null) ? null : new MCServerStatus(mcsocket);
    }

    public static MCServerStatus getStatus(final MCServerSocket mcsocket, final ProtocolVersion version) {
        return (mcsocket == null) ? null : new MCServerStatus(mcsocket, version);
    }
}
