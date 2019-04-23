package com.sic777.common.utils.json;

import com.alibaba.fastjson.JSONObject;
import com.sic777.common.utils.lang.StringUtil;

import java.util.Map;

/**
 * <p>Json工具类
 *
 * @author sic777
 * @since 0.0.1
 */
public class JsonUtil {
    private JsonUtil() {
    }

    /**
     * @param key   键
     * @param value 值
     * @return
     */
    public static JSONObject put(String key, String value) {
        JSONObject js = new JSONObject();
        if (StringUtil.isNotNull(key)) {
            js.put(key, value);
        }
        return js;
    }

    /**
     * @param keys   键数组
     * @param values 值数组
     * @return
     * @throws IllegalArgumentException
     */
    public static JSONObject put(String[] keys, String[] values) throws IllegalArgumentException {
        if (null == keys || keys.length > values.length) {
            throw new IllegalArgumentException("'keys' can not be null and the length of 'values' must be greater than or equal to 'keys'");
        }
        JSONObject js = new JSONObject();
        for (int i = 0, len = keys.length; i < len; i++) {
            js.put(keys[i], values[i]);
        }
        return js;
    }

    /**
     * @param map
     * @return
     */
    public static JSONObject put(Map<String, Object> map) {
        JSONObject js = new JSONObject();
        if (StringUtil.isNotNull(map)) {
            js.putAll(map);
        }
        return js;
    }

    /**
     * 判断是否存在某一个key
     *
     * @param js  JSONObject
     * @param key 键
     * @return
     */
    public static boolean containsKey(JSONObject js, String key) {
        return StringUtil.isNotNull(js) && js.containsKey(key);
    }

    /**
     * 判断是否存在某一个值
     *
     * @param js    JSONObject
     * @param value 值
     * @return
     */
    public static boolean containsValue(JSONObject js, Object value) {
        return StringUtil.isNotNull(js) && js.containsValue(value);
    }
}
