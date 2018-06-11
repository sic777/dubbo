package com.sic777.dubbo.exception;

/**
 * <p>dubbo rpc异常</p>
 *
 * @author Zhengzhenxie
 * @version v1.0
 * @since 2018-06-05
 */
public class RpcException extends RuntimeException {
	private static final long serialVersionUID = 1957474075268725412L;
	/**
     * 异常类型
     */
    private final RpcExceptionType type;
    /**
     * 异常信息
     */
    private final String msg;

    public RpcException(RpcExceptionType type, String msg) {
        super(msg);
        this.type = type;
        this.msg = msg;
    }

    public RpcExceptionType getType() {
        return type;
    }

    public String getMsg() {
        return msg;
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}
