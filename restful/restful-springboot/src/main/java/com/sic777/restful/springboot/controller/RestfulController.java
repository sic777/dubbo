package com.sic777.restful.springboot.controller;

import com.sic777.restful.base.constants.MicroConstants;
import com.sic777.restful.base.controller.SuperRestfulController;
import com.alibaba.fastjson.JSONObject;
import com.sic777.utils.proguard.NoProguard;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * <p>controller抽象类</p>
 *
 * @author Zhengzhenxie
 * @version v1.0
 * @since 2018-04-11
 */
@NoProguard
public abstract class RestfulController extends SuperRestfulController {

    @Override
    protected final JSONObject getAccessTokenData() {
        return (JSONObject) this.getAttribute(MicroConstants.ACK_ATTRIBUTE_FLAG);
    }

    /**
     * @param writeObject
     * @throws Exception
     * @deprecated
     */
    protected void rest200(Object writeObject) throws Exception {
        success(writeObject);
    }

    /**
     * @param writeObject 输出的对象
     * @throws Exception
     * @see com.sic777.restful.base.response.ResponseManager#success(SuperRestfulController, Object)
     */
    @Override
    public void success(Object writeObject) throws Exception {
        PrintWriter out = this.getResponse().getWriter();
        out.print(writeObject);
        out.flush();
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
