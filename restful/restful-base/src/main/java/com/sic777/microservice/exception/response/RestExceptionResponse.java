package com.sic777.microservice.exception.response;


import com.sic777.microservice.constants.MicroConstants;
import com.alibaba.fastjson.JSONObject;
import com.sic777.microservice.exception.error.RESTFUL_ERROR;

public class RestExceptionResponse {

    private final long code;
    private final String message;

    public RestExceptionResponse(RESTFUL_ERROR ERROR, Object... format) {
        this(ERROR.getCode(), String.format(ERROR.getMsg(), format));
    }

    public RestExceptionResponse(long code, String message) {
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

    public final long getCode() {
        return code;
    }

    public final String getMessage() {
        return message;
    }
}
