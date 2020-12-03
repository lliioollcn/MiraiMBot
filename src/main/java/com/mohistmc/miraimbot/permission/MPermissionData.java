package com.mohistmc.miraimbot.permission;

import java.util.Arrays;
import java.util.List;
import lombok.Builder;
import lombok.Data;

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

    public static final MPermissionData data = new MPermissionData();

    /**
     * 权限节点
     */
    public String[] permissions;
    /**
     * 继承的组
     */
    public String[] extendGroups;

    public MPermissionData(String[] permissions,String[] extendGroups) {
        this.permissions = permissions;
        this.extendGroups = extendGroups;
    }

    public MPermissionData() {

    }

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
