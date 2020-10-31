package com.mohistmc.miraimbot.permission;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;

import com.mohistmc.miraimbot.cmds.manager.ConsoleSender;
import com.mohistmc.miraimbot.utils.FileUtil;
import lombok.SneakyThrows;
import net.mamoe.mirai.contact.User;

import java.io.File;

import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * *************************************
 * 示例JSON
 * *************************************
 * {
 * "default": {// 组名称
 * "permissions": [// 组的权限节点
 * "xxx.xxx.xxx",
 * "fadsfads.xsafasd.xaxa"
 * ],
 * "extendGroups": []
 * },
 * "others": {
 * "permissions": [// 组的权限节点
 * "others.xxxx.xxx",
 * "others.xxxxx.1xxx"
 * ],
 * "extendGroups": [// 所有继承的组
 * "default"
 * ]
 * }
 * }
 * *************************************
 */
public class MPermission {

    private static final File pF = new File("permisson.json");// 权限文件
    private static final File oF = new File("ops.json");// op文件
    private static final File usersDir = new File("./permissions/");// 权限文件夹
    private static List<Long> ops = Lists.newArrayList();
    private static Map<String, Object> groups = new HashMap<String, Object>() {{
        put("default", MPermissionData.builder()
                .extendGroups(new String[]{""})
                .permissions(new String[]{"default.base"})
                .build());
    }};

    @SneakyThrows
    public static void init() {
        if (!pF.exists()) {
            pF.createNewFile();
            FileUtil.writeData(pF, new JSONObject(groups).toJSONString().getBytes(StandardCharsets.UTF_8));
        }
        if (!oF.exists()) {
            oF.createNewFile();
            JSONArray ja = new JSONArray();
            ja.add(3483706632L);
            FileUtil.writeData(oF, ja.toJSONString().getBytes(StandardCharsets.UTF_8));
        }
        if (!usersDir.exists()) {
            usersDir.mkdir();
        }
        reloadAll();
    }

    public static boolean hasPermission(User user, String permission) {
        return hasPermission(user.getId(), permission);
    }

    public static boolean hasPermission(long user, String permission) {
        try {
            if ((user == ConsoleSender.INSTANCE.getId()) || ops.contains(user)) {
                return true;
            }
            File file = new File(usersDir, user + ".json");
            if (!file.exists()) {
                MPermissionData data = (MPermissionData) groups.get("default");
                if (data.getPermissions().contains(permission)) {
                    return true;
                }
                return false;
            } else {
                String jstr = FileUtil.readContent(oF, "UTF-8");
                if (JSONObject.isValidObject(jstr)) {
                    MUserPermissionData d = JSON.parseObject(jstr, MUserPermissionData.class);
                    if (d.getPermissions().contains(permission)) {
                        return true;
                    }
                }
                return false;
            }
        } catch (Throwable e) {
            return false;
        }
    }

    public static List<String> getAllPermission(long user) {
        List<String> pers = Lists.newArrayList();
        try {
            if ((user == ConsoleSender.INSTANCE.getId()) || ops.contains(user)) {
                return pers;
            }
            File file = new File(usersDir, user + ".json");
            if (!file.exists()) {
                MPermissionData data = (MPermissionData) groups.get("default");
                pers.addAll(data.getPermissions());
            } else {
                String jstr = FileUtil.readContent(oF, "UTF-8");
                if (JSONObject.isValidObject(jstr)) {
                    MUserPermissionData d = JSON.parseObject(jstr, MUserPermissionData.class);
                    pers.addAll(d.getPermissions());
                }
            }
            return pers;
        } catch (Throwable e) {
            return pers;
        }
    }

    public static List<String> getAllPermission(User user) {
        return getAllPermission(user.getId());
    }

    public static boolean addPermission(User user, String permission) {
        return addPermission(user.getId(), permission);
    }

    public static boolean addPermission(long user, String permission) {
        try {
            if ((user == ConsoleSender.INSTANCE.getId()) || ops.contains(user)) {
                return true;
            }
            File file = new File(usersDir, user + ".json");
            MUserPermissionData d = MUserPermissionData.builder().id(user).group("default").permissions(new String[]{permission}).build();
            if (file.exists()) {
                String jstr = FileUtil.readContent(oF, "UTF-8");
                if (JSONObject.isValidObject(jstr)) {
                    d = JSON.parseObject(jstr, MUserPermissionData.class);
                    if (!d.getPermissions().contains(permission)) {
                        d.getPermissions().add(permission);
                    }
                }
            }
            FileUtil.writeData(file, JSON.toJSONString(d).getBytes(StandardCharsets.UTF_8));
            return true;
        } catch (Throwable e) {
            return false;
        }
    }

    public static boolean setGroup(User user, String group) {
        return setGroup(user.getId(), group);
    }

    public static boolean setGroup(long user, String group) {
        try {
            if ((user == ConsoleSender.INSTANCE.getId()) || ops.contains(user)) {
                return true;
            }
            File file = new File(usersDir, user + ".json");
            MUserPermissionData d = MUserPermissionData.builder().id(user).group(group).permissions(new String[0]).build();
            if (file.exists()) {
                String jstr = FileUtil.readContent(oF, "UTF-8");
                if (JSONObject.isValidObject(jstr)) {
                    d = JSON.parseObject(jstr, MUserPermissionData.class);
                    d.setGroup(group);
                }
            }
            FileUtil.writeData(file, JSON.toJSONString(d).getBytes(StandardCharsets.UTF_8));
            return true;
        } catch (Throwable e) {
            return false;
        }
    }

    public static boolean setOp(User user) {
        return setOp(user.getId());
    }

    public static boolean setOp(long user) {
        try {
            if ((user == ConsoleSender.INSTANCE.getId()) || ops.contains(user)) {
                return true;
            }
            ops.add(user);
            saveAll();
            return true;
        } catch (Throwable e) {
            return false;
        }
    }

    public static boolean isOp(User user) {
        try {
            if ((user instanceof ConsoleSender) || ops.contains(user.getId())) {
                return true;
            }
            return ops.contains(user.getId());
        } catch (Throwable e) {
            return false;
        }
    }

    @SneakyThrows
    public static void saveAll() {
        JSONArray ja = new JSONArray();
        ja.addAll(ops);
        FileUtil.writeData(oF, ja.toJSONString().getBytes(StandardCharsets.UTF_8));
        JSONObject json = new JSONObject();
        groups.forEach(json::put);
        FileUtil.writeData(pF, json.toJSONString().getBytes(StandardCharsets.UTF_8));
    }

    @SneakyThrows
    public static void reloadAll() {
        ops = JSON.parseArray(FileUtil.readContent(oF, "UTF-8")).toJavaList(Long.class);
        JSONObject o = JSON.parseObject(FileUtil.readContent(pF, "UTF-8"));
        for (String key : groups.keySet()) {
            groups.put(key, o.getJSONObject(key).toJavaObject(MPermissionData.class));
        }
    }

    public static Map<String, Object> getGroups() {
        return groups;
    }


    public static String getGroup(long user) {
        try {
            if ((user == ConsoleSender.INSTANCE.getId()) || ops.contains(user)) {
                return "op";
            }
            File file = new File(usersDir, user + ".json");
            if (file.exists()) {
                String jstr = FileUtil.readContent(oF, "UTF-8");
                if (JSONObject.isValidObject(jstr)) {
                    MUserPermissionData d = JSON.parseObject(jstr, MUserPermissionData.class);
                    return d.getGroup();
                }
            }
            return "default";
        } catch (Throwable e) {
            return "default";
        }
    }

    public static String getGroup(User user) {
       return getGroup(user.getId());
    }
}
