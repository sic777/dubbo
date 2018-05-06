package com.sic777.dubbo.common.exception;


import com.sic777.utils.other.RemoteHostUtil;

/**
 * <p></p>
 *
 * @author Zhengzhenxie
 * @version v1.0
 * @since 2018-04-23
 */
public class RpcException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 7229323756899328038L;

	public RpcException() {
        super(String.format("an exception occurs when the server '%s' is invoked.", RemoteHostUtil.getHost()));
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}
