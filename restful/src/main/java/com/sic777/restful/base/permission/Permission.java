package com.sic777.restful.base.permission;


import java.lang.annotation.*;

/**
 * <p>权限注解: 方法级别大于类级别</p>
 *
 * @author Zhengzhenxie
 * @version v1.0
 * @since 2018-01-24 18:01
 */
@Target(value = {ElementType.METHOD, ElementType.TYPE})
@Retention(value = RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface Permission {

    int[] value() default RestPermission.ANYBODY;
}
