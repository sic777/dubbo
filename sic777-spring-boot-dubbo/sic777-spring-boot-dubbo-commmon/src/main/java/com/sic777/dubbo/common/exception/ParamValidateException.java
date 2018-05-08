package com.sic777.dubbo.common.exception;

/**
 * <p></p>
 *
 * @author Zhengzhenxie
 * @version v1.0
 * @since 2018-04-23
 */
public class ParamValidateException extends RuntimeException {
    private final int code;
    private final String msg;

    public ParamValidateException(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
