package com.sic777.microserver.exception;

import com.sic777.microserver.error.ERROR_ENUM;
import org.springframework.http.HttpStatus;

/**
 * <p></p>
 *
 * @author Zhengzhenxie<br>
 *         <br>2017-12-18
 * @version v1.0
 * @since 1.7
 */
public final class Rest503Exception extends RestException {

    public Rest503Exception() {
        super(ERROR_ENUM.SERVICE_EXCEPTION.getCode(), ERROR_ENUM.SERVICE_EXCEPTION.getMsg());
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.SERVICE_UNAVAILABLE;
    }

}
