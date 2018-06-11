package com.sic777.dubbo.exception;

/**
 * <p>rpc异常类型</p>
 *
 * @author Zhengzhenxie
 * @version v1.0
 * @since 2018-06-05
 */
public enum RpcExceptionType {
    /**
     * 未知错误
     */
    UNKNOWN,
    /**
     * 参数校验非法
     */
    PARAM_INVALID,
    /**
     * 权限不足,禁止访问
     */
    INVALID_ACCESS,
    /**
     * 资源未找到
     */
    RESOURCE_NOT_FOUND,
    /**
     * 服务端发生异常
     */
    SERVICE_EXCEPTION,
    /**
     * 客户端发生异常
     */
    CLIENT_EXCEPTION
}
