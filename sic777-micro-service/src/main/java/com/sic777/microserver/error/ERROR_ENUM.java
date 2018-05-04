package com.sic777.microserver.error;

/**
 * <p>错误集合</p>
 *
 * @author Zhengzhenxie
 * @version v1.0
 * @since 2018-05-04
 */
public enum ERROR_ENUM {
    /**
     * 禁止访问
     */
    INVALID_ACCESS(4031001, "invalid access"),
    /**
     * Access-Token 为空
     */
    ACCESS_TOKEN_EMPTY(4031002, "'Access-Token' empty"),
    /**
     * URI找不到
     */
    URI_NOT_FOUND(4041001, "requested path: '%s' not found"),
    /**
     * 请求方法不支持
     */
    METHOD_NOT_ALLOWED(4051001, "method '%s' not allowed,requested path: '%s'"),
    /**
     * 服务端发生异常
     */
    SERVICE_EXCEPTION(5031001, "service unavailable");

    private final int code;
    private final String msg;

    ERROR_ENUM(int code, String msg) {
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
