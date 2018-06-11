package com.sic777.microservice.exception.handler;

import com.sic777.microservice.exception.Rest503Exception;
import com.sic777.microservice.exception.error.RESTFUL_ERROR;
import com.sic777.microservice.exception.response.RestExceptionResponse;
import com.sic777.microservice.exception.RestException;
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

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 记录响应错误日志
     *
     * @param th
     */
    private void exceptionResponseLog(Throwable th) {
        logger.error("restful response error:", th);
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
        exceptionResponseLog(e);
        return this.restExceptionHandler(request, response, new Rest503Exception(null, false));
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
        response.setStatus(ex.getHttpStatus());
        if (ex.isLog()) {
            exceptionResponseLog(ex);
        }
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
        return new RestExceptionResponse(RESTFUL_ERROR.METHOD_NOT_ALLOWED, request.getMethod(), request.getRequestURI()).response();
    }
}
