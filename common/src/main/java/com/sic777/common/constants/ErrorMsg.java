package com.sic777.common.constants;

/**
 * <p>错误信息常量
 *
 * @author sic777
 * @since 0.0.1
 */
public final class ErrorMsg {
    private ErrorMsg() {
    }

    /**
     * 参数校验错误
     */
    public final static String PARAM_INVALID = "param invalid,details:'%s'";
    /**
     * key或者对象不存在
     */
    public final static String OBJECT_NULL = "'%s' must not be null";
    /**
     * 参数值为空
     */
    public final static String VALUE_EMPTY = "'%s' value must not be empty";
    /**
     * 参数值为NULL
     */
    public final static String VALUE_NULL = "'%s' value must not be null";
    /**
     * 权限不足
     */
    public final static String INVALID_ACCESS = "invalid access";
    /**
     * URL找不到
     */
    public final static String URL_NOT_FOUND = "requested path: '%s' not found";
    /**
     * 请求的方法不支持
     */
    public final static String METHOD_NOT_ALLOWED = "Method {%s} Not Allowed,URI {'%s'}";
    /**
     * 服务器发生异常
     */
    public final static String SERVICE_EXCEPTION = "service unavailable";

}
