package com.sic777.microserver.interceptor;


import com.sic777.microserver.constants.MicroConstants;
import com.sic777.microserver.error.ERROR_ENUM;
import com.sic777.microserver.exception.Rest403Exception;
import com.sic777.microserver.permission.AccessToken;
import com.sic777.microserver.permission.Permission;
import com.sic777.microserver.permission.RestPermission;
import com.sic777.microserver.spi.auth.IAuthSPI;
import com.sic777.utils.string.StringUtil;
import com.sic777.utils.permission.PermissionUtil;
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
public class AuthInterceptor implements HandlerInterceptor {

    @Autowired
    private IAuthSPI auth;

    /**
     * <p>
     * 权限注解优先级： 方法注解 > 类注解
     * 1. 类无,方法无 无需校验
     * 2. 类无,方法有 以方法为主
     * 3. 类有,方法无 以类为主
     * 4. 类有,方法有 以方法为主
     * </p>
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HandlerMethod hd = (HandlerMethod) handler;
        Object controller = hd.getBean();
        Permission methodPermission = hd.getMethodAnnotation(Permission.class);//方法权限注解
        if (methodPermission != null) {//2 || 4
            this.validate(request, methodPermission);
        } else {
            Permission classPermission = controller.getClass().getAnnotation(Permission.class);//类权限注解
            if (null != classPermission) {//1 || 3
                this.validate(request, classPermission);
            }
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

    /**
     * 验证权限
     *
     * @param request
     * @param permissionAnnotation
     * @throws Exception
     */
    private void validate(HttpServletRequest request, Permission permissionAnnotation) throws Exception {
        int permission = PermissionUtil.permission(permissionAnnotation.value());
        if ((RestPermission.ANYBODY & permission) == 0) {
            String tokenString = request.getHeader(MicroConstants.ACCESS_TOKEN_FLAG);
            if (StringUtil.isEmpty(tokenString)) {
                throw new Rest403Exception(ERROR_ENUM.ACCESS_TOKEN_EMPTY.getCode(), ERROR_ENUM.ACCESS_TOKEN_EMPTY.getMsg());
            }
            AccessToken accessToken = auth.parseToken(tokenString);
            if (accessToken == null || (accessToken.getPermission() & permission) == 0) {
                throw new Rest403Exception(ERROR_ENUM.INVALID_ACCESS.getCode(), ERROR_ENUM.INVALID_ACCESS.getMsg());
            }
            //将token信息携带给控制器
            request.setAttribute(MicroConstants.ACK_ATTRIBUTE_FLAG, accessToken);
        }
    }
}
