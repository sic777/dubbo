package com.sic777.microservice.exception.response;


import com.sic777.microservice.constants.MicroConstants;
import com.alibaba.fastjson.JSONObject;
import scala.Enumeration;

public class RestExceptionResponse {

    private final long code;
    private final String message;

    public RestExceptionResponse(Enumeration.Value error, Object... format) {
        this(error.id(), String.format(error.toString(), format));
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
