package com.sic777.microservice.exception;

import com.sic777.microservice.exception.error.SystemException;

/**
 * <p></p>
 *
 * @author Zhengzhenxie<br>
 * <br>2017-12-18
 * @version v1.0
 * @since 1.7
 */
public final class Rest503Exception extends RestException {
    private static final long serialVersionUID = -9219530146399136414L;

    public Rest503Exception(Throwable throwable, boolean log) {
        super(SystemException.SERVICE_EXCEPTION(), log, throwable);
    }

    @Override
    public int getHttpStatus() {
        return 503;
    }
}
