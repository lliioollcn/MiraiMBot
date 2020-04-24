package red.mohist.cmds;

import cn.lliiooll.IOUtil;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import red.mohist.Command;
import red.mohist.CommandExecutor;
import studio.trc.minecraft.serverpinglib.API.MCServerModInfo;
import studio.trc.minecraft.serverpinglib.API.MCServerSocket;
import studio.trc.minecraft.serverpinglib.API.MCServerStatus;
import studio.trc.minecraft.serverpinglib.Protocol.ProtocolVersion;
import xin.lz1998.cq.event.message.CQGroupMessageEvent;
import xin.lz1998.cq.robot.CoolQ;

public class Ping implements CommandExecutor {

    @Override
    public boolean onCommand(Command cmd) {
        CoolQ cq = cmd.getCq();
        CQGroupMessageEvent event = cmd.getEvent();
        if (cmd.getArgs().length <= 0) {
            cq.sendGroupMsg(event.getGroupId(),
                    "[CQ:image,file=mohist/logo.png]\n" +
                            "======使用检测======\n" +
                            "请输入IP, 格式> ip:端口", false);
            return true;
        } else {
            String msg = cmd.getArgs()[0];
            String[] ip = msg.split(":");
            MCServerSocket socket = null;
            switch (ip.length) {
                case 1: {
                    socket = MCServerSocket.getInstance(ip[0], 25565);
                    break;
                }
                case 2: {
                    try {
                        socket = MCServerSocket.getInstance(ip[0], Integer.valueOf(ip[1]));
                    } catch (NumberFormatException ex) {
                        break;
                    }
                    break;
                }
                default: {
                    break;
                }
            }
            if (socket == null) return false;
            MCServerStatus status = socket.getStatus(ProtocolVersion.v1_12_2);
            if (!status.isMCServer()) {
                return false;
            }
            StringBuilder sb = new StringBuilder();
            StringBuilder mods = new StringBuilder();
            sb.append("======Mohist使用检测======").append("\n");
            sb.append("检测通过: 你正在使用Mohist, 感谢你的使用!!!").append("\n");
            sb.append("在线: " + status.getOnlinePlayers() + "/" + status.getMaxPlayers()).append("\n");
            boolean mohist = false;
            for (MCServerModInfo.MCMod mod : status.getModInfo().getModList()) {
                mods.append(mod.getModId() + ":" + mod.getVersion()).append("\n");
                String ver = mod.getVersion().replace(" ", "");
                if (mod.getModId().equals("mohist") && ver.length() == 14) {
                    mohist = true;
                    sb.append("Mohist版本: " + (hasLatestVersion().equals(ver) ? ver + "(已是最新版)" : ver + "(你该更新了)")).append("\n");
                }
            }
            if (mods != null && mods.toString().contains("mohist:") && mohist) {
                sb.append("模组数量: " + (modsize(mods.toString(), ":") - 5)).append("\n");

                cq.sendGroupMsg(event.getGroupId(), sb.toString(), false);
                return true;
            } else {
                System.out.println(status.getVersion());
                cq.sendGroupMsg(event.getGroupId(),
                                "======Mohist使用检测======\n" +
                                "此服务器不是Mohist, 可能原因：1.BC, 2.旧版Mohist, 3.其他核心", false);
            }
        }
        return false;
    }

    public int modsize(String str, String s) {
        int count = 0, len = str.length();
        while (str.indexOf(s) != -1) {
            str = str.substring(str.indexOf(s) + 1, str.length());
            count++;
        }
        return count;
    }

    public String hasLatestVersion() {
        try {
            URL url = new URL("https://ci.codemc.io/job/Mohist-Community/job/Mohist-1.12.2/lastBuild/api/json");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("User-Agent", "Mohist");
            if (connection.getResponseCode() == 200) {
                InputStream is = connection.getInputStream();
                String commits = IOUtil.readContent(is);
                String sha = "\"SHA1\"";

                String s0 = commits.substring(commits.indexOf(sha));

                String[] ss = s0.split("\n");
                String b = ss[0].trim().replace(" ", "").replace("\"", "");

                return "1.12.2-" + b.substring(5, 12);
            } else {
                System.out.println("Link access failed");
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return "未知版本";
    }
}
