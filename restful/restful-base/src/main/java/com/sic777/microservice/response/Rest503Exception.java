package com.sic777.microservice.response;


import com.sic777.common.constants.ErrorMsg;

/**
 * <p></p>
 *
 * @author Zhengzhenxie<br>
 * <br>2017-12-18
 * @version v1.0
 * @since 1.7
 */
final class Rest503Exception extends AbstractRestException {
    private static final long serialVersionUID = -9219530146399136414L;

    Rest503Exception(Throwable throwable) {
        super(HttpStatus.SERVICE_UNAVAILABLE.value(), ErrorMsg.SERVICE_EXCEPTION, throwable);
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.SERVICE_UNAVAILABLE;
    }
}
