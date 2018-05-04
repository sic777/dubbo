package com.sic777.microserver.controller;

import com.alibaba.fastjson.JSONObject;
import com.sic777.dubbo.common.Error;
import com.sic777.dubbo.common.Response;
import com.sic777.dubbo.common.Status;
import com.sic777.microserver.constants.MicroConstants;
import com.sic777.microserver.exception.*;
import com.sic777.microserver.permission.AccessToken;
import com.sic777.utils.string.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>controller抽象类</p>
 *
 * @author Zhengzhenxie
 * @version v1.0
 * @since 2018-04-11
 */
public abstract class RestfulController {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 获取当前accessToken的权限
     *
     * @return
     */
    protected final int getPermission() {
        return this.getAccessToken().getPermission();
    }

    /**
     * 获取accessToken字符串
     *
     * @return
     */
    protected final String getAccessTokenString() {
        return this.getAccessToken().getAccessToken();
    }

    /**
     * 获取accessToken缓存数据
     *
     * @return
     */
    protected final JSONObject getAccessTokenData() {
        return this.getAccessToken().getData();
    }


    /**
     * 获取accessToken对象
     *
     * @return
     */
    protected final AccessToken getAccessToken() {
        return (AccessToken) this.getAttribute(MicroConstants.ACK_ATTRIBUTE_FLAG);
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
     * 处理rpc响应
     *
     * @param rs
     */
    protected final void processRpc(Response<?> rs) {
        if (rs.getStatus() == Status.FAILURE) {
            Error er = rs.getError();
            String code = StringUtil.getString(er.getErrorCode());
            if (code.startsWith("400")) {
                this.rest400(er.getErrorCode(), er.getErrorMsg());
            } else if (code.startsWith("403")) {
                this.rest403(er.getErrorCode(), er.getErrorMsg());
            } else if (code.startsWith("404")) {
                this.rest404(er.getErrorCode(), er.getErrorMsg());
            } else {
                logger.error("", er.getThrowable());
                this.rest503();
            }
        }
    }

    /**
     * 响应200
     *
     * @param obj
     */
    protected final void rest200(Object obj) {
        throw new Rest200(obj);
    }

    /**
     * 抛出400异常
     *
     * @param code
     * @param msg
     */
    protected final void rest400(int code, String msg) {
        throw new Rest400Exception(code, msg);
    }

    /**
     * 抛出403异常
     *
     * @param code
     * @param msg
     */
    protected final void rest403(int code, String msg) {
        throw new Rest403Exception(code, msg);
    }

    /**
     * 抛出404异常
     *
     * @param code
     * @param msg
     */
    protected final void rest404(int code, String msg) {
        throw new Rest404Exception(code, msg);
    }

    /**
     * 抛出503异常
     */
    protected final void rest503() {
        throw new Rest503Exception();
    }

}
