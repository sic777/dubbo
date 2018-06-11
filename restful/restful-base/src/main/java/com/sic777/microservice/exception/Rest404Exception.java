package com.sic777.microservice.exception;

import com.sic777.microservice.exception.error.RESTFUL_ERROR;

/**
 * <p></p>
 *
 * @author Zhengzhenxie<br>
 * <br>2017-12-18
 * @version v1.0
 * @since 1.7
 */
public final class Rest404Exception extends RestException {

    private static final long serialVersionUID = 161657807118141593L;

    public Rest404Exception(long code, String msg) {
        this(code, msg, true);
    }

    public Rest404Exception(long code, String msg, boolean log) {
        super(code, msg, log, null);
    }

    public Rest404Exception(RESTFUL_ERROR ERROR, Object... format) {
        this(ERROR, false, format);
    }

    public Rest404Exception(RESTFUL_ERROR ERROR, boolean log, Object... format) {
        super(ERROR, log, format);
    }

    @Override
    public int getHttpStatus() {
        return 404;
    }

}
