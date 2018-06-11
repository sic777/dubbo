package com.sic777.microservice.filters;


import com.sic777.microservice.constants.HttpConstants;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebFilter(urlPatterns = "/*")
public class RequestMethodFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        String method = httpRequest.getMethod();
        httpResponse.setStatus(HttpServletResponse.SC_OK);
        httpResponse.setContentType(HttpConstants.CONTENT_TYPE);
        String originUrl = httpRequest.getHeader(HttpConstants.ORIGIN);
        if (originUrl == null) {
            originUrl = HttpConstants.ALL_ORIGIN;
        }

        httpResponse.setHeader(HttpConstants.HEADER_KEY_CONNECTION, HttpConstants.HEADER_VALUE_CONNECTION);
        httpResponse.setHeader(HttpConstants.HEADER_KEY_ALLOW_METHODS, HttpConstants.HEADER_VALUE_ALLOW_METHODS);
        httpResponse.setHeader(HttpConstants.HEADER_KEY_ALLOW_HEADERS, HttpConstants.HEADER_VALUE_ALLOW_HEADERS);
        httpResponse.setHeader(HttpConstants.HEADER_KEY_ALLOW_CREDENTIALS, HttpConstants.HEADER_VALUE_ALLOW_CREDENTIALS);
        httpResponse.setHeader(HttpConstants.HEADER_KEY_ALLOW_ORIGIN, originUrl);
        //httpResponse.addHeader("Transfer-Encoding", "chunked");
        if (HttpConstants.OPTIONS_METHOD.equals(method.toUpperCase())) {
            httpResponse.getWriter().append("").flush();
            return;
        } else {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
    }

}
