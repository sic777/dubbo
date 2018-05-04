package com.sic777.microserver.exception.handler;

import com.sic777.microserver.error.ERROR_ENUM;
import com.sic777.microserver.exception.RestException;
import com.sic777.microserver.exception.response.RestExceptionResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

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
    Logger logger = LoggerFactory.getLogger(this.getClass());

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
        logger.error("", e);
        response.setStatus(HttpStatus.SERVICE_UNAVAILABLE.value());
        ERROR_ENUM error = ERROR_ENUM.SERVICE_EXCEPTION;
        return new RestExceptionResponse(error.getCode(), error.getMsg()).response();
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
    @ExceptionHandler(value = RestException.class)
    public Object restExceptionHandler(HttpServletRequest request, HttpServletResponse response, Exception e) {
        RestException ex = (RestException) e;
        response.setStatus(ex.getHttpStatus().value());
        return ex.response();
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
        ERROR_ENUM error = ERROR_ENUM.METHOD_NOT_ALLOWED;
        return new RestExceptionResponse(error.getCode(), String.format(error.getMsg(), request.getMethod(), request.getRequestURI()));
    }
}
