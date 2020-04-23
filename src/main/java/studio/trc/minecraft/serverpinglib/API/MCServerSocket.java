package studio.trc.minecraft.serverpinglib.API;

import javax.naming.directory.Attribute;
import java.net.SocketAddress;
import java.net.InetSocketAddress;
import javax.naming.NamingException;
import javax.naming.directory.InitialDirContext;
import studio.trc.minecraft.serverpinglib.Protocol.ProtocolVersion;
import java.io.IOException;
import java.net.Socket;

public class MCServerSocket
{
    private final Socket socket;
    private boolean srv;
    private boolean isOnline;
    private String IP;
    private int Port;
    
    private MCServerSocket(final Socket socket) throws IOException {
        this.srv = false;
        this.isOnline = false;
        this.socket = socket;
    }
    
    public String getIP() {
        return this.IP;
    }
    
    public int getPort() {
        return this.Port;
    }
    
    public boolean isOnline() {
        return this.isOnline;
    }
    
    public boolean isSRVDomainName() {
        return this.srv;
    }
    
    @Deprecated
    public MCServerStatus getStatus() {
        return MCServerStatus.getStatus(this);
    }
    
    public MCServerStatus getStatus(final ProtocolVersion version) {
        return MCServerStatus.getStatus(this, version);
    }
    
    public Socket getSocket() {
        return this.socket;
    }
    
    public static MCServerSocket getInstance(String ip, int port, final int timeout) {
        try {
            final Socket socket = new Socket();
            boolean srv = false;
            try {
                final Attribute host = new InitialDirContext().getAttributes("dns:/_Minecraft._tcp." + ip, new String[] { "SRV" }).get("SRV");
                final String[] domain = host.toString().split(" ");
                final String newip = domain[domain.length - 1].substring(0, domain[domain.length - 1].length() - 1);
                final int newport = Integer.valueOf(domain[domain.length - 2]);
                ip = newip;
                port = newport;
                srv = true;
            }
            catch (NamingException ex3) {}
            catch (NullPointerException ex1) {
                return null;
            }
            socket.connect(new InetSocketAddress(ip, port), timeout);
            final MCServerSocket instance = new MCServerSocket(socket);
            instance.IP = ip;
            instance.Port = port;
            instance.srv = srv;
            instance.isOnline = true;
            return instance;
        }
        catch (IOException ex2) {
            return null;
        }
    }
    
    public static MCServerSocket getInstance(String ip, int port) {
        try {
            final Socket socket = new Socket();
            boolean srv = false;
            try {
                final Attribute host = new InitialDirContext().getAttributes("dns:/_Minecraft._tcp." + ip, new String[] { "SRV" }).get("SRV");
                if (host != null) {
                    final String[] domain = host.toString().split(" ");
                    final String newip = domain[domain.length - 1].substring(0, domain[domain.length - 1].length() - 1);
                    final int newport = Integer.valueOf(domain[domain.length - 2]);
                    ip = newip;
                    port = newport;
                    srv = true;
                }
            }
            catch (NamingException ex3) {}
            catch (NullPointerException ex1) {
                return null;
            }
            socket.connect(new InetSocketAddress(ip, port), 30000);
            final MCServerSocket instance = new MCServerSocket(socket);
            instance.IP = ip;
            instance.Port = port;
            instance.srv = srv;
            instance.isOnline = true;
            return instance;
        }
        catch (IOException ex2) {
            return null;
        }
    }
}
