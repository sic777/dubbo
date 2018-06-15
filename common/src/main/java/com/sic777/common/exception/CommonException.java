package com.sic777.common.exception;

import scala.Enumeration;

/**
 * <p>通用异常</p>
 *
 * @author Zhengzhenxie
 * @version v1.0
 * @since 2018-06-15
 */
public class CommonException extends SuperRuntimeException {
    /**
     * 错误枚举(Scala)
     */
    private final Enumeration.Value error;
    /**
     * 格式化，用于String.format()
     */
    private final Object[] format;
    /**
     * 是否需要记录错误日志(默认为不打日志)
     */
    private final boolean log;


    public CommonException(Enumeration.Value error, boolean log, Object... format) {
        this.error = error;
        this.log = log;
        this.format = format;
    }

    public CommonException(Enumeration.Value error, Object... format) {
        this.error = error;
        this.log = false;
        this.format = format;
    }

    public Enumeration.Value getError() {
        return error;
    }

    public Object[] getFormat() {
        return format;
    }

    public boolean isLog() {
        return log;
    }
}
