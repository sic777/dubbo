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
public final class Rest403Exception extends RestException {

    private static final long serialVersionUID = 3487325839481274723L;


    public Rest403Exception(long code, String msg) {
        this(code, msg, true);
    }

    public Rest403Exception(long code, String msg, boolean log) {
        super(code, msg, log, null);
    }

    public Rest403Exception(Enumeration.Value error, boolean log, Object... format) {
        super(error, log, format);
    }

    @Override
    public int getHttpStatus() {
        return 403;
    }
}
