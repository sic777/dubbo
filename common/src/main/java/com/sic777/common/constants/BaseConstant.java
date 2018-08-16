package com.sic777.common.constants;

/**
 * <p>基础常量</p>
 *
 * @author Zhengzhenxie
 * @version v1.0
 * @since 2018-06-10
 */
public class BaseConstant {
    /**
     * Spring扫描路径
     */
    public static final String BASE_SPRING_SCAN_PACKAGE = "com.sic777";
    /**
     * ISV Spring扫描路径
     */
    public static final String ISV_SPRING_SCAN_PACKAGE = "${spring.scan}";
    /**
     * 默认配置文件所在文件夹名称
     */
    public final static String DEFAULT_CONFIG_PATH_NAME = "config";
    /**
     * 默认配置文件名称
     */
    public final static String DEFAULT_PROPERTIES_NAME = "application.properties";
    /**
     * 默认配置文件名称
     */
    public final static String DEFAULT_JSON_NAME = "configure.json";
    /**
     * 环境标识(值为：开发、测试、生产...)
     */
    public final static String ENVIRONMENT_FLAG = "environment";
    /**
     * 鉴权标识
     */
    public static final String ACCESS_TOKEN_FLAG = "Access-Token";
}
