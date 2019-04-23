package com.sic777.restful.base.constants;


/**
 * <p>错误信息常量
 *
 * @author sic777
 * @since 0.0.1
 */
public class ErrorMsg {
    /**
     * 参数校验错误,子类可覆盖
     */
    public static String PARAM_INVALID = "param invalid,details:'%s'";
    /**
     * key或者对象不存在,子类可覆盖
     */
    public static String OBJECT_NULL = "'%s' must not be null";
    /**
     * 参数值为空,子类可覆盖
     */
    public static String VALUE_EMPTY = "'%s' value must not be empty";
    /**
     * 参数值为NULL,子类可覆盖
     */
    public static String VALUE_NULL = "'%s' value must not be null";
    /**
     * 操作频繁
     */
    public static String FREQUENT_OPERATION = "frequent operation";
    /**
     * 权限不足,子类可覆盖
     */
    public static String INVALID_ACCESS = "invalid access";
    /**
     * URL找不到,子类可覆盖
     */
    public static String URL_NOT_FOUND = "requested path: '%s' not found";
    /**
     * 请求的方法不支持,子类可覆盖
     */
    public static String METHOD_NOT_ALLOWED = "method '%s' not allowed,uri '%s'";
    /**
     * 服务器发生异常,子类可覆盖
     */
    public static String SERVICE_EXCEPTION = "service unavailable";

}
