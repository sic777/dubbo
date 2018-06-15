package com.sic777.microservice.exception;


/**
 * <p></p>
 *
 * @author Zhengzhenxie<br>
 * <br>2017-12-18
 * @version v1.0
 * @since 1.7
 */
public final class Rest200 extends RestException {

    private static final long serialVersionUID = -1591048367290898210L;
    /**
     * 响应实体
     */
    private final Object obj;

    public Rest200(Object obj, boolean log) {
        super(0, null, log, null);
        this.obj = obj;
    }

    public Rest200(Object obj) {
        this(obj, false);
    }

    @Override
    public Object response() {
        return obj;
    }

    @Override
    public int getHttpStatus() {
        return 200;
    }

}
