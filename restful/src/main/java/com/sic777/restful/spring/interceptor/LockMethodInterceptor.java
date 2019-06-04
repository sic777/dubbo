package com.sic777.restful.spring.interceptor;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sic777.common.annotation.MethodLock;
import com.sic777.common.annotation.MethodLockParam;
import com.sic777.common.utils.encrypt.md5.MD5Util;
import com.sic777.common.utils.lang.StringUtil;
import com.sic777.db.redis.Redis;
import com.sic777.db.redis.RedisDistributedLock;
import com.sic777.logger.LoggerHelper;
import com.sic777.restful.base.constants.RestConstants;
import com.sic777.restful.base.response.AbstractRestException;
import com.sic777.restful.base.response.ResponseManager;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeUnit;

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
    private final static String LIMIT_FLAG = "_method_limit";
    private final static String LOCK_FLAG = "_method_lock";
    private final static String CLIENT_FLAG = "LOCK";

    @Before("execution(* *(..)) && @annotation(com.sic777.common.annotation.MethodLock)")
    public void before(JoinPoint pjp) throws Exception {
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = signature.getMethod();
        MethodLock lock = method.getAnnotation(MethodLock.class);

        if (StringUtil.isEmpty(lock.value())) {
            String msg = "@MethodLock prefix error.";
            LoggerHelper.error(msg);
            ResponseManager.instance().throwRest503Exception(new Exception(msg));
        }

        Object callerId = getCallerId();
        boolean rs = lock(method, lock, pjp.getArgs(), callerId);
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
                unlock(method, lock, pjp.getArgs(), callerId);
            }
        }
    }

    @After("execution(* *(..)) && @annotation(com.sic777.common.annotation.MethodLock)")
    public void after(JoinPoint pjp) {
    }

    /**
     * 获取接口调用者id
     *
     * @return
     */
    private Object getCallerId() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        Object accessToken = request.getAttribute(RestConstants.ACK_ATTRIBUTE_FLAG);
        JSONObject json = JSONObject.parseObject(JSON.toJSONString(accessToken));
        return json.get("id");
    }


    private String getLockMethodKey(Method method, MethodLock methodLock, final Object[] pjpArgs, Object callerId) throws Exception {
        if (methodLock == null) {
            return null;
        }
        if (methodLock.isLimit()) {
            if (methodLock.timeUnit() != TimeUnit.SECONDS) {
                String msg = "Time unit must be SECONDS";
                LoggerHelper.error(msg);
                ResponseManager.instance().throwRest503Exception(new Exception(msg));
            }
            return getLocalKey(methodLock, null, callerId);
        }

        final Parameter[] parameters = method.getParameters();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < parameters.length; i++) {
            final MethodLockParam annotation = parameters[i].getAnnotation(MethodLockParam.class);
            if (annotation == null) {
                continue;
            }
            builder.append(methodLock.delimiter());
            Object obj = pjpArgs[i];
            if (obj instanceof JSONObject) {
                String[] lockParams = annotation.lockParams();
                if (lockParams.length == 0) {
                    String msg = "No values of lockParams was found.";
                    LoggerHelper.error(msg);
                    ResponseManager.instance().throwRest503Exception(new Exception(msg));
                }
                JSONObject js = (JSONObject) obj;
                for (String lockParam : lockParams) {
                    builder.append(js.getString(lockParam));
                }
            } else {
                builder.append(obj);
            }
        }
        if (StringUtil.isEmpty(builder.toString())) {
            final Annotation[][] parameterAnnotations = method.getParameterAnnotations();
            for (int i = 0; i < parameterAnnotations.length; i++) {
                final Object object = pjpArgs[i];
                final Field[] fields = object.getClass().getDeclaredFields();
                for (Field field : fields) {
                    final MethodLockParam annotation = field.getAnnotation(MethodLockParam.class);
                    if (annotation == null) {
                        continue;
                    }
                    field.setAccessible(true);
                    builder.append(methodLock.delimiter()).append(ReflectionUtils.getField(field, object));
                }
            }
        }
        try {
            return getLocalKey(methodLock, MD5Util.md5(builder.toString()), callerId);
        } catch (NoSuchAlgorithmException e) {
            return getLocalKey(methodLock, builder.toString(), callerId);
        }
    }

    /**
     * 上锁
     *
     * @param method
     * @param methodLock
     * @param pjpArgs
     * @param callerId
     * @return
     * @throws Exception
     */
    private boolean lock(Method method, MethodLock methodLock, final Object[] pjpArgs, Object callerId) throws Exception {
        int expire = methodLock.expire();
        switch (methodLock.timeUnit()) {
            case DAYS:
                expire *= 60 * 60 * 24;
                break;
            case HOURS:
                expire *= 60 * 60;
                break;
            case MINUTES:
                expire *= 60;
                break;
            case SECONDS:
                break;
            default:
                String msg = "Unsupported time unit.";
                LoggerHelper.error(msg);
                ResponseManager.instance().throwRest503Exception(new Exception(msg));
        }
        String lockKey = this.getLockMethodKey(method, methodLock, pjpArgs, callerId);
        boolean rs = RedisDistributedLock.tryGetDistributedLock(lockKey, CLIENT_FLAG, expire);
        if (rs && methodLock.isLimit()) {
            Jedis jedis = null;
            try {
                jedis = Redis.instance().getJedis();
                Pipeline pipeline = jedis.pipelined();
                String KEY = getLimitKey(methodLock);
                String i = Redis.instance().String().get(KEY);
                if (i != null && Integer.parseInt(i) == methodLock.limit()) {
                    ResponseManager.instance().throwRestException(FREQUENT_OPERATION.getId(), FREQUENT_OPERATION.getMsg());
                }
                pipeline.incrBy(KEY, 1);
                pipeline.expire(KEY, methodLock.expire());
                pipeline.sync();
            } finally {
                Redis.instance().closeJedis(jedis);
            }
        }
        return rs;
    }

    /**
     * 解锁
     *
     * @param method
     * @param methodLock
     * @param pjpArgs
     * @param callerId
     * @return
     * @throws Exception
     */
    private boolean unlock(Method method, MethodLock methodLock, final Object[] pjpArgs, Object callerId) throws Exception {
        String lockKey = this.getLockMethodKey(method, methodLock, pjpArgs, callerId);
        return RedisDistributedLock.releaseDistributedLock(lockKey, CLIENT_FLAG);
    }

    private String getLocalKey(MethodLock methodLock, String parameters, Object callerId) {
        boolean isLimit = methodLock.isLimit();
        String module = StringUtil.isNotEmpty(methodLock.module()) ? methodLock.module() + methodLock.delimiter() : "";
        callerId = callerId != null && StringUtil.isNotEmpty(callerId) ? callerId + "_" : "";
        return isLimit
                ? module + LOCK_FLAG + methodLock.delimiter() + callerId + methodLock.value()
                : module + LOCK_FLAG + methodLock.delimiter() + callerId + methodLock.value() + methodLock.delimiter() + parameters;
    }

    private String getLimitKey(MethodLock methodLock) {
        String module = StringUtil.isNotEmpty(methodLock.module()) ? methodLock.module() + methodLock.delimiter() : "";
        return module + LIMIT_FLAG + methodLock.delimiter() + methodLock.value();
    }
}
