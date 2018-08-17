package com.sic777.utils;


import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * <p>configuration manager</p>
 *
 * @author Zhengzhenxie
 * @version v1.0
 * @since 2018-06-30
 */
public class ConfigureManager {
    private static ConfigureManager singleton = new ConfigureManager();

    public static ConfigureManager instance() {
        return singleton;
    }

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    /**
     * 配置文件
     */
    private Properties properties;
    /**
     * 配置文件
     */
    private JSONObject config;

    private AtomicBoolean atomicBoolean = new AtomicBoolean(false);

    private ConfigureManager() {
        init();
    }

    public JSONObject getConfig() {
        return config;
    }

    public Properties getProperties() {
        return properties;
    }

    /**
     * 初始化
     */
    private void init() {
        if (atomicBoolean.compareAndSet(false, true)) {
            try {
                config = PropertiesUtil.loadJsonAutomatic();
                properties = PropertiesUtil.loadPropertiesAutomatic();
                if (config == null && properties == null) {
                    throw new Exception();
                }
            } catch (Exception e) {
                logger.warn("Automatic loading configuration file failed", e);
                System.exit(-1);
            }
        }
    }

    /**
     * get json object
     *
     * @param key
     * @return
     */
    public JSONObject getJsonObject(String key) {
        JSONObject rs = null;
        if (null != config) {
            JSONObject configTmp = config;
            String[] ks = key.split("\\.");
            for (String k : ks) {
                rs = configTmp.getJSONObject(k);
                configTmp = rs;
            }
        }
        return rs;
    }

    /**
     * get string value
     *
     * @param key
     * @return
     */
    public String getString(String key) {
        Object rs;
        if (null != config) {
            JSONObject configTmp = config;
            String[] ks = key.split("\\.");
            for (String k : ks) {
                rs = configTmp.get(k);
                if (rs != null) {
                    if (rs instanceof JSONObject) {
                        configTmp = (JSONObject) rs;
                    } else {
                        return rs.toString();
                    }
                }
            }
        }
        return properties.getProperty(key);
    }

    /**
     * grt string value
     *
     * @param key
     * @param defaultValue
     * @return
     */
    public String getString(String key, String defaultValue) {
        String rs = getString(key);
        return null != rs ? rs : defaultValue;
    }

    /**
     * get int value
     *
     * @param key
     * @return
     */
    public Integer getInt(String key) {
        String value = getString(key);
        return null != value ? Integer.parseInt(value) : null;
    }

    /**
     * get int value
     *
     * @param key
     * @param defaultValue
     * @return
     */
    public int getInt(String key, int defaultValue) {
        Integer rs = getInt(key);
        return null != rs ? rs : defaultValue;
    }

    /**
     * get boolean value
     *
     * @param key
     * @return
     */
    public boolean getBool(String key) {
        String value = getString(key);
        return Boolean.parseBoolean(value);
    }
}





