package com.sic777.restful.base.controller;

import com.sic777.restful.base.constants.RestConstants;
import com.sic777.restful.base.permission.RestPermission;
import com.alibaba.fastjson.JSONObject;
import com.sic777.common.utils.proguard.NoProguard;

/**
 * <p></p>
 *
 * @author Zhengzhenxie
 * @version v1.0
 * @since 2018-06-05
 */
@NoProguard
public abstract class SuperRestfulController implements RestPermission {
    /**
     * 获取当前accessToken对应的权限
     *
     * @return
     */
    protected final int getPermission() {
        return this.getAccessTokenData().getIntValue(RestConstants.PERMISSION_FLAG);
    }

    /**
     * 获取accessToken字符串
     *
     * @return
     */
    protected final String getAccessTokenString() {
        return this.getAccessTokenData().getString(RestConstants.ACCESS_TOKEN_FLAG);
    }

    /**
     * 获取accessToken缓存数据和接口权限
     *
     * @return
     */
    protected abstract JSONObject getAccessTokenData();

    /**
     * 返回成功(任意对象)
     *
     * @param obj
     * @throws Exception
     */
    public abstract void success(Object obj) throws Exception;

}
