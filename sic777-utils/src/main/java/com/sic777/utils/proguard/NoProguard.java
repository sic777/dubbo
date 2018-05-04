package com.sic777.utils.proguard;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>定义不参与混淆的代码</p>
 *
 * @author Zhengzhenxie<br>
 *         <br>2017-11-27 11:52
 * @version v1.0
 * @since 1.7
 */
@Retention(RetentionPolicy.CLASS)
@Target({ElementType.TYPE, ElementType.METHOD, ElementType.CONSTRUCTOR, ElementType.FIELD})
public @interface NoProguard {

}
