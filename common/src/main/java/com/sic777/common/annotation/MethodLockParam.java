package com.sic777.common.annotation;


import java.lang.annotation.*;

/**
 * <p>
 *
 * @author sic777
 * @version 0.0.1
 * @since 0.0.1
 */
@Target({ElementType.PARAMETER, ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface MethodLockParam {
    /**
     * 当MethodLockParam为com.alibaba.fastjson.JSONObject时候，指定其中的参数，用于区分锁
     */
    String[] lockParams() default {};
}

