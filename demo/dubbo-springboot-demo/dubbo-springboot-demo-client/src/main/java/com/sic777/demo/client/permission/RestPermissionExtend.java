package com.sic777.demo.client.permission;

import com.sic777.microservice.permission.RestPermission;

/**
 * <p></p>
 *
 * @author Zhengzhenxie
 * @version v1.0
 * @since 2018-08-16
 */
public interface RestPermissionExtend extends RestPermission {
    int MEMBER = 2;
    int BIZ = 4;
    int USER = 8;
}
