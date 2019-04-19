package com.sic777.common.annotation;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 *
 * @author sic777
 * @version 0.0.1
 * @since 0.0.1
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface MethodLock {

    /**
     * value
     */
    String value();

    /**
     * 所属模块
     */
    String module() default "";

    /**
     * 过期时间
     */
    int expire() default 3;

    /**
     * 是否限制访问数量，如果是，意味着该接口#expire秒内，仅允许#limit个访问（统计限制的过期时间为最后一个访问的#expire秒之后）
     */
    boolean isLimit() default false;

    /**
     * 当isLimit为true时,限制访问控制的数量
     */
    int limit() default 1;

    /**
     * 仅支持'天'、'小时'、'分钟'、'秒'
     */
    TimeUnit timeUnit() default TimeUnit.SECONDS;

    String delimiter() default ":";
}