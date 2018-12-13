package com.sic777.restful.springboot.response.exception.handler;

import com.sic777.restful.base.response.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.sic777.restful.base.exception.ExceptionType.ServiceUnavailableExceptionType.*;
import static com.sic777.restful.base.exception.ExceptionType.NotAllowExceptionType.*;

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
        response.setStatus(HttpStatus.SERVICE_UNAVAILABLE.value());
        return ResponseManager.instance().getErrorResponseBody(SERVICE_UNAVAILABLE.getId(), SERVICE_UNAVAILABLE.getMsg());
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
        response.setStatus(HttpStatus.METHOD_NOT_ALLOWED.value());
        return ResponseManager.instance().getErrorResponseBody(METHOD_NOT_ALLOW.getId(), String.format(METHOD_NOT_ALLOW.getMsg(), request.getMethod(), request.getRequestURI()));
    }
}
