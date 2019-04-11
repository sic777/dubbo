package com.sic777.restful.springboot.interceptor;


import com.sic777.common.annotation.CacheLock;
import com.sic777.common.annotation.CacheParam;
import com.sic777.common.utils.encrypt.md5.MD5Util;
import com.sic777.common.utils.lang.StringUtil;
import com.sic777.logger.LoggerHelper;
import com.sic777.restful.base.response.ResponseManager;
import com.sic777.restful.springboot.spi.ILockMethodRedisProcess;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ReflectionUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.security.NoSuchAlgorithmException;

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
public class LockMethodInterceptor {

    @Autowired(required = false)
    private ILockMethodRedisProcess lockMethodRedisProcess;

    @Around("execution(* *(..)) && @annotation(com.sic777.common.annotation.CacheLock)")
    public Object interceptor(ProceedingJoinPoint pjp) throws Throwable {
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = signature.getMethod();
        CacheLock lock = method.getAnnotation(CacheLock.class);
        if (StringUtil.isEmpty(lock.prefix())) {
            String msg = "@CacheLock prefix error.";
            LoggerHelper.instance().error(msg);
            ResponseManager.instance().throwRest503Exception(new Exception(msg));
        }
        String lockKey = getLockKey(pjp);
        if (lockMethodRedisProcess == null) {
            String msg = "No com.sic777.restful.springboot.spi.ILockMethodRedisProcess implementation class was found.";
            LoggerHelper.instance().error(msg);
            ResponseManager.instance().throwRest503Exception(new Exception(msg));
        }
        boolean rs = lockMethodRedisProcess.process(lockKey, lock.expire(), lock.timeUnit());
        if (!rs) {
            ResponseManager.instance().throwRestException(FREQUENT_OPERATION.getId(), FREQUENT_OPERATION.getMsg());
        }
        return pjp.proceed();
    }


    private static String getLockKey(ProceedingJoinPoint pjp) throws NoSuchAlgorithmException {
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = signature.getMethod();
        CacheLock lockAnnotation = method.getAnnotation(CacheLock.class);
        final Object[] args = pjp.getArgs();
        final Parameter[] parameters = method.getParameters();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < parameters.length; i++) {
            final CacheParam annotation = parameters[i].getAnnotation(CacheParam.class);
            if (annotation == null) {
                continue;
            }
            builder.append(lockAnnotation.delimiter()).append(args[i]);
        }
        if (StringUtil.isEmpty(builder.toString())) {
            final Annotation[][] parameterAnnotations = method.getParameterAnnotations();
            for (int i = 0; i < parameterAnnotations.length; i++) {
                final Object object = args[i];
                final Field[] fields = object.getClass().getDeclaredFields();
                for (Field field : fields) {
                    final CacheParam annotation = field.getAnnotation(CacheParam.class);
                    if (annotation == null) {
                        continue;
                    }
                    field.setAccessible(true);
                    builder.append(lockAnnotation.delimiter()).append(ReflectionUtils.getField(field, object));
                }
            }
        }
        return lockAnnotation.prefix() + ":" + MD5Util.md5(builder.toString());
    }
}
