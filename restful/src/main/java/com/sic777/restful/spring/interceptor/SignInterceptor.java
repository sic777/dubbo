package com.sic777.restful.spring.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.sic777.common.utils.encrypt.md5.MD5Util;
import com.sic777.common.utils.lang.StringUtil;
import com.sic777.restful.base.response.ResponseManager;
import com.sic777.restful.spring.filters.wrapper.HttpHelper;
import com.sic777.restful.base.spi.sign.ISignSPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLDecoder;
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
            String requestId = request.getHeader("requestId");
            String timestamp = request.getHeader("timestamp");

            String sign = request.getHeader("sign");

            ResponseManager.instance().funcValidateValueNotEmpty(
                    new Object[]{requestId, timestamp, sign},
                    new String[]{"requestId", "timestamp", "sign"});

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

            sb.append("uri=").append(uri)
                    .append("&").append("requestId=").append(requestId)
                    .append("&").append("timestamp=").append(timestamp)
                    .append("&").append("salt=").append(salt);

            String q = request.getQueryString();
            if (StringUtil.isNotEmpty(q)) {
                String queryString = URLDecoder.decode(q, "UTF-8");
                if (StringUtil.isNotEmpty(queryString)) {
                    String[] query = queryString.split("&");
                    for (String que : query) {
                        sb.append("&").append(que);
                    }
                }
            }


            String body = HttpHelper.getBodyString(request);
            if (StringUtil.isNotEmpty(body)) {//暂时只先支持json object
                JSONObject bodyJson = JSONObject.parseObject(body);
                for (Map.Entry<String, Object> entry : bodyJson.entrySet()) {
                    sb.append("&").append(entry.getKey()).append("=").append(entry.getValue());
                }
            }
            String secret = signSPI.secret();
            sb.append("&").append(secret);

            String mySign = MD5Util.md5(sb.toString());
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
