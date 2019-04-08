package com.sic777.restful.springboot.interceptor;

import com.sic777.common.utils.encrypt.md5.MD5Util;
import com.sic777.common.utils.lang.StringUtil;
import com.sic777.restful.base.response.ResponseManager;
import com.sic777.restful.springboot.filters.wrapper.HttpHelper;
import com.sic777.restful.base.spi.sign.ISignSPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLDecoder;

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
            String requestId = request.getHeader("_requestId");
            String timestamp = request.getHeader("_timestamp");

            String sign = request.getParameter("_sign");

            ResponseManager.instance().funcValidateValueNotEmpty(
                    new Object[]{requestId, timestamp, sign},
                    new String[]{"_requestId", "_timestamp", "_sign"});

            if (timestamp.trim().length() != 13) {
                ResponseManager.instance().throwParamInvalidException("timestamp format error.");
            }

            try {
                Long time = Long.parseLong(timestamp);
                long now = System.currentTimeMillis();
                if (now < time || now - time > 60000L) {
                    ResponseManager.instance().throwParamInvalidException("timestamp expired.");
                }
            } catch (NumberFormatException e) {
                ResponseManager.instance().throwParamInvalidException("timestamp format error.");
            }

            StringBuilder sb = new StringBuilder();

            String uri = request.getRequestURI();
            String salt = signSPI.salt();

            sb.append("_uri=").append(uri)
                    .append("_requestId=").append(requestId)
                    .append("_timestamp").append(timestamp)
                    .append("_salt=").append(salt);

            String queryString = URLDecoder.decode(request.getQueryString(), "UTF-8");
            String[] query = queryString.split("&");
            for (String que : query) {
                if (que.startsWith("_sign")) {
                    continue;
                }
                sb.append(que);
            }

            String body = HttpHelper.getBodyString(request);
            if (StringUtil.isNotEmpty(body)) {
                sb.append("_body=").append(MD5Util.md5(body));
            }
            String secret = signSPI.secret();
            sb.append(secret);

            String sbString = sb.toString();
            String mySign = MD5Util.md5(sbString);
            if (!mySign.equals(sign)) {
                ResponseManager.instance().throwParamInvalidException("sign error.");
            }
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
