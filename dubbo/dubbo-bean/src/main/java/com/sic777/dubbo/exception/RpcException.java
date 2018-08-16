package com.sic777.dubbo.exception;


import com.sic777.common.exception.SuperRuntimeException;

/**
 * <p>dubbo rpc异常</p>
 *
 * @author Zhengzhenxie
 * @version v1.0
 * @since 2018-06-05
 */
public class RpcException extends SuperRuntimeException {
    /**
     * 异常类型
     */
    private final RpcExceptionType type;
    /**
     * 异常信息
     */
    private final String msg;
    /**
     * 扩充字段
     */
    private final Object[] extendParams;

    public RpcException(RpcExceptionType type, String msg, Object... extendParams) {
        super(msg);
        this.type = type;
        this.msg = msg;
        this.extendParams = extendParams;
    }

    public RpcExceptionType getType() {
        return type;
    }

    public String getMsg() {
        return msg;
    }

    public Object[] getExtendParams() {
        return extendParams;
    }
}
