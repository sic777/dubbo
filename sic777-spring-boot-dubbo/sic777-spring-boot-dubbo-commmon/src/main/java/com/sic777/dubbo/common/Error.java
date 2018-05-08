package com.sic777.dubbo.common;

import java.io.Serializable;


/**
 * <p>错误实体</p>
 *
 * @author Zhengzhenxie
 * @version v1.0
 * @since 2018-04-23
 */
public class Error implements Serializable{
    /**
     * 错误码
     */
    private int errorCode;
    /**
     * 错误信息
     */
    private String errorMsg;
    /**
     * 异常
     */
    private Throwable throwable;

    /**
     * 业务异常
     *
     * @param errorCode
     * @param errorMsg
     */
    public Error(int errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    /**
     * 系统异常
     *
     * @param throwable
     */
    public Error(Throwable throwable) {
        this.errorCode = 5031001;
        this.errorMsg = "service unavailable";
        this.throwable = throwable;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public Throwable getThrowable() {
        return throwable;
    }
}
