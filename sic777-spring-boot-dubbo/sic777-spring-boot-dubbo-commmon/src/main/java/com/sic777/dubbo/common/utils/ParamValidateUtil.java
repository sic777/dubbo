package com.sic777.dubbo.common.utils;


import com.sic777.dubbo.common.exception.ParamValidateException;
import com.sic777.utils.string.StringUtil;

/**
 * <p></p>
 *
 * @author Zhengzhenxie
 * @version v1.0
 * @since 2018-05-08
 */
public class ParamValidateUtil {
    /**
     * 校验对象不为空
     *
     * @param obj
     * @param key
     * @param errorCode
     * @throws ParamValidateException
     */
    public static void funcValidateNotEmpty(Object obj, String key, int errorCode) throws ParamValidateException {
        if (null == obj || StringUtil.isEmpty(obj.toString())) {
            throw new ParamValidateException(errorCode, String.format("param '%s' must not be empty", key));
        }
    }

    /**
     * 校验对象不为null
     *
     * @param obj
     * @param key
     * @param errorCode
     * @throws ParamValidateException
     */
    public static void funcValidateNotNull(Object obj, String key, int errorCode) throws ParamValidateException {
        if (null == obj) {
            throw new ParamValidateException(errorCode, String.format("param '%s' must not be null", key));
        }
    }
}
