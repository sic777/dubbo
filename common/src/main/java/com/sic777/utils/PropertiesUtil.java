package com.sic777.utils;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sic777.common.constants.BaseConstant;
import com.sic777.common.system.CurrentEnvironment;
import com.sic777.utils.container.tuple.Tuple;
import com.sic777.utils.container.tuple.TwoTuple;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.net.URL;
import java.util.Properties;

import com.sic777.utils.proguard.NoProguard;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>配置文件工具类
 *
 * @author sic777
 * @since 0.0.1
 */
@NoProguard
public final class PropertiesUtil {

    private final static Logger logger = LoggerFactory.getLogger(PropertiesUtil.class);

    private PropertiesUtil() {
    }

    /**
     * 在绝对路径下加载配置
     *
     * @param configPath 绝对路径
     * @return
     * @throws Exception
     */
    public static Properties loadPropertiesAbsolute(String configPath) throws Exception {
        if (null == configPath) {
            return null;
        }
        String path = StringUtil.parsePath(configPath);
        FileInputStream file = null;
        Properties pro = new Properties();
        try {
            file = new FileInputStream(path);
            pro.load(file);
        } finally {
            if (null != file) {
                file.close();
            }
        }
        return pro;
    }

    /**
     * 在类路径下加载配置
     *
     * @param configPath 类路径下的文件路径
     * @return
     * @throws Exception
     */
    public static Properties loadPropertiesClasspath(String configPath) throws Exception {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        URL url = classLoader.getResource(configPath);
        return null != url ? loadPropertiesAbsolute(url.getPath()) : null;
    }

    /**
     * 自动根据环境加载配置文件
     *
     * @param configName     配置文件名称
     * @param configPathName 配置文件所在文件夹名称
     * @return
     * @throws Exception
     */
    public static Properties loadPropertiesAutomatic(String configName, String configPathName) throws Exception {
        TwoTuple<String, Boolean> t = getPathAutomatic(configName, configPathName);
        return t.second
                ? PropertiesUtil.loadPropertiesClasspath(t.first)
                : PropertiesUtil.loadPropertiesAbsolute(t.first);
    }

    /**
     * 自动根据环境加载配置文件
     *
     * <p>
     * 注意：
     * 配置文件名称默认为 application.properties
     * 配置文件所在文件夹名称默认为config
     *
     * @return
     * @throws Exception
     */
    public static Properties loadPropertiesAutomatic() throws Exception {
        return loadPropertiesAutomatic(BaseConstant.DEFAULT_PROPERTIES_NAME, BaseConstant.DEFAULT_CONFIG_PATH_NAME);
    }


    /**
     * 在绝对路径下解析配置文件，输出JSONObject对象
     *
     * @param configurePath 绝对路径
     * @return
     * @throws Exception
     */
    public static JSONObject loadJsonAbsolute(String configurePath) throws Exception {
        if (null == configurePath) {
            return null;
        }
        String path = StringUtil.parsePath(configurePath);
        BufferedReader input = null;
        try {
            String text;
            StringBuilder sb = new StringBuilder();
            input = new BufferedReader(new FileReader(path));
            while ((text = input.readLine()) != null) {
                sb.append(text);
            }
            return JSONObject.parseObject(sb.toString());
        } finally {
            if (null != input) {
                input.close();
            }
        }
    }

    /**
     * 在类路径下解析配置文件，输出JSONObject对象
     *
     * @param configurePath 类路径下的文件路径
     * @return
     * @throws Exception
     */
    public static JSONObject loadJsonClasspath(String configurePath) throws Exception {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        URL url = classLoader.getResource(configurePath);
        return null != url ? loadJsonAbsolute(url.getPath()) : null;
    }

    /**
     * 自动根据环境加载配置文件
     *
     * @param configureName     配置文件名称
     * @param configurePathName 配置文件所在文件夹名称
     * @return
     */
    public static JSONObject loadJsonAutomatic(String configureName, String configurePathName) throws Exception {
        TwoTuple<String, Boolean> t = getPathAutomatic(configureName, configurePathName);
        return t.second
                ? PropertiesUtil.loadJsonClasspath(t.first)
                : PropertiesUtil.loadJsonAbsolute(t.first);
    }

    /**
     * 自动根据环境加载配置文件
     * <p>
     * 注意：
     * 配置文件名称默认为 configure.json
     * 配置文件存放文件夹名称 config
     *
     * @return
     */
    public static JSONObject loadJsonAutomatic() throws Exception {
        return loadJsonAutomatic(BaseConstant.DEFAULT_JSON_NAME, BaseConstant.DEFAULT_CONFIG_PATH_NAME);
    }


    /**
     * 在绝对路径下解析配置文件，输出JSONArray对象
     *
     * @param configurePath 绝对路径
     * @return
     * @throws Exception
     */
    public static JSONArray loadArrayAbsolute(String configurePath) throws Exception {
        if (null == configurePath) {
            return null;
        }
        String path = StringUtil.parsePath(configurePath);
        BufferedReader input = null;
        try {
            String text;
            StringBuilder sb = new StringBuilder();
            input = new BufferedReader(new FileReader(path));
            while ((text = input.readLine()) != null) {
                sb.append(text);
            }
            return JSONArray.parseArray(sb.toString());
        } finally {
            if (null != input) {
                input.close();
            }
        }
    }

    /**
     * 在类路径下解析配置文件，输出JSONArray对象
     *
     * @param configurePath 类路径下的文件路径
     * @return
     * @throws Exception
     */
    public static JSONArray loadArrayClasspath(String configurePath) throws Exception {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        URL url = classLoader.getResource(configurePath);
        return null != url ? loadArrayAbsolute(url.getPath()) : null;
    }

    /**
     * 自动根据环境加载配置文件
     *
     * @param configureName     配置文件名称
     * @param configurePathName 配置文件所在文件夹名称
     * @return
     */
    public static JSONArray loadArrayAutomatic(String configureName, String configurePathName) throws Exception {
        TwoTuple<String, Boolean> t = getPathAutomatic(configureName, configurePathName);
        return t.second
                ? PropertiesUtil.loadArrayClasspath(t.first)
                : PropertiesUtil.loadArrayAbsolute(t.first);
    }

    /**
     * 自动根据环境加载配置文件
     * <p>
     * 注意：
     * 配置文件名称默认为 configure.json
     * 配置文件存放文件夹名称 config
     *
     * @return
     */
    public static JSONArray loadArrayAutomatic() throws Exception {
        return loadArrayAutomatic(BaseConstant.DEFAULT_JSON_NAME, BaseConstant.DEFAULT_CONFIG_PATH_NAME);
    }

    /**
     * 获取文件全路径
     *
     * @param fileName
     * @param filePath
     * @return <路径,是否为默认环境>
     * @throws Exception
     */
    private static TwoTuple<String, Boolean> getPathAutomatic(String fileName, String filePath) throws Exception {
        String os = System.getProperty("os.name");
        String userDir = StringUtil.parsePath(System.getProperty("user.dir"));
        boolean isDefault = CurrentEnvironment.instance().isDefault();
        String rsFileName = isDefault ? fileName :
                String.format("%s%s%s", userDir, String.format(os.toLowerCase().startsWith("win") ? "\\%s\\" : "/%s/", filePath), fileName);
        logger.info("file name:" + fileName + ",path:" + filePath + ",current environment:" + CurrentEnvironment.instance().getEnvironment().getEnvironment());
        return Tuple.tuple(rsFileName, isDefault);
    }
}

