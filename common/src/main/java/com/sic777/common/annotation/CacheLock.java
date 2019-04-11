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
public @interface CacheLock {

    String prefix() default "";

    int expire() default 5;

    /**
     * 仅支持'天'、'小时'、'分钟'、'秒'
     */
    TimeUnit timeUnit() default TimeUnit.SECONDS;

    String delimiter() default ":";
}