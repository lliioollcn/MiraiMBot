package com.mohistmc.miraimbot.permission;

import com.alibaba.fastjson.JSONArray;
import com.mohistmc.miraimbot.utils.FileUtil;
import lombok.Builder;
import lombok.Data;
import lombok.SneakyThrows;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

/**
 * *************************************
 * 示例JSON
 * *************************************
 * {
 * "permissions": [// 组的权限节点
 * "xxx.xxx.xxx",
 * "fadsfads.xsafasd.xaxa"
 * ],
 * "extendGroups": []
 * }
 * *************************************
 */
@Data
@Builder
public class MPermissionData {

    /**
     * 权限节点
     */
    public String[] permissions;
    /**
     * 继承的组
     */
    public String[] extendGroups;

    public List<String> getPermissions() {
        List<String> pers = Arrays.asList(permissions);
        if (extendGroups.length > 0) {
            for (String group : extendGroups) {
                if (MPermission.getGroups().containsKey(group)) {
                    MPermissionData data = (MPermissionData) MPermission.getGroups().get(group);
                    pers.addAll(data.getPermissions());
                }
            }
        }
        return pers;
    }

}
