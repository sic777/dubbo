package com.sic777.microservice.response;


import com.sic777.microservice.response.exception.error.ServiceUnavailableException;

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
        super(ServiceUnavailableException.SERVICE_UNAVAILABLE().id(),
                ServiceUnavailableException.SERVICE_UNAVAILABLE().toString(), throwable);
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.SERVICE_UNAVAILABLE;
    }
}
