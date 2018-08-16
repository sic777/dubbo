package com.sic777.microservice.interceptor;


import com.alibaba.fastjson.JSONObject;
import com.sic777.common.constants.BaseConstant;
import com.sic777.microservice.constants.MicroConstants;
import com.sic777.microservice.permission.Permission;
import com.sic777.microservice.spi.auth.IAuthSPI;
import com.sic777.utils.container.tuple.TwoTuple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>鉴权拦截器</p>
 *
 * @author Zhengzhenxie
 * @version v1.0
 * @since 2018-01-24 18:01
 */
@Component
public class AuthInterceptor extends SuperAuthInterceptor implements HandlerInterceptor {

    @Autowired
    private IAuthSPI auth;

    /**
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod hd = (HandlerMethod) handler;
            Object controller = hd.getBean();
            Permission methodPermission = hd.getMethodAnnotation(Permission.class);//方法权限注解
            Permission classPermission = null;
            if (null == methodPermission) {
                classPermission = controller.getClass().getAnnotation(Permission.class);//类权限注解
            }
            String accessToken = request.getHeader(BaseConstant.ACCESS_TOKEN_FLAG);
            TwoTuple<JSONObject, Integer> tuple = super.validate(accessToken, methodPermission, classPermission, auth);
            //将token缓存的数据和解析出来的权限携带给控制器
            tuple.first.put(MicroConstants.PERMISSION_FLAG, tuple.second);
            request.setAttribute(MicroConstants.ACK_ATTRIBUTE_FLAG, tuple.first);
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
    }
}
