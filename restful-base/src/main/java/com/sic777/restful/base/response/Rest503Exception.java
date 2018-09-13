package com.sic777.restful.base.response;


import com.sic777.common.exception.ExceptionType;

/**
 * <p></p>
 *
 * @author Zhengzhenxie<br>
 * <br>2017-12-18
 * @version v1.0
 * @since 1.7
 */
final class Rest503Exception extends AbstractRestException {
    private static final long serialVersionUID = -9219530446399116414L;

    Rest503Exception(Throwable throwable) {
        super(ExceptionType.ServiceUnavailableExceptionType.SERVICE_UNAVAILABLE.getId(),
                ExceptionType.ServiceUnavailableExceptionType.SERVICE_UNAVAILABLE.getMsg(), throwable);
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.SERVICE_UNAVAILABLE;
    }
}
