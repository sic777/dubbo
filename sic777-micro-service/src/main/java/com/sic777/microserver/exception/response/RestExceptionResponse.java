package com.sic777.microserver.exception.response;


import com.sic777.microserver.constants.MicroConstants;
import com.alibaba.fastjson.JSONObject;

public class RestExceptionResponse {

    private final int code;
    private final String message;

    public RestExceptionResponse(int code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 响应前端
     *
     * @return
     */
    public final JSONObject response() {
        JSONObject resp = new JSONObject();
        resp.put(MicroConstants.ERROR_FLAG, this);
        return resp;
    }

    public final int getCode() {
        return code;
    }

    public final String getMessage() {
        return message;
    }
}
