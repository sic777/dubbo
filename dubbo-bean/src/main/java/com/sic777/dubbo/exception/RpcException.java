package com.sic777.dubbo.exception;


/**
 * <p>dubbo rpc异常</p>
 *
 * @author Zhengzhenxie
 * @version v1.0
 * @since 2018-06-05
 */
public class RpcException extends Exception {
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

    /**
     * 覆盖父方法,不爬栈,提升性能
     *
     * @return 异常对象
     */
    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}
