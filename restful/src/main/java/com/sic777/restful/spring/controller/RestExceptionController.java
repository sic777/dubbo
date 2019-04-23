package com.sic777.restful.spring.controller;

import com.sic777.restful.base.response.HttpStatus;
import com.sic777.restful.base.response.ResponseManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

import static com.sic777.restful.base.exception.ExceptionType.NotFoundExceptionType.*;
import static com.sic777.restful.base.exception.ExceptionType.ServiceUnavailableExceptionType.*;

/**
 * <p></p>
 *
 * @author Zhengzhenxie
 * @version v1.0
 * @since 2018-04-11
 */
@RestController
public class RestExceptionController extends RestfulController implements ErrorController {
    private static final String PATH = "/error";

    @Autowired
    private ErrorAttributes errorAttributes;

    /**
     * 统一错误页面
     *
     * @param request
     * @return
     */
    @RequestMapping(value = PATH, produces = {MediaType.APPLICATION_JSON_VALUE})
    private Object error(HttpServletRequest request) {
        RequestAttributes requestAttributes = new ServletRequestAttributes(request);
        Map<String, Object> ea = errorAttributes.getErrorAttributes(requestAttributes, false);
        return HttpStatus.NOT_FOUND.value() == (Integer) ea.get("status")
                ? ResponseManager.instance().getErrorResponseBody(URL_NOT_FOUND.getId(), String.format(URL_NOT_FOUND.getMsg(), ea.get("path")))
                : ResponseManager.instance().getErrorResponseBody(SERVICE_UNAVAILABLE.getId(), SERVICE_UNAVAILABLE.getMsg());
    }

    @Override
    public String getErrorPath() {
        return PATH;
    }
}
