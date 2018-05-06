package com.sic777.microserver.exception;

import org.springframework.http.HttpStatus;

/**
 * <p></p>
 *
 * @author Zhengzhenxie<br>
 *         <br>2017-12-18
 * @version v1.0
 * @since 1.7
 */
public final class Rest404Exception extends RestException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 161657807118141593L;

	public Rest404Exception(int code, String msg) {
        super(code, msg);
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.NOT_FOUND;
    }
}
