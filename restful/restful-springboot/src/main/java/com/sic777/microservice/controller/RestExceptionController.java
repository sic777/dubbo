package com.sic777.microservice.controller;

import com.sic777.microservice.exception.error.RESTFUL_ERROR;
import com.sic777.microservice.exception.response.RestExceptionResponse;
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
        return 404 == (Integer) ea.get("status")
                ? new RestExceptionResponse(RESTFUL_ERROR.URI_NOT_FOUND, ea.get("path"))
                : new RestExceptionResponse(RESTFUL_ERROR.SERVICE_EXCEPTION);
    }

    @Override
    public String getErrorPath() {
        return PATH;
    }
}
