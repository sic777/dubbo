package com.sic777.microservice.response.exception.handler;

import com.sic777.common.constants.ErrorMsg;
import com.sic777.common.exception.CommonException;
import com.sic777.microservice.response.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import scala.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * <p>Rest异常处理器</p>
 *
 * @author Zhengzhenxie
 * @version v1.0
 * @since 2018-01-22 16:14
 */
@ControllerAdvice
public class RestExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    /**
     * 通用异常
     *
     * @param request
     * @param response
     * @param e
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = {CommonException.class})
    public Object commonExceptionHandler(HttpServletRequest request, HttpServletResponse response, Exception e) {
        CommonException commonException = (CommonException) e;
        Enumeration.Value v = commonException.getError();
        Object[] format = commonException.getFormat();
        ExceptionType exceptionType = ResponseManager.instance().getExceptionType(v);
        return this.restExceptionHandler(request, response, new AbstractRestException(v, format) {
            @Override
            public HttpStatus getHttpStatus() {
                return HttpStatus.valueOf(exceptionType.getCode());
            }
        });
    }

    /**
     * 系统异常
     *
     * @param request
     * @param response
     * @param e
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = {Exception.class, RuntimeException.class})
    public Object defaultExceptionHandler(HttpServletRequest request, HttpServletResponse response, Exception e) {
        logger.error("restful response error", e);
        return ResponseManager.instance().getErrorResponseBody(HttpStatus.SERVICE_UNAVAILABLE.value(), ErrorMsg.SERVICE_EXCEPTION);
    }

    /**
     * Rest异常
     *
     * @param request
     * @param response
     * @param e
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = AbstractRestException.class)
    public Object restExceptionHandler(HttpServletRequest request, HttpServletResponse response, Exception e) {
        AbstractRestException ex = (AbstractRestException) e;
        HttpStatus httpStatus = ex.getHttpStatus();
        boolean isFixed = ResponseManager.instance().getResponseBodyType() == ResponseBodyType.FIXED;
        if (isFixed && httpStatus != HttpStatus.SERVICE_UNAVAILABLE && httpStatus != HttpStatus.FORBIDDEN) {//除了503和403，Http状态码统一为200
            response.setStatus(HttpStatus.OK.value());
        } else {
            response.setStatus(httpStatus.value());
        }
        return ResponseManager.instance().getErrorResponseBody(ex.getCode(), ex.getMsg());
    }

    /**
     * 'http请求方法不支持'异常
     *
     * @param request
     * @param response
     * @param e
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    public Object methodNotSupportedExceptionHandler(HttpServletRequest request, HttpServletResponse response, Exception e) {
        int code = HttpStatus.METHOD_NOT_ALLOWED.value();
        String msg = "Method {%s} Not Allowed,URI {'%s'}";
        response.setStatus(code);
        return ResponseManager.instance().getErrorResponseBody(code, String.format(msg, request.getMethod(), request.getRequestURI()));
    }
}
