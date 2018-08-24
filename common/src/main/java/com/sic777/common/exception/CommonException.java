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
	 * 
	 */
	private static final long serialVersionUID = -6106546564795439880L;
	/**
     * 错误枚举(Scala)
     */
    private final Enumeration.Value error;
    /**
     * 格式化，用于String.format()
     */
    private final Object[] format;


    public CommonException(Enumeration.Value error, Object... format) {
        this.error = error;
        this.format = format;
    }

    public Enumeration.Value getError() {
        return error;
    }

    public Object[] getFormat() {
        return format;
    }

}
