package com.sic777.utils.properties;


import com.alibaba.fastjson.JSONObject;

import java.io.*;
import java.util.Properties;

/**
 * Created by Zhengzhenxie on 2017/9/12.
 */
public final class PropertiesUtil {
    private PropertiesUtil() {
    }

    /**
     * 加载配置
     *
     * @param configPath
     * @return
     */
    public static Properties load(String configPath) throws IOException {
        FileInputStream file = null;
        Properties pro = new Properties();
        try {
            file = new FileInputStream(configPath);
            pro.load(file);
        } finally {
            if (null != file) {
                file.close();
            }
        }
        return pro;
    }

    /**
     * 解析配置文件，输出JSONObject对象
     *
     * @param configurePath
     * @return
     * @throws IOException
     */
    public static JSONObject loadConfigure(String configurePath) throws IOException {
        BufferedReader input = null;
        try {
            String text;
            StringBuilder sb = new StringBuilder();
            input = new BufferedReader(new FileReader(configurePath));
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
}
