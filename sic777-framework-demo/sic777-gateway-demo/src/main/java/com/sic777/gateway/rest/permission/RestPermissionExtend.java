package com.sic777.gateway.rest.permission;

import com.sic777.microserver.permission.RestPermission;

/**
 * <p></p>
 *
 * @author Zhengzhenxie
 * @version v1.0
 * @since 2018-05-04
 */
public interface RestPermissionExtend extends RestPermission {
    /**
     * 管理员
     */
    int ADMIN = 2;
    /**
     * 商家
     */
    int BIZ = 4;
    /**
     * 用户
     */
    int USER = 8;
}
