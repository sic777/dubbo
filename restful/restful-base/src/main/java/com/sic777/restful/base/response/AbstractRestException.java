package com.sic777.restful.base.response;


import com.sic777.common.exception.SuperRuntimeException;

/**
 * <p></p>
 *
 * @author Zhengzhenxie<br>
 * <br>2017-12-18
 * @version v1.0
 * @since 1.7
 */
public abstract class AbstractRestException extends SuperRuntimeException {
    /**
     *
     */
    private static final long serialVersionUID = -70921703399466740L;
    /**
     * 错误码
     */
    private final long code;
    /**
     * 错误信息
     */
    private final String msg;

    public AbstractRestException(long code, String msg, Throwable throwable) {
        super(throwable);
        this.code = code;
        this.msg = msg;
    }

    public abstract HttpStatus getHttpStatus();

    public long getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

}
