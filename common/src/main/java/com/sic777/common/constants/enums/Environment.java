package com.sic777.common.constants.enums;

/**
 * <p>环境枚举
 *
 * @author sic777
 * @since 0.0.1
 */
public enum Environment {
    /**
     * 开发环境
     */
    DEVELOP("develop"),
    /**
     * 测试环境
     */
    TEST("test"),
    /**
     * 生产环境
     */
    PRODUCT("product");

    /**
     * 环境标识
     */
    private final String environment;

    /**
     * @param environment 环境标识
     */
    Environment(String environment) {
        this.environment = environment;
    }

    /**
     * 获取环境
     *
     * @return 环境枚举标识
     */
    public String getEnvironment() {
        return environment;
    }

    /**
     * 字符串转换成环境枚举
     * <br>
     * default:DEVELOP
     *
     * @param environment 环境字符串
     * @return 环境枚举
     */
    public static Environment fromEnvironment(String environment) {
        for (Environment e : Environment.values()) {
            if (e.environment.equals(environment)) {
                return e;
            }
        }
        return Environment.DEVELOP;
    }
}
