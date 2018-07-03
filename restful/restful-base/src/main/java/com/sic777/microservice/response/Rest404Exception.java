package com.sic777.microservice.response;


import scala.Enumeration;

/**
 * <p></p>
 *
 * @author Zhengzhenxie<br>
 * <br>2017-12-18
 * @version v1.0
 * @since 1.7
 */
final class Rest404Exception extends AbstractRestException {

    private static final long serialVersionUID = 161657807118141593L;

    Rest404Exception(long code, String msg) {
        super(code, msg, null);
    }

    Rest404Exception(Enumeration.Value error, Object... format) {
        super(error, format);
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.NOT_FOUND;
    }

}
