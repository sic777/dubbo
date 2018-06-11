package com.sic777.microservice.controller;

import com.alibaba.fastjson.JSONObject;
import com.sic777.microservice.constants.MicroConstants;
import com.sic777.microservice.exception.Rest503Exception;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * <p>controller抽象类</p>
 *
 * @author Zhengzhenxie
 * @version v1.0
 * @since 2018-04-11
 */
public abstract class RestfulController extends SuperRestfulController {

    @Override
    protected final JSONObject getAccessTokenData() {
        return (JSONObject) this.getAttribute(MicroConstants.ACK_ATTRIBUTE_FLAG);
    }

    @Override
    protected void rest200(Object obj) {
        try {
            PrintWriter out = this.getResponse().getWriter();
            out.print(JSONObject.toJSON(obj));
            out.flush();
        } catch (IOException e) {
            throw new Rest503Exception(e);
        }
    }

    /**
     * 获取参数
     *
     * @param key
     * @return
     */
    protected final String getParameter(String key) {
        HttpServletRequest request = this.getRequest();
        return request.getParameter(key);
    }

    /**
     * 获取request属性数据
     *
     * @param attributeKey
     * @return
     */
    protected final Object getAttribute(String attributeKey) {
        return this.getRequest().getAttribute(attributeKey);
    }

    /**
     * 获取request对象
     *
     * @return
     */
    protected final HttpServletRequest getRequest() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return requestAttributes.getRequest();
    }

    /**
     * 获取response对象
     *
     * @return
     */
    protected final HttpServletResponse getResponse() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return requestAttributes.getResponse();
    }

}
