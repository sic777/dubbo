package com.sic777.common.exception;


import com.sic777.common.utils.proguard.NoProguard;

/**
 * <p>通用的运行时异常
 *
 * @author sic777
 * @since 0.0.1
 */
@NoProguard
public class CommonException extends SuperRuntimeException {
    /**
     *
     */
    private static final long serialVersionUID = -702270821179079289L;
    private final long code;
    private final String msg;

    public CommonException(long code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public long getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    @Override
    public String toString() {
        return "CommonException{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                '}';
    }
}
