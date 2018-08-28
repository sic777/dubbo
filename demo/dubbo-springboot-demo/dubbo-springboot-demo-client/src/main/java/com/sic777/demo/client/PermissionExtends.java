package com.sic777.demo.client;

import com.sic777.restful.base.permission.RestPermission;

/**
 * <p>
 *
 * @author sic777
 * @since 0.0.1
 */
public interface PermissionExtends extends RestPermission {
    int MEMBER = 2;
    int BIZ = 4;
    int USER = 8;
}
