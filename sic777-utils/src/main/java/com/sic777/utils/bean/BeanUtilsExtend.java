package com.sic777.utils.bean;


import com.sic777.utils.bean.convert.DateConvert;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;

/**
 * <p></p>
 *
 * @author Zhengzhenxie
 * @version v1.0
 * @since 2018-01-25 16:14
 */
public class BeanUtilsExtend extends BeanUtils {
    static {
        ConvertUtils.register(new DateConvert(), Date.class);
    }

    /**
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
