package com.sic777.microserver.exception;


import com.sic777.microserver.exception.response.RestExceptionResponse;
import org.springframework.http.HttpStatus;

/**
 * <p></p>
 *
 * @author Zhengzhenxie<br>
 *         <br>2017-12-18
 * @version v1.0
 * @since 1.7
 */
public abstract class RestException extends RuntimeException {
    private final int code;
    private final String msg;


    public RestException(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public abstract HttpStatus getHttpStatus();

    public Object response() {
        return new RestExceptionResponse(code, msg).response();
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    /**
     * 覆盖父方法,不爬栈,提升性能
     *
     * @return
     */
    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}
