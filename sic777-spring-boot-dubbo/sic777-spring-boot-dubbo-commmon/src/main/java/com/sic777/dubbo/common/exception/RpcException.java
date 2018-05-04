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

    public RpcException() {
        super(String.format("an exception occurs when the server '%s' is invoked.", RemoteHostUtil.getHost()));
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}
