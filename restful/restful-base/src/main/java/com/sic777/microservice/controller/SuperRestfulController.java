package com.sic777.microservice.controller;

import com.alibaba.fastjson.JSONObject;
import com.sic777.common.constants.BaseConstant;
import com.sic777.microservice.constants.MicroConstants;

/**
 * <p></p>
 *
 * @author Zhengzhenxie
 * @version v1.0
 * @since 2018-06-05
 */
public abstract class SuperRestfulController {
    /**
     * 获取当前accessToken对应的权限
     *
     * @return
     */
    protected final int getPermission() {
        return this.getAccessTokenData().getIntValue(MicroConstants.PERMISSION_FLAG);
    }

    /**
     * 获取accessToken字符串
     *
     * @return
     */
    protected final String getAccessTokenString() {
        return this.getAccessTokenData().getString(BaseConstant.ACCESS_TOKEN_FLAG);
    }

    /**
     * 获取accessToken缓存数据和接口权限
     *
     * @return
     */
    protected abstract JSONObject getAccessTokenData();

    /**
     * 响应200
     *
     * @param obj
     */
    protected abstract void rest200(Object obj);

}
