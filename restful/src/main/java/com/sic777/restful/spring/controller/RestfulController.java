package com.sic777.restful.spring.controller;

import com.sic777.common.utils.lang.StringUtil;
import com.sic777.restful.base.constants.RestConstants;
import com.sic777.restful.base.controller.SuperRestfulController;
import com.alibaba.fastjson.JSONObject;
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
public abstract class RestfulController extends SuperRestfulController {
    /**
     * 当前页码标识
     */
    private final static String PAGE_NO_FLAG = "pageNo";
    /**
     * 每页数量标识
     */
    private final static String PAGE_SIZE_FLAG = "pageSize";
    /**
     * 默认当前页码
     */
    private final static int DEFAULT_PAGE_NO = 1;
    /**
     * 默认每页数据量
     */
    private final static int DEFAULT_PAGE_SIZE = 10;
    /**
     * 分页最大查询数据量
     */
    private final static int MAX_PAGE_SIZE = 1000;

    /**
     * get page number
     *
     * @return
     */
    protected final int getPageNo() {
        String pageNo = this.getParameter(PAGE_NO_FLAG);
        if (StringUtil.isEmpty(pageNo)) {
            return DEFAULT_PAGE_NO;
        }
        int pageNoInt = Integer.parseInt(pageNo);
        return pageNoInt <= DEFAULT_PAGE_NO ? DEFAULT_PAGE_NO : pageNoInt;
    }

    /**
     * get page size
     *
     * @return
     */
    protected final int getPageSize() {
        String pageSize = this.getParameter(PAGE_SIZE_FLAG);
        if (StringUtil.isEmpty(pageSize)) {
            return DEFAULT_PAGE_SIZE;
        }
        int pageSizeInt = Integer.parseInt(pageSize);
        if (pageSizeInt <= 0) {
            return DEFAULT_PAGE_SIZE;
        }
        return pageSizeInt > MAX_PAGE_SIZE ? MAX_PAGE_SIZE : pageSizeInt;
    }

    @Override
    protected final JSONObject getAccessTokenData() {
        return (JSONObject) this.getAttribute(RestConstants.ACK_ATTRIBUTE_FLAG);
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

    /**
     * 获取请求IP
     *
     * @return
     */
    protected final String getRequestIp() {
        HttpServletRequest request = this.getRequest();
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}
