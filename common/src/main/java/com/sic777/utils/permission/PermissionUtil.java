package com.sic777.utils.permission;


/**
 * <p>权限校验工具
 *
 * @author sic777
 * @since 0.0.1
 */
public class PermissionUtil {
    private PermissionUtil() {
    }

    public static int permission(int... permissions) throws Exception {
        int len = permissions.length - 1;
        if (len < 0) {
            return 0;
        }
        int ret = permissions[len];
        while (len > 0) {
            ret |= permissions[--len] | permissions[len];
        }
        return ret;
    }

    /**
     * 判断是否有权限
     *
     * @param defaultPermission  默认配置的权限
     * @param validatePermission 需要校验的权限
     * @return
     */
    public static boolean hasPermission(int defaultPermission, int validatePermission) {
        return (defaultPermission & validatePermission) != 0;
    }
}