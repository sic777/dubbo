package com.sic777.restful.springboot.interceptor;

import com.sic777.restful.base.response.ResponseManager;
import com.sic777.restful.base.spi.sign.Sign;
import com.sic777.restful.springboot.filters.wrapper.HttpHelper;
import com.sic777.restful.base.spi.sign.ISignSPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * <p>
 *
 * @author sic777
 * @version 0.0.1
 * @since 0.0.1
 */
@Component
public class SignInterceptor implements HandlerInterceptor {
    @Autowired(required = false)
    private ISignSPI signSPI;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (null != signSPI) {
            String requestId = request.getHeader("REQUEST-ID");
            String accessKey = request.getHeader("ACCESS-KEY");
            String timestamp = request.getHeader("TIMESTAMP");
            String sign = request.getHeader("SIGN");
            Map<String, String[]> params = request.getParameterMap();
            String uri = request.getRequestURI();
            String body = HttpHelper.getBodyString(request);

            ResponseManager.instance().funcValidateValueNotEmpty(
                    new Object[]{requestId, accessKey, timestamp, sign},
                    new String[]{"REQUEST-ID", "ACCESS-KEY", "TIMESTAMP", "SIGN"});

            return signSPI.preHandle(new Sign(requestId, accessKey, timestamp, sign, params, uri, body));
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
