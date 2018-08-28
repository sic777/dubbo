package com.sic777.common.exception;

import com.sic777.common.constants.BaseConstant;
import com.sic777.common.constants.ErrorMsg;
import com.sic777.utils.StringUtil;

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

    /**
     * 根据错误码返回错误类对象
     *
     * @param code 错误码
     * @return
     */
    public static ExceptionType parse(long code) {
        String codeStr = StringUtil.getString(code);
        if (codeStr.startsWith("1") || codeStr.startsWith("400")) {
            return ExceptionType.EXCEPTION_400;
        } else if (codeStr.startsWith("2") || codeStr.startsWith("403")) {
            return ExceptionType.EXCEPTION_403;
        } else if (codeStr.startsWith("3") || codeStr.startsWith("404")) {
            return ExceptionType.EXCEPTION_404;
        } else if (codeStr.startsWith("4") || codeStr.startsWith("405")) {
            return ExceptionType.EXCEPTION_405;
        } else {
            return ExceptionType.EXCEPTION_503;
        }
    }

    /**
     * <p>
     * '参数校验错误'异常错误码(400...)
     * 说明：使用者的错误码从11000xxxxx开始，10000xxxxx~10999xxxxx位为系统保留(xxxxx可以用作系统标识,也可为空)
     *
     * @author sic777
     * @since 0.0.1
     */
    public enum ParamExceptionType {
        /**
         * 参数校验错误
         */
        PARAM_INVALID(10000, ErrorMsg.PARAM_INVALID),
        /**
         * key或者对象不存在
         */
        OBJECT_NULL(10001, ErrorMsg.OBJECT_NULL),
        /**
         * 参数值为空
         */
        VALUE_EMPTY(10002, ErrorMsg.VALUE_EMPTY),
        /**
         * 参数值为NULL
         */
        VALUE_NULL(10003, ErrorMsg.VALUE_NULL);

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
     * '认证/权限错误'异常错误码(403...)
     * 说明：使用者的错误码从21000xxxxx开始，20000xxxxx~20999xxxxx位为系统保留(xxxxx可以用作系统标识,也可为空)
     *
     * @author sic777
     * @since 0.0.1
     */
    public enum AuthenticationExceptionType {
        /**
         * 禁止访问
         */
        INVALID_ACCESS(20000, ErrorMsg.INVALID_ACCESS),
        /**
         * Access-Token 为空
         */
        ACCESS_TOKEN_VALUE_EMPTY(20001, String.format(ErrorMsg.VALUE_EMPTY, BaseConstant.ACCESS_TOKEN_FLAG));
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
     * '资源未找到'异常错误码(404...)
     * 说明：使用者的错误码从31000xxxxx开始，30000xxxxx~30999xxxxx位为系统保留(xxxxx可以用作系统标识,也可为空)
     *
     * @author sic777
     * @since 0.0.1
     */
    public enum NotFoundExceptionType {
        /**
         * URL未找到
         */
        URL_NOT_FOUND(30000, ErrorMsg.URL_NOT_FOUND),
        /**
         * 资源未找到
         */
        RESOURCE_NOT_FOUND(30001, "");

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
     * '不支持的操作'异常错误码(405...)
     * 说明：使用者的错误码从41000xxxxx开始，40000xxxxx~40999xxxxx位为系统保留(xxxxx可以用作系统标识,也可为空)
     *
     * @author sic777
     * @since 0.0.1
     */
    public enum NotAllowExceptionType {
        /**
         * 请求方法不支持
         */
        METHOD_NOT_ALLOW(40000, ErrorMsg.METHOD_NOT_ALLOWED),;

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
     * '服务器不可用'异常错误码(503...)
     * 说明：使用者的错误码从51000xxxxx开始，50000xxxxx~50999xxxxx位为系统保留(xxxxx可以用作系统标识,也可为空)
     *
     * @author sic777
     * @since 0.0.1
     */
    public enum ServiceUnavailableExceptionType {
        /**
         * 服务不可用
         */
        SERVICE_UNAVAILABLE(50000, ErrorMsg.SERVICE_EXCEPTION);
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
