package com.sic777.common.constants;

import com.sic777.common.utils.proguard.NoProguard;

/**
 * <p>基础常量
 *
 * @author sic777
 * @since 0.0.1
 */
@NoProguard
public class BaseConstant {
    /**
     * Spring在此框架下的默认扫描路径,不可覆盖
     */
    public final static String BASE_SPRING_SCAN_PACKAGE = "com.sic777";
    /**
     * 开发者自定义的Spring扫描路径,不可覆盖
     */
    public final static String ISV_SPRING_SCAN_PACKAGE = "${spring.scan}";
    /**
     * 默认配置文件所在文件夹名称,子类可覆盖
     */
    public static String DEFAULT_CONFIG_PATH_NAME = "config";
    /**
     * 默认配置文件名称,子类可覆盖
     */
    public static String DEFAULT_PROPERTIES_NAME = "application.properties";
    /**
     * 默认配置文件名称,子类可覆盖
     */
    public static String DEFAULT_JSON_NAME = "configure.json";
    /**
     * 环境标识,子类可覆盖
     */
    public static String ENVIRONMENT_FLAG = "environment";
    /**
     * 鉴权标识,子类可覆盖
     */
    public static String ACCESS_TOKEN_FLAG = "Access-Token";
}
