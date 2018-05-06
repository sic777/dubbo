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
public final class Rest200 extends RestException {

    /**
	 * 
	 */
	private static final long serialVersionUID = -1591048367290898210L;
	/**
     * 响应实体
     */
    private final Object obj;

    public Rest200(Object obj) {
        super(0, null);
        this.obj = obj;
    }

    @Override
    public Object response() {
        return obj;
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.OK;
    }

}
