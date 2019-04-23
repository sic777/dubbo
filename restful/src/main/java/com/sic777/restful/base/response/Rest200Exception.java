package com.sic777.restful.base.response;


/**
 * <p></p>
 *
 * @author Zhengzhenxie<br>
 * <br>2017-12-18
 * @version v1.0
 * @since 1.7
 */
final class Rest200Exception extends AbstractRestException {

    private static final long serialVersionUID = -1591048367290898210L;

    /**
     * error
     *
     * @param code
     * @param msg
     */
    Rest200Exception(long code, String msg) {
        super(code, msg, null);
    }


    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.OK;
    }

}
