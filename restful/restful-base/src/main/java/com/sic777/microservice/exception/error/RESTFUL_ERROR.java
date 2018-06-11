package com.sic777.microservice.exception.error;

import com.sic777.common.constants.ErrorMsg;
import com.sic777.microservice.constants.MicroConstants;

/**
 * <p>通用错误枚举</p>
 *
 * @author Zhengzhenxie
 * @version v1.0
 * @since 2018-06-05
 */
public enum RESTFUL_ERROR {
    /*=============================== 参数校验异常相关 ===============================*/
    /**
     * 参数校验错误
     */
    PARAM_INVALID(4001000, ErrorMsg.PARAM_INVALID),
    /**
     * key或者对象不存在
     */
    OBJECT_NULL(4001001, ErrorMsg.OBJECT_NULL),
    /**
     * 参数值为空
     */
    VALUE_EMPTY(4001002, ErrorMsg.VALUE_EMPTY),
    /**
     * 参数值为NULL
     */
    VALUE_NULL(4001003, ErrorMsg.VALUE_NULL),


    /*=============================== 权限异常相关 ===============================*/
    /**
     * 禁止访问
     */
    INVALID_ACCESS(4031000, ErrorMsg.INVALID_ACCESS),
    /**
     * Access-Token 为空
     */
    ACCESS_TOKEN_VALUE_EMPTY(4031001, String.format("'%s' value must not be empty", MicroConstants.ACCESS_TOKEN_FLAG)),

    /*=============================== 资源未找到异常相关 ===============================*/
    /**
     * 资源未找到
     */
    RESOURCE_NOT_FOUND(4041000, "%s"),
    /**
     * URI找不到
     */
    URI_NOT_FOUND(4041001, "requested path: '%s' not found"),

    /*=============================== XX不支持异常相关 ===============================*/
    /**
     * 请求方法不支持
     */
    METHOD_NOT_ALLOWED(4051000, "method '%s' not allowed,requested path: '%s'"),


    /*=============================== 服务器异常相关 ===============================*/
    /**
     * 服务器发生异常
     */
    SERVICE_EXCEPTION(5031001, ErrorMsg.SERVICE_EXCEPTION);

    private final int code;
    private final String msg;

    RESTFUL_ERROR(int code, String msg) {
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
