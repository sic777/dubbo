package com.sic777.microservice.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sic777.microservice.constants.MicroConstants;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

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
     * @see com.sic777.microservice.response.ResponseManager#success(SuperRestfulController, Object)
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

    /**
     * 获取企业id
     *
     * @return
     */
    protected final String funcGetCorpId() {
        return getAccessTokenData().getString("corp_id");
    }

    /**
     * 获取企业成员id
     *
     * @return
     */
    protected final String funcGetMemberId() {
        return getAccessTokenData().getString("member_id");
    }

    /**
     * 获取成员岗位列表
     *
     * @return
     */
    protected final List<String> funcGetPositionIds() {
        JSONArray array = getAccessTokenData().getJSONArray("position_ids");
        return null != array ? array.toJavaList(String.class) : new ArrayList<>();
    }

    /**
     * 获取成员部门列表
     *
     * @return
     */
    protected final List<String> funcGetDepartmentIds() {
        JSONArray array = getAccessTokenData().getJSONArray("department_ids");
        return null != array ? array.toJavaList(String.class) : new ArrayList<>();
    }

    /**
     * 用户用户id
     *
     * @return
     */
    protected final Integer funcGetUserId() {
        return getAccessTokenData().getInteger("user_id");
    }

}
