package com.sic777.restful.springboot.interceptor;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sic777.common.annotation.MethodLock;
import com.sic777.common.utils.lang.StringUtil;
import com.sic777.logger.LoggerHelper;
import com.sic777.restful.base.constants.RestConstants;
import com.sic777.restful.base.response.AbstractRestException;
import com.sic777.restful.base.response.ResponseManager;
import com.sic777.restful.springboot.aop.BaseAop;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

import static com.sic777.restful.base.exception.ExceptionType.ParamExceptionType.*;

/**
 * <p>
 *
 * @author sic777
 * @version 0.0.1
 * @since 0.0.1
 */
@Aspect
@Configuration
public class LockMethodInterceptor extends BaseAop {

    @Around("execution(* *(..)) && @annotation(com.sic777.common.annotation.MethodLock)")
    public void before(ProceedingJoinPoint pjp) throws Exception {
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = signature.getMethod();
        MethodLock lock = method.getAnnotation(MethodLock.class);

        if (StringUtil.isEmpty(lock.value())) {
            String msg = "@MethodLock prefix error.";
            LoggerHelper.instance().error(msg);
            ResponseManager.instance().throwRest503Exception(new Exception(msg));
        }

        Object callerId = getCallerId();
        System.out.println(callerId);
        boolean rs = super.lock(method, lock, pjp.getArgs(), callerId);
        if (!rs) {
            ResponseManager.instance().throwRestException(FREQUENT_OPERATION.getId(), FREQUENT_OPERATION.getMsg());
        }
    }

    @AfterThrowing(value = "execution(* *(..)) && @annotation(com.sic777.common.annotation.MethodLock)", throwing = "e")
    public void afterThrowing(JoinPoint pjp, Throwable e) throws Exception {
        if (e instanceof AbstractRestException) {
            AbstractRestException ex = (AbstractRestException) e;
            if (ex.getCode() != FREQUENT_OPERATION.getId()) {
                MethodSignature signature = (MethodSignature) pjp.getSignature();
                Method method = signature.getMethod();
                MethodLock lock = method.getAnnotation(MethodLock.class);
                Object callerId = getCallerId();
                super.unlock(method, lock, pjp.getArgs(), callerId);
            }
        }
    }

    @After("execution(* *(..)) && @annotation(com.sic777.common.annotation.MethodLock)")
    public void after(JoinPoint pjp) {
    }

    private Object getCallerId() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        Object accessToken = request.getAttribute(RestConstants.ACK_ATTRIBUTE_FLAG);
        JSONObject json = JSONObject.parseObject(JSON.toJSONString(accessToken));
        return json.get("id");
    }
}
