package com.sic777.utils;

import com.alibaba.fastjson.JSONObject;
import com.sic777.utils.StringUtil;

/**
 * <p></p>
 *
 * @author Zhengzhenxie
 * @version v1.0
 * @since 2018-05-08
 */
public class JsonUtil {
    /**
     * @param key
     * @param value
     * @return
     */
    public static JSONObject putSingle(String key, String value) {
        if (StringUtil.isEmpty(key)) {
            return new JSONObject();
        }
        JSONObject js = new JSONObject();
        js.put(key, value);
        return js;
    }

    /**
     * @param keys
     * @param values
     * @return
     * @throws Exception
     */
    public static JSONObject putMulti(String[] keys, String[] values) throws Exception {
        if (null == keys || keys.length > values.length) {
            throw new Exception("'keys' can not be null and the length of 'values' must be greater than or equal to 'keys'");
        }
        JSONObject js = new JSONObject();
        for (int i = 0, len = keys.length; i < len; i++) {
            js.put(keys[i], values[i]);
        }
        return js;
    }

    /**
     * 判断是否存在某一个key
     *
     * @param js
     * @param key
     * @return
     */
    public static boolean containsKey(JSONObject js, String key) {
        return ObjectUtil.isNotNull(js) && js.containsKey(key);
    }

    /**
     * 判断是否存在某一个值
     *
     * @param js
     * @param value
     * @return
     */
    public static boolean containsValue(JSONObject js, Object value) {
        return ObjectUtil.isNotNull(js) && js.containsValue(value);
    }
}
