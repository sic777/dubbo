package com.sic777.common.exception;

import com.sic777.common.utils.proguard.NoProguard;

/**
 * <p>运行时异抽象类,所有运行时异常均可继承该类
 *
 * @author sic777
 * @since 0.0.1
 */
@NoProguard
public abstract class SuperRuntimeException extends RuntimeException {
    /**
     *
     */
    private static final long serialVersionUID = 1957474075268725412L;

    public SuperRuntimeException() {
        super();
    }

    /**
     * @param message 错误信息
     */
    public SuperRuntimeException(String message) {
        super(message);
    }

    /**
     * @param message 错误信息
     * @param cause   异常
     */
    public SuperRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @param cause 异常
     */
    public SuperRuntimeException(Throwable cause) {
        super(cause);
    }

    /**
     * @param message            错误信息
     * @param cause              异常
     * @param enableSuppression
     * @param writableStackTrace
     */
    public SuperRuntimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
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
