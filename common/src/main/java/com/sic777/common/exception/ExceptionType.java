package com.sic777.common.exception;

import com.sic777.common.constants.BaseConstant;
import com.sic777.common.constants.ErrorMsg;
import com.sic777.common.utils.lang.StringUtil;
import com.sic777.common.utils.proguard.NoProguard;

/**
 * <p>异常枚举
 *
 * @author sic777
 * @since 0.0.1
 */
@NoProguard
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

    /**
     * 根据错误码返回错误类对象
     *
     * @param code 错误码
     * @return
     */
    @NoProguard
    public static ExceptionType parse(long code) {
        String codeStr = StringUtil.getString(code);
        if (codeStr.startsWith("1")) {
            return ExceptionType.EXCEPTION_400;
        } else if (codeStr.startsWith("2")) {
            return ExceptionType.EXCEPTION_403;
        } else if (codeStr.startsWith("3")) {
            return ExceptionType.EXCEPTION_404;
        } else if (codeStr.startsWith("4")) {
            return ExceptionType.EXCEPTION_405;
        } else {
            return ExceptionType.EXCEPTION_503;
        }
    }

    /**
     * <p>
     * '参数校验错误'异常错误码
     * <p>
     * 格式：(f|xxxx|yyyy)
     * <p>
     * f:错误类型标识
     * xxxx:系统标识（0000为系统默认保留，从0001开始）
     * yyyy：错误码（0000~9999）
     *
     * @author sic777
     * @since 0.0.1
     */
    @NoProguard
    public enum ParamExceptionType {
        /**
         * 参数校验错误
         */
        PARAM_INVALID(100000000, ErrorMsg.PARAM_INVALID),
        /**
         * key或者对象不存在
         */
        OBJECT_NULL(100000001, ErrorMsg.OBJECT_NULL),
        /**
         * 参数值为空
         */
        VALUE_EMPTY(100000002, ErrorMsg.VALUE_EMPTY),
        /**
         * 参数值为NULL
         */
        VALUE_NULL(100000003, ErrorMsg.VALUE_NULL);

        private final long id;
        private final String msg;

        ParamExceptionType(long id, String msg) {
            this.id = id;
            this.msg = msg;
        }

        public long getId() {
            return id;
        }

        public String getMsg() {
            return msg;
        }
    }

    /**
     * <p>
     * '认证/权限错误'异常错误码
     * <p>
     * 格式：(f|xxxx|yyyy)
     * <p>
     * f:错误类型标识
     * xxxx:系统标识（0000为系统默认保留，从0001开始）
     * yyyy：错误码（0000~9999）
     *
     * @author sic777
     * @since 0.0.1
     */
    @NoProguard
    public enum AuthenticationExceptionType {
        /**
         * 禁止访问
         */
        INVALID_ACCESS(200000000, ErrorMsg.INVALID_ACCESS),
        /**
         * Access-Token 为空
         */
        ACCESS_TOKEN_VALUE_EMPTY(200000001, String.format(ErrorMsg.VALUE_EMPTY, BaseConstant.ACCESS_TOKEN_FLAG));
        private final long id;
        private final String msg;

        AuthenticationExceptionType(long id, String msg) {
            this.id = id;
            this.msg = msg;
        }

        public long getId() {
            return id;
        }

        public String getMsg() {
            return msg;
        }
    }

    /**
     * <p>
     * '资源未找到'异常错误码
     * <p>
     * 格式：(f|xxxx|yyyy)
     * <p>
     * f:错误类型标识
     * xxxx:系统标识（0000为系统默认保留，从0001开始）
     * yyyy：错误码（0000~9999）
     *
     * @author sic777
     * @since 0.0.1
     */
    @NoProguard
    public enum NotFoundExceptionType {
        /**
         * URL未找到
         */
        URL_NOT_FOUND(300000000, ErrorMsg.URL_NOT_FOUND),
        /**
         * 资源未找到
         */
        RESOURCE_NOT_FOUND(300000001, "resource not found");

        private final long id;
        private final String msg;

        NotFoundExceptionType(long id, String msg) {
            this.id = id;
            this.msg = msg;
        }

        public long getId() {
            return id;
        }

        public String getMsg() {
            return msg;
        }
    }

    /**
     * <p>
     * '不支持的操作'异常错误码
     * <p>
     * 格式：(f|xxxx|yyyy)
     * <p>
     * f:错误类型标识
     * xxxx:系统标识（0000为系统默认保留，从0001开始）
     * yyyy：错误码（0000~9999）
     *
     * @author sic777
     * @since 0.0.1
     */
    @NoProguard
    public enum NotAllowExceptionType {
        /**
         * 请求方法不支持
         */
        METHOD_NOT_ALLOW(400000000, ErrorMsg.METHOD_NOT_ALLOWED),;

        private final long id;
        private final String msg;

        NotAllowExceptionType(long id, String msg) {
            this.id = id;
            this.msg = msg;
        }

        public long getId() {
            return id;
        }

        public String getMsg() {
            return msg;
        }
    }

    /**
     * <p>
     * '服务器不可用'异常错误码
     * <p>
     * 格式：(f|xxxx|yyyy)
     * <p>
     * f:错误类型标识
     * xxxx:系统标识（0000为系统默认保留，从0001开始）
     * yyyy：错误码（0000~9999）
     *
     * @author sic777
     * @since 0.0.1
     */
    @NoProguard
    public enum ServiceUnavailableExceptionType {
        /**
         * 服务不可用
         */
        SERVICE_UNAVAILABLE(500000000, ErrorMsg.SERVICE_EXCEPTION);
        private final long id;
        private final String msg;

        ServiceUnavailableExceptionType(long id, String msg) {
            this.id = id;
            this.msg = msg;
        }

        public long getId() {
            return id;
        }

        public String getMsg() {
            return msg;
        }

    }
}
