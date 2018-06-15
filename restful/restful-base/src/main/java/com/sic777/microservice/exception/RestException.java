package com.sic777.microservice.exception;


import com.sic777.common.exception.SuperRuntimeException;
import com.sic777.microservice.exception.response.RestExceptionResponse;
import scala.Enumeration;

/**
 * <p></p>
 *
 * @author Zhengzhenxie<br>
 * <br>2017-12-18
 * @version v1.0
 * @since 1.7
 */
public abstract class RestException extends SuperRuntimeException {
    /**
     *
     */
    private static final long serialVersionUID = -70921703399466740L;
    /**
     * 错误码
     */
    private final long code;
    /**
     * 错误信息
     */
    private final String msg;
    /**
     * 是否需要记录错误日志
     */
    private final boolean log;

    public RestException(Enumeration.Value error, boolean log, Object... format) {
        this(error.id(), String.format(error.toString(), format), log, null);
    }

    public RestException(long code, String msg, boolean log, Throwable throwable) {
        super(throwable);
        this.code = code;
        this.msg = msg;
        this.log = log;
    }

    public abstract int getHttpStatus();

    public Object response() {
        return new RestExceptionResponse(code, msg).response();
    }

    public long getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public boolean isLog() {
        return log;
    }
}
