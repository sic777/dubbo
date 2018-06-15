package com.sic777.microservice.exception.handler;

import com.sic777.common.exception.CommonException;
import com.sic777.microservice.exception.*;
import com.sic777.microservice.exception.error.ExceptionCode;
import com.sic777.microservice.exception.error.NotAllowedException;
import com.sic777.microservice.exception.response.RestExceptionResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
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
        int id = v.id();
        boolean isLog = commonException.isLog();
        Object format = commonException.getFormat();
        //根据id解析抛出异常
        Object obj = ExceptionCode.parse(id);
        if (obj instanceof ExceptionCode.ParamException) {//400
            return this.restExceptionHandler(request, response, new Rest400Exception(v, isLog, format));
        } else if (obj instanceof ExceptionCode.AuthenticationException) {//403
            return this.restExceptionHandler(request, response, new Rest403Exception(v, isLog, format));
        } else if (obj instanceof ExceptionCode.NotFoundException) {//404
            return this.restExceptionHandler(request, response, new Rest404Exception(v, isLog, format));
        } else if (obj instanceof ExceptionCode.NotAllowedException) {//405
            return this.methodNotSupportedExceptionHandler(request, response, e);
        }//503
        return this.restExceptionHandler(request, response, new Rest503Exception(e, isLog));
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
        return this.restExceptionHandler(request, response, new Rest503Exception(e, true));
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
            logger.error("restful response error:", ex);
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
        return new RestExceptionResponse(NotAllowedException.METHOD_NOT_ALLOWED(), request.getMethod(), request.getRequestURI()).response();
    }
}
