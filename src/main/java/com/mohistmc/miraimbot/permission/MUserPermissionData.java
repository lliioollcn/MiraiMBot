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
 * "group": "default",
 * "permissions": [
 * "xxx.xxx"
 * ],
 * "id": 123321122333
 * }
 * *************************************
 */
@Data
@Builder
public class MUserPermissionData {

    /**
     * 权限节点
     */
    public String[] permissions;
    /**
     * 所在的组
     */
    public String group;
    /**
     * id
     */
    public long id;

    public List<String> getPermissions() {
        List<String> pers = Arrays.asList(permissions);
        if (MPermission.getGroups().containsKey(group)) {
            MPermissionData data = (MPermissionData) MPermission.getGroups().get(group);
            pers.addAll(data.getPermissions());
        }
        return pers;
    }

}
