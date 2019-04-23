package com.sic777.dubbo.bean;


import com.sic777.dubbo.exception.RpcException;
import com.sic777.dubbo.exception.RpcExceptionType;

import java.io.Serializable;

/**
 * <p>Rpc响应实体</p>
 *
 * @author Zhengzhenxie
 * @version v1.0
 * @since 2018-01-13 14:02
 */
public final class RpcResponse<T> implements Serializable {
    private static final long serialVersionUID = -7402179064110127428L;

    /**
     * 响应状态
     */
    private final RpcResponseStatus status;
    /**
     * 响应数据
     */
    private final T data;
    /**
     * 异常实体,当响应状态为失败时才存在
     */
    private final RpcException exception;

    /**
     * 仅返回Rpc状态(成功)
     */
    public RpcResponse() {
        this.status = RpcResponseStatus.SUCCESS;
        this.data = null;
        this.exception = null;
    }

    /**
     * Rpc成功,返回数据
     *
     * @param t
     */
    public RpcResponse(T t) {
        this.status = RpcResponseStatus.SUCCESS;
        this.data = t;
        this.exception = null;
    }

    /**
     * Rpc异常,返回异常信息(不带错误码)
     * 注意：ISV不需要抛出SERVICE_EXCEPTION、CLIENT_EXCEPTION等两种类型的错误，这是属于系统级别的错误(如发生空指针),已经被过滤器统一处理了。
     *
     * @param rpcExceptionType
     * @param errorMsg
     * @param extendParams
     */
    public RpcResponse(RpcExceptionType rpcExceptionType, String errorMsg, Object... extendParams) {
        this.status = RpcResponseStatus.FAILURE;
        this.data = null;
        this.exception = new RpcException(rpcExceptionType, errorMsg, extendParams);
    }

    /**
     * Rpc异常,返回异常信息(自定义错误码)
     * 注意：ISV不需要抛出SERVICE_EXCEPTION、CLIENT_EXCEPTION等两种类型的错误，这是属于系统级别的错误(如发生空指针),已经被过滤器统一处理了。
     *
     * @param errorCode
     * @param errorMsg
     * @param extendParams
     */
    public RpcResponse(long errorCode, String errorMsg, Object... extendParams) {
        this.status = RpcResponseStatus.FAILURE;
        this.data = null;
        this.exception = new RpcException(errorCode, errorMsg, extendParams);
    }

    public RpcResponseStatus getStatus() {
        return status;
    }

    public T getData() {
        return data;
    }

    public RpcException getException() {
        return exception;
    }

    @Override
    public String toString() {
        return "RpcResponse{" +
                "status=" + status +
                ", data=" + data +
                ", exception=" + exception +
                '}';
    }
}
