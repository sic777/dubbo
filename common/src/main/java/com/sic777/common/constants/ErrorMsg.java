package com.sic777.common.constants;

/**
 * <p>错误信息常量</p>
 *
 * @author Zhengzhenxie
 * @version v1.0
 * @since 2018-06-05
 */
public final class ErrorMsg {
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
     * 服务器发生异常
     */
    public final static String SERVICE_EXCEPTION = "service unavailable";

}
