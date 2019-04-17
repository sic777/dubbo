package com.sic777.restful.springboot.interceptor;


import com.sic777.common.annotation.MethodLock;
import com.sic777.common.utils.lang.StringUtil;
import com.sic777.logger.LoggerHelper;
import com.sic777.restful.base.response.AbstractRestException;
import com.sic777.restful.base.response.ResponseManager;
import com.sic777.restful.springboot.aop.BaseAop;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.Configuration;

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

    @Before("execution(* *(..)) && @annotation(com.sic777.common.annotation.MethodLock)")
    public void before(JoinPoint pjp) throws Exception {
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = signature.getMethod();
        MethodLock lock = method.getAnnotation(MethodLock.class);
        if (StringUtil.isEmpty(lock.value())) {
            String msg = "@MethodLock prefix error.";
            LoggerHelper.instance().error(msg);
            ResponseManager.instance().throwRest503Exception(new Exception(msg));
        }
        boolean rs = super.lock(method, lock, pjp.getArgs());
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
                super.unlock(method, lock, pjp.getArgs());
            }
        }
    }

    @After("execution(* *(..)) && @annotation(com.sic777.common.annotation.MethodLock)")
    public void after(JoinPoint pjp) {
    }
}
