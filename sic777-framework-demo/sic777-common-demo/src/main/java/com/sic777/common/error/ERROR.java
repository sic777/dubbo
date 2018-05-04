package com.sic777.common.error;

/**
 * <p></p>
 *
 * @author Zhengzhenxie
 * @version v1.0
 * @since 2018-05-03
 */
public enum ERROR {
    /**
     * 参数校验错误
     */
    PARAM_INVALID(40010001, "param '%s' invalid");


    private final int code;

    private final String msg;

    ERROR(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
