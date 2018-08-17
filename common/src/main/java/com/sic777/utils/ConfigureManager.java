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
    private Properties properties = new Properties();
    /**
     * 配置文件
     */
    private JSONObject config = new JSONObject();

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
     * 初始化，优先初始化json配置文件，如果json配置文件不存在，才会初始化properties配置文件
     */
    private void init() {
        if (atomicBoolean.compareAndSet(false, true)) {
            try {
                config = PropertiesUtil.loadJsonAutomatic();
            } catch (Exception e) {
                logger.warn("Automatic loading of json configuration file failed, try to automatically load properties configuration file...");
                try {
                    properties = PropertiesUtil.loadPropertiesAutomatic();
                } catch (Exception e1) {
                    logger.warn("Automatic loading properties configuration file failed", e1);
                    System.exit(-1);
                }
            }
        }
    }

    /**
     * 初始化，会同时初始化json配置文件和properties配置文件
     */
    private void initAll() {
        if (atomicBoolean.compareAndSet(false, true)) {
            try {
                config = PropertiesUtil.loadJsonAutomatic();
                properties = PropertiesUtil.loadPropertiesAutomatic();
            } catch (Exception e) {
                logger.error("Automatic loading of json configuration file and  properties configuration file failed...");
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
        JSONObject configTmp = config;
        String[] ks = key.split("\\.");
        for (String k : ks) {
            rs = configTmp.getJSONObject(k);
            configTmp = rs;
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
        JSONObject configTmp = config;
        String[] ks = key.split("\\.");
        for (String k : ks) {
            rs = configTmp.get(k);
            if (rs instanceof JSONObject) {
                configTmp = (JSONObject) rs;
            } else {
                return rs.toString();
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





