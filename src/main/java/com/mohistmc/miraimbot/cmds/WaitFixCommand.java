package com.mohistmc.miraimbot.cmds;

import com.alibaba.fastjson.JSONObject;
import com.mohistmc.miraimbot.cmds.manager.CommandExecutor;
import com.mohistmc.miraimbot.cmds.manager.CommandResult;
import com.mohistmc.miraimbot.cmds.manager.annotations.Command;
import com.mohistmc.miraimbot.utils.FileUtil;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Command(name = "waitfix", alias = {"待修"})
public class WaitFixCommand implements CommandExecutor {

    public static Map<Integer, String> l = new HashMap<>();


    @Override
    public boolean onCommand(CommandResult result) {
        Long mg = 2634905535L;
        if (result.getArgs().size() <= 0) {
            StringBuilder sb = new StringBuilder();
            sb.append("======待修列表======").append("\n");
            Set<Integer> set = l.keySet();
            for (int i : set) {
                sb.append("ID: ").append(i).append("内容: ").append(l.get(i)).append("\n");
            }
            result.getSender().sendMessage(sb.toString());
            return true;
        } else {
            if (result.getSender().getId() != mg) {
                StringBuilder sb = new StringBuilder();
                sb.append("抱歉呐，目前只有Mgazul可以使用添加、保存和删除功能呐~").append("\n");
                result.getSender().sendMessage(sb.toString());
                return true;
            }
            String msg = result.getArgs().get(0);
            if (msg.equals("save")) {
                result.getSender().sendMessage("保存ing...");
                JSONObject datas = new JSONObject();
                Set<Integer> set = l.keySet();
                for (int i : set) {
                    datas.put(String.valueOf(i), l.get(i));
                }
                try {
                    FileUtil.writeData(new File("waitFix.json"), datas.toJSONString().getBytes());
                } catch (IOException e) {
                    e.printStackTrace();
                    return false;
                }
                result.getSender().sendMessage("保存完毕~");
                return true;
            }
            if (msg.equals("add")) {
                StringBuilder sb = new StringBuilder();
                sb.append("======待修列表======").append("\n");
                if (result.getArgs().size() < 3) {
                    sb.append("参数不符合哦~").append("\n");
                    sb.append("用法: #待修 add id(数字) 内容").append("\n");
                } else {
                    String value = result.getArgs().get(2);
                    int id = Integer.parseInt(result.getArgs().get(1));
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
                result.getSender().sendMessage(sb.toString());
                return true;
            }
            if (msg.equals("del")) {
                StringBuilder sb = new StringBuilder();
                sb.append("======待修列表======").append("\n");
                if (result.getArgs().size() < 2) {
                    sb.append("参数不符合哦~").append("\n");
                    sb.append("用法: #待修 del id(数字)").append("\n");
                } else {
                    int id = Integer.parseInt(result.getArgs().get(1));
                    if (l.containsKey(id)) {
                        l.remove(id);
                        sb.append("移除成功~").append("\n");
                        sb.append("ID: " + id).append("\n");
                    } else {
                        sb.append("移除失败~").append("\n");
                        sb.append("不存在的ID: " + id).append("\n");
                    }
                }
                result.getSender().sendMessage(sb.toString());
                return true;
            }
        }
        return true;
    }
}
