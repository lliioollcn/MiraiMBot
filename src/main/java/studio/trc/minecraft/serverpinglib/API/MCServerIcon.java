package studio.trc.minecraft.serverpinglib.API;

import java.io.IOException;
import java.io.FileOutputStream;
import studio.trc.lib.json.JSONObject;
import studio.trc.minecraft.serverpinglib.Utils.Base64Decoder;
import java.io.File;

public class MCServerIcon
{
    private final JSONObject Json;
    private final MCServerStatus status;
    
    MCServerIcon(final MCServerStatus status) {
        this.status = status;
        this.Json = status.getJson();
    }
    
    public void saveImageFile(final File file) {
        final String base64code = this.getBase64Image();
        if (base64code == null) {
            return;
        }
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            final Base64Decoder base64 = new Base64Decoder();
            try (final FileOutputStream out = new FileOutputStream(file)) {
                final byte[] bytes = base64.decodeBuffer(base64code);
                out.write(bytes);
            }
        }
        catch (Exception ex) {}
    }
    
    public void saveImageFile(final String path) {
        final String base64code = this.getBase64Image();
        final File file = new File(path);
        if (base64code == null) {
            return;
        }
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            final Base64Decoder base64 = new Base64Decoder();
            try (final FileOutputStream out = new FileOutputStream(file)) {
                final byte[] bytes = base64.decodeBuffer(base64code);
                out.write(bytes);
            }
        }
        catch (Exception ex) {}
    }
    
    public boolean hasServerIcon() {
        return this.getBase64Image() != null;
    }
    
    public String getBase64Image() {
        return (this.Json.get("favicon") != null) ? this.Json.getString("favicon").split(",")[1] : null;
    }
    
    public byte[] getImageBytes() {
        try {
            return new Base64Decoder().decodeBuffer(this.getBase64Image());
        }
        catch (IOException ex) {
            return null;
        }
    }
    
    public MCServerStatus status() {
        return this.status;
    }
}
