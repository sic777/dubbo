package com.sic777.microservice.exception;


import scala.Enumeration;

/**
 * <p></p>
 *
 * @author Zhengzhenxie<br>
 * <br>2017-12-18
 * @version v1.0
 * @since 1.7
 */
public final class Rest400Exception extends RestException {

    private static final long serialVersionUID = -2097486976527779486L;


    public Rest400Exception(long code, String msg) {
        this(code, msg, true);
    }

    public Rest400Exception(long code, String msg, boolean log) {
        super(code, msg, log, null);
    }

    public Rest400Exception(Enumeration.Value error, boolean log, Object... format) {
        super(error, log, format);
    }

    @Override
    public int getHttpStatus() {
        return 400;
    }

}
