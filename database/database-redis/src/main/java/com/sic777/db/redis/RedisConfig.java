package com.sic777.db.redis;


import com.alibaba.fastjson.JSONObject;

import java.util.Properties;

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

    public RedisConfig(final JSONObject jsonObject) {
        JSONObject redisJson = jsonObject.getJSONObject(RedisConstant.REDIS_FLAG);
        this.ip = redisJson.getString(RedisConstant.HOST);
        this.port = redisJson.getIntValue(RedisConstant.PORT);
        this.pass = redisJson.getString(RedisConstant.PASSWORD);
        this.maxTotal = redisJson.getIntValue(RedisConstant.POOL_MAX_TOTAL);
        this.maxIdle = redisJson.getIntValue(RedisConstant.POOL_MAX_IDLE);
        this.maxWaitMillis = redisJson.getIntValue(RedisConstant.POOL_MAX_WAIT_MILLIS);
        this.testOnBorrow = redisJson.getBoolean(RedisConstant.POOL_TEST_ON_BORROW);
        this.testOnReturn = redisJson.getBoolean(RedisConstant.POOL_TEST_ON_RETURN);
        this.testWhileIdle = redisJson.getBoolean(RedisConstant.POOL_TEST_WHILE_IDLE);
    }

    public RedisConfig(final Properties props) {
        this.ip = props.getProperty(String.format("redis.%s", RedisConstant.HOST));
        this.port = Integer.parseInt(props.getProperty(String.format("redis.%s", RedisConstant.PORT)));
        this.pass = props.getProperty(String.format("redis.%s", RedisConstant.PASSWORD));
        this.maxTotal = Integer.parseInt(props.getProperty(String.format("redis.%s", RedisConstant.POOL_MAX_TOTAL)));
        this.maxIdle = Integer.parseInt(props.getProperty(String.format("redis.%s", RedisConstant.POOL_MAX_IDLE)));
        this.maxWaitMillis = Integer.parseInt(props.getProperty(String.format("redis.%s", RedisConstant.POOL_MAX_WAIT_MILLIS)));
        this.testOnBorrow = Boolean.parseBoolean(props.getProperty(String.format("redis.%s", RedisConstant.POOL_TEST_ON_BORROW)));
        this.testOnReturn = Boolean.parseBoolean(props.getProperty(String.format("redis.%s", RedisConstant.POOL_TEST_ON_RETURN)));
        this.testWhileIdle = Boolean.parseBoolean(props.getProperty(String.format("redis.%s", RedisConstant.POOL_TEST_WHILE_IDLE)));
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
