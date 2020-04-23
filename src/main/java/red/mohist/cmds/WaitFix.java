package red.mohist.cmds;

import com.alibaba.fastjson.JSONObject;
import red.mohist.Command;
import red.mohist.CommandExecutor;
import red.mohist.utils.FileUtil;
import xin.lz1998.SpringCQApplication;
import xin.lz1998.cq.event.message.CQGroupMessageEvent;
import xin.lz1998.cq.robot.CoolQ;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class WaitFix implements CommandExecutor {

    public static Map<Integer, String> l = new HashMap<>();

    @Override
    public boolean onCommand(Command cmd) {
        CoolQ cq = cmd.getCq();
        CQGroupMessageEvent event = cmd.getEvent();
        Long mg = 2634905535L;

        if (cmd.getArgs().length <= 0) {
            StringBuilder sb = new StringBuilder();
            sb.append("[CQ:image,file=mohist/logo.png]").append("\n");
            sb.append("======待修列表======").append("\n");
            Set<Integer> set = l.keySet();
            for (int i : set) {
                sb.append("ID: ").append(i).append("内容: ").append(l.get(i)).append("\n");
            }
            cq.sendGroupMsg(event.getGroupId(), sb.toString(), false);
            return true;
        } else {
            if (event.getSender().getUserId() != mg) {
                StringBuilder sb = new StringBuilder();
                sb.append("[CQ:image,file=mohist/logo.png]").append("\n");
                sb.append("======待修列表======").append("\n");
                sb.append("抱歉呐，目前只有Mgazul可以使用添加、保存和删除功能呐~").append("\n");
                cq.sendGroupMsg(event.getGroupId(), sb.toString(), false);
                return true;
            }
            String msg = cmd.getArgs()[0];
            if (msg.equals("save")) {
                cq.sendGroupMsg(event.getGroupId(), "保存ing...", false);
                JSONObject datas = new JSONObject();
                Set<Integer> set = l.keySet();
                for (int i : set) {
                    datas.put(String.valueOf(i), l.get(i));
                }
                try {
                    FileUtil.writeData(new File(SpringCQApplication.dataFile), datas.toJSONString().getBytes());
                } catch (IOException e) {
                    e.printStackTrace();
                    return false;
                }
                cq.sendGroupMsg(event.getGroupId(), "保存完毕~", false);
                return true;
            }
            if (msg.equals("add")) {
                StringBuilder sb = new StringBuilder();
                sb.append("[CQ:image,file=mohist/logo.png]").append("\n");
                sb.append("======待修列表======").append("\n");
                if (cmd.getArgs().length < 3) {
                    sb.append("参数不符合哦~").append("\n");
                    sb.append("用法: #待修 add id(数字) 内容").append("\n");
                } else {
                    String value = cmd.getArgs()[2];
                    int id = Integer.parseInt(cmd.getArgs()[1]);
                    if (l.containsKey(id)) {
                        sb.append("添加失败~").append("\n");
                        sb.append("原因: 已存在这个id~").append("\n");
                        return true;
                    }
                    l.put(id, value);
                    sb.append("添加成功~").append("\n");
                    sb.append("ID: " + id).append("\n");
                    sb.append("内容: " + value).append("\n");
                }
                cq.sendGroupMsg(event.getGroupId(), sb.toString(), false);
                return true;
            }
            if (msg.equals("del")) {
                StringBuilder sb = new StringBuilder();
                sb.append("[CQ:image,file=mohist/logo.png]").append("\n");
                sb.append("======待修列表======").append("\n");
                if (cmd.getArgs().length < 2) {
                    sb.append("参数不符合哦~").append("\n");
                    sb.append("用法: #待修 del id(数字)").append("\n");
                } else {
                    int id = Integer.parseInt(cmd.getArgs()[1]);
                    if (l.containsKey(id)) {
                        l.remove(id);
                        sb.append("移除成功~").append("\n");
                        sb.append("ID: " + id).append("\n");
                    } else {
                        sb.append("移除失败~").append("\n");
                        sb.append("不存在的ID: " + id).append("\n");
                    }
                }
                cq.sendGroupMsg(event.getGroupId(), sb.toString(), false);
                return true;
            }
        }
        return false;
    }
}
