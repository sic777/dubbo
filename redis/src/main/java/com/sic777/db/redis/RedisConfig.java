package com.sic777.db.redis;


import com.sic777.common.utils.conf.ConfigureManager;

/**
 * <p>Redis配置</p>
 *
 * @author Zhengzhenxie
 * @version v1.0
 * @since 2018-02-11 10:58
 */
public class RedisConfig {
    private final String ip;
    private final int port;
    private final String pass;
    private final int maxTotal;
    private final int maxIdle;
    private final int maxWaitMillis;
    private final boolean testOnBorrow;
    private final boolean testOnReturn;
    private final boolean testWhileIdle;

    public RedisConfig() {
        this.ip = ConfigureManager.instance().getString(RedisConstant.HOST);
        this.port = ConfigureManager.instance().getInt(RedisConstant.PORT);
        this.pass = ConfigureManager.instance().getString(RedisConstant.PASSWORD);
        this.maxTotal = ConfigureManager.instance().getInt(RedisConstant.POOL_MAX_TOTAL);
        this.maxIdle = ConfigureManager.instance().getInt(RedisConstant.POOL_MAX_IDLE);
        this.maxWaitMillis = ConfigureManager.instance().getInt(RedisConstant.POOL_MAX_WAIT_MILLIS);
        this.testOnBorrow = ConfigureManager.instance().getBool(RedisConstant.POOL_TEST_ON_BORROW);
        this.testOnReturn = ConfigureManager.instance().getBool(RedisConstant.POOL_TEST_ON_RETURN);
        this.testWhileIdle = ConfigureManager.instance().getBool(RedisConstant.POOL_TEST_WHILE_IDLE);
    }

    public RedisConfig(String ip, int port, String pass, int maxTotal, int maxIdle, int maxWaitMillis, boolean testOnBorrow, boolean testOnReturn, boolean testWhileIdle) {
        this.ip = ip;
        this.port = port;
        this.pass = pass;
        this.maxTotal = maxTotal;
        this.maxIdle = maxIdle;
        this.maxWaitMillis = maxWaitMillis;
        this.testOnBorrow = testOnBorrow;
        this.testOnReturn = testOnReturn;
        this.testWhileIdle = testWhileIdle;
    }

    public String getIp() {
        return ip;
    }

    public int getPort() {
        return port;
    }

    public String getPass() {
        return pass;
    }

    public int getMaxTotal() {
        return maxTotal;
    }

    public int getMaxIdle() {
        return maxIdle;
    }

    public int getMaxWaitMillis() {
        return maxWaitMillis;
    }

    public boolean isTestOnBorrow() {
        return testOnBorrow;
    }

    public boolean isTestOnReturn() {
        return testOnReturn;
    }

    public boolean isTestWhileIdle() {
        return testWhileIdle;
    }

}
