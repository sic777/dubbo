package com.sic777.db.redis;


import java.util.Properties;

/**
 * <p>Redis配置</p>
 *
 * @author Zhengzhenxie
 * @version v1.0
 * @since 2018-02-11 10:58
 */
class RedisConfig {
    private static String ip;
    private static int port;
    private static String pass;
    private static int maxTotal;
    private static int maxIdle;
    private static int maxWaitMillis;
    private static boolean testOnBorrow;
    private static boolean testOnReturn;
    private static boolean testWhileIdle;

    /**
     * 初始化redis配置文件
     *
     * @param props
     */
    public static final void init(final Properties props) {
        ip = props.getProperty(RedisConstant.HOST);
        port = Integer.parseInt(props.getProperty(RedisConstant.PORT));
        pass = props.getProperty(RedisConstant.PASSWORD);
        maxTotal = Integer.parseInt(props.getProperty(RedisConstant.POOL_MAX_TOTAL));
        maxIdle = Integer.parseInt(props.getProperty(RedisConstant.POOL_MAX_IDLE));
        maxWaitMillis = Integer.parseInt(props.getProperty(RedisConstant.POOL_MAX_WAIT_MILLIS));
        testOnBorrow = Boolean.parseBoolean(props.getProperty(RedisConstant.POOL_TEST_ON_BORROW));
        testOnReturn = Boolean.parseBoolean(props.getProperty(RedisConstant.POOL_TEST_ON_RETURN));
        testWhileIdle = Boolean.parseBoolean(props.getProperty(RedisConstant.POOL_TEST_WHILE_IDLE));
    }

    public static String getIp() {
        return ip;
    }

    public static int getPort() {
        return port;
    }

    public static String getPass() {
        return pass;
    }

    public static int getMaxTotal() {
        return maxTotal;
    }

    public static int getMaxIdle() {
        return maxIdle;
    }

    public static int getMaxWaitMillis() {
        return maxWaitMillis;
    }

    public static boolean isTestOnBorrow() {
        return testOnBorrow;
    }

    public static boolean isTestOnReturn() {
        return testOnReturn;
    }

    public static boolean isTestWhileIdle() {
        return testWhileIdle;
    }

}
