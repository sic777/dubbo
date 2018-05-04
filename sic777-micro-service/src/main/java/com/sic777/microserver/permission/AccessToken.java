package com.sic777.microserver.permission;

import com.alibaba.fastjson.JSONObject;
import com.sic777.microserver.constants.MicroConstants;

public class AccessToken {
    /**
     * accessToken
     */
    private final String accessToken;
    /**
     * token对应的数据
     */
    private final JSONObject data;


    public AccessToken(String accessToken, JSONObject data) {
        this.accessToken = accessToken;
        this.data = data;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public JSONObject getData() {
        return data;
    }

    public int getPermission() {
        Integer p = data.getInteger(MicroConstants.PERMISSION_FLAG);
        return null != p ? p : RestPermission.ANYBODY;
    }
}
