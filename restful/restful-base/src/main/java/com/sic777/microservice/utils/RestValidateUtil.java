package com.sic777.microservice.utils;

import com.alibaba.fastjson.JSONObject;
import com.sic777.microservice.exception.Rest400Exception;
import com.sic777.microservice.exception.error.RESTFUL_ERROR;
import com.sic777.utils.JsonUtil;
import com.sic777.utils.ObjectUtil;
import com.sic777.utils.StringUtil;

/**
 * <p>校验工具类</p>
 *
 * @author Zhengzhenxie
 * @version v1.0
 * @since 2018-06-05
 */
public final class RestValidateUtil {
    private RestValidateUtil() {
    }

    /**
     * 校验是否存在key
     *
     * @param js
     * @param key
     * @throws Rest400Exception
     */
    public static void containsKey(JSONObject js, String key) throws Rest400Exception {
        if (!JsonUtil.containsKey(js, key)) {
            throw new Rest400Exception(RESTFUL_ERROR.OBJECT_NULL, key);
        }
    }


    /**
     * 校验对象不为null
     *
     * @param obj
     * @param key
     * @throws Rest400Exception
     */
    public static void objectNotNull(Object obj, String key) throws Rest400Exception {
        if (ObjectUtil.isNull(obj)) {
            throw new Rest400Exception(RESTFUL_ERROR.OBJECT_NULL, key);
        }
    }

    /**
     * 校验字符串不为null
     *
     * @param str
     * @param key
     * @throws Rest400Exception
     */
    public static void stringNotNull(String str, String key) throws Rest400Exception {
        if (StringUtil.isNull(str)) {
            throw new Rest400Exception(RESTFUL_ERROR.VALUE_NULL, key);
        }
    }

    /**
     * 校验字符串不为空
     *
     * @param str
     * @param key
     * @throws Rest400Exception
     */
    public static void stringNotEmpty(String str, String key) throws Rest400Exception {
        if (StringUtil.isEmpty(str)) {
            throw new Rest400Exception(RESTFUL_ERROR.VALUE_EMPTY, key);
        }
    }
}
