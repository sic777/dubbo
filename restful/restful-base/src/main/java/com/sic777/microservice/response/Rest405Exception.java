package com.sic777.microservice.response;

import scala.Enumeration;

/**
 * @author Zhengzhenxie
 * @version v1.0
 * @since 2018-08-23
 */
final class Rest405Exception extends AbstractRestException {

    private static final long serialVersionUID = 161657907218141593L;

    Rest405Exception(long code, String msg) {
        super(code, msg, null);
    }

    Rest405Exception(Enumeration.Value error, Object... format) {
        super(error, format);
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.METHOD_NOT_ALLOWED;
    }

}

