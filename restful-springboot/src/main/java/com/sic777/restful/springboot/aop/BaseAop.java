package com.sic777.restful.springboot.aop;

import com.alibaba.fastjson.JSONObject;
import com.sic777.common.annotation.MethodLock;
import com.sic777.common.annotation.MethodLockParam;
import com.sic777.common.utils.encrypt.md5.MD5Util;
import com.sic777.common.utils.lang.StringUtil;
import com.sic777.db.redis.Redis;
import com.sic777.db.redis.RedisDistributedLock;
import com.sic777.logger.LoggerHelper;
import com.sic777.restful.base.response.ResponseManager;
import org.springframework.util.ReflectionUtils;
import redis.clients.jedis.Pipeline;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeUnit;

import static com.sic777.restful.base.exception.ExceptionType.ParamExceptionType.FREQUENT_OPERATION;

/**
 * <p>
 *
 * @author sic777
 * @version 0.0.1
 * @since 0.0.1
 */
public abstract class BaseAop {
    private final static String LIMIT_FLAG = "_method_limit:";
    private final static String LOCK_FLAG = "_method_lock:";
    private final static String CLIENT_FLAG = "LOCK:";

    private final String getLockMethodKey(Method method, MethodLock methodLock, final Object[] pjpArgs) throws Exception {
        if (methodLock == null) {
            return null;
        }
        if (methodLock.isLimit()) {
            if (methodLock.timeUnit() != TimeUnit.SECONDS) {
                String msg = "Time unit must be SECONDS";
                LoggerHelper.instance().error(msg);
                ResponseManager.instance().throwRest503Exception(new Exception(msg));
            }
            Pipeline pipeline = Redis.instance().getJedis().pipelined();
            String KEY = LIMIT_FLAG + methodLock.value();
            String i = Redis.instance().String().get(KEY);
            if (i != null && Integer.parseInt(i) == methodLock.limit()) {
                ResponseManager.instance().throwRestException(FREQUENT_OPERATION.getId(), FREQUENT_OPERATION.getMsg());
            }
            pipeline.incrBy(KEY, 1);
            pipeline.expire(KEY, methodLock.expire());
            pipeline.sync();
            return LOCK_FLAG + methodLock.value();
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
                    LoggerHelper.instance().error(msg);
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
            return LOCK_FLAG + methodLock.value() + ":" + MD5Util.md5(builder.toString());
        } catch (NoSuchAlgorithmException e) {
            return LOCK_FLAG + methodLock.value() + ":" + builder.toString();
        }
    }

    protected final boolean lock(Method method, MethodLock methodLock, final Object[] pjpArgs) throws Exception {
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
                LoggerHelper.instance().error(msg);
                ResponseManager.instance().throwRest503Exception(new Exception(msg));
        }
        return RedisDistributedLock.tryGetDistributedLock(this.getLockMethodKey(method, methodLock, pjpArgs), CLIENT_FLAG, expire);
    }

    protected final boolean unlock(Method method, MethodLock methodLock, final Object[] pjpArgs) throws Exception {
        return RedisDistributedLock.releaseDistributedLock(this.getLockMethodKey(method, methodLock, pjpArgs), CLIENT_FLAG);
    }
}
