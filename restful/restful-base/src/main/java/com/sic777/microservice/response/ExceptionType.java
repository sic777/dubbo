package com.sic777.microservice.response;

/**
 * <p></p>
 *
 * @author Zhengzhenxie
 * @version v1.0
 * @since 2018-07-03
 */
public enum ExceptionType {
    EXCEPTION_200(200),
    EXCEPTION_400(400),
    EXCEPTION_403(403),
    EXCEPTION_404(404),
    EXCEPTION_405(405),
    EXCEPTION_503(503);

    private final int code;

    ExceptionType(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
