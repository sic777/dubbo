package com.sic777.dubbo.common;


import com.sic777.dubbo.common.exception.RpcException;

import java.io.Serializable;

/**
 * <p>Rpc响应实体</p>
 *
 * @author Zhengzhenxie
 * @version v1.0
 * @since 2018-01-13 14:02
 */
public final class Response<T> implements Serializable {
    private static final long serialVersionUID = -7402179064110127428L;
    /**
     * 响应状态
     */
    private transient Status status = Status.SUCCESS;
    /**
     * 响应数据
     */
    private T data;
    /**
     * 错误信息
     */
    private transient Error error;

    /**
     * 成功，响应状态
     */
    public Response() {
    }

    /**
     * 成功，响应状态和数据
     *
     * @param data
     */
    public Response(T data) {
        this.data = data;
    }

    /**
     * 系统异常
     *
     * @param e
     * @return
     */
    @SuppressWarnings("rawtypes")
	public static Response exception(Throwable e) {
        Response rs = new Response<>();
        rs.setStatus(Status.FAILURE);
        rs.setError(new Error(e));
        return rs;
    }

    /**
     * rpc通用异常
     *
     * @return
     */
    @SuppressWarnings("rawtypes")
	public static Response rpcException() {
        Response rs = new Response<>();
        rs.setStatus(Status.FAILURE);
        rs.setError(new Error(new RpcException()));
        return rs;
    }


    /**
     * 业务异常
     *
     * @param errorCode
     * @param errorMsg
     * @return
     */
    @SuppressWarnings("rawtypes")
	public static Response bizException(int errorCode, String errorMsg) {
        Response rs = new Response<>();
        rs.setStatus(Status.FAILURE);
        rs.setError(new Error(errorCode, errorMsg));
        return rs;
    }

    public Status getStatus() {
        return status;
    }

    public T getData() {
        return data;
    }

    public Error getError() {
        return error;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setData(T data) {
        this.data = data;
    }

    public void setError(Error error) {
        this.error = error;
    }
}
