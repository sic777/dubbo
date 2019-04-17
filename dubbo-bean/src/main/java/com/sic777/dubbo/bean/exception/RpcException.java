package com.sic777.dubbo.bean.exception;


import com.sic777.common.exception.SuperException;
import com.sic777.common.utils.proguard.NoProguard;

/**
 * <p>dubbo rpc异常</p>
 *
 * @author Zhengzhenxie
 * @version v1.0
 * @since 2018-06-05
 */
@NoProguard
public class RpcException extends SuperException {
    /**
     *
     */
    private static final long serialVersionUID = -8080433427358664852L;
    /**
     * 异常类型(与错误码互斥)
     */
    private final RpcExceptionType type;
    /**
     * 错误码(与异常类型互斥)
     */
    private final Long code;
    /**
     * 异常信息
     */
    private final String msg;
    /**
     * 扩充字段
     */
    private final Object[] extendParams;

    public RpcException(Long code, String msg, Object... extendParams) {
        super(msg);
        this.type = RpcExceptionType.UNKNOWN;
        this.code = code;
        this.msg = msg;
        this.extendParams = extendParams;
    }

    public RpcException(RpcExceptionType type, String msg, Object... extendParams) {
        super(msg);
        this.type = type;
        this.code = null;
        this.msg = msg;
        this.extendParams = extendParams;
    }

    public RpcExceptionType getType() {
        return type;
    }

    public Long getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public Object[] getExtendParams() {
        return extendParams;
    }
}
