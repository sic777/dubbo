package com.sic777.common.utils.bean;


import com.sic777.common.utils.bean.convert.DateConvert;
import com.sic777.common.utils.proguard.NoProguard;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;


/**
 * <p>BeanUtils扩展
 *
 * @author sic777
 * @since 0.0.1
 */
@NoProguard
public class BeanUtil extends BeanUtils {
    static {
        ConvertUtils.register(new DateConvert(), Date.class);
    }

    /**
     * 复制实体
     *
     * @param target 目标实体
     * @param source 源头实体
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    public static void copyProperties(Object target, Object source) throws IllegalAccessException, InvocationTargetException {
        if (source == null || target == null) {
            return;
        }
        BeanUtils.copyProperties(target, source);
    }
}
