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
final class Rest400Exception extends AbstractRestException {

    private static final long serialVersionUID = -2097486976527779486L;


    Rest400Exception(long code, String msg) {
        super(code, msg, null);
    }

    Rest400Exception(Enumeration.Value error, Object... format) {
        super(error, format);
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.BAD_REQUEST;
    }

}