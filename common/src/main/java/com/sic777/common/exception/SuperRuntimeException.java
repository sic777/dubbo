package com.sic777.common.exception;

/**
 * <p>运行时异常基类</p>
 *
 * @author Zhengzhenxie
 * @version v1.0
 * @since 2018-06-15
 */
public abstract class SuperRuntimeException extends RuntimeException {
    private static final long serialVersionUID = 1957474075268725412L;

    public SuperRuntimeException() {
        super();
    }

    public SuperRuntimeException(String message) {
        super(message);
    }

    public SuperRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public SuperRuntimeException(Throwable cause) {
        super(cause);
    }

    public SuperRuntimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    /**
     * 覆盖父方法,不爬栈,提升性能
     *
     * @return
     */
    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }

}
