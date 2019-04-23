package com.sic777.db.redis;


import com.sic777.db.redis.collection.*;
import com.sic777.logger.LoggerHelper;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.util.Pool;

import java.util.concurrent.atomic.AtomicBoolean;


/**
 * <p>Redis操作实例</p>
 *
 * @author Zhengzhenxie
 * @version v1.0
 * @since 2017-09-29 09:11
 */
public final class Redis {
    private static Redis singleton = new Redis();

    public static Redis instance() {
        return singleton;
    }

    private Redis() {
    }

    private static AtomicBoolean isInit = new AtomicBoolean(false);
    private RedisConfig redisConfig;
    private JedisPool jedisPool;
    private RedisHash HASH = new RedisHash();
    private RedisKeys KEYS = new RedisKeys();
    private RedisList LIST = new RedisList();
    private RedisSet SET = new RedisSet();
    private RedisZSet Z_SET = new RedisZSet();
    private RedisString STRING = new RedisString();

    /**
     * redis初始化
     */
    public void init() {
        if (isInit.compareAndSet(false, true)) {
            LoggerHelper.instance().info("init redis ...");
            redisConfig = new RedisConfig();
            initPool();
        }
    }

    /**
     * 初始化连接池
     */
    private void initPool() {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(redisConfig.getMaxTotal());
        config.setMaxIdle(redisConfig.getMaxIdle());
        config.setMaxWaitMillis(redisConfig.getMaxWaitMillis());
        config.setTestOnBorrow(redisConfig.isTestOnBorrow());
        config.setTestOnReturn(redisConfig.isTestOnReturn());
        config.setTestWhileIdle(redisConfig.isTestWhileIdle());
        jedisPool = new JedisPool(config, redisConfig.getIp(), redisConfig.getPort(), redisConfig.getMaxWaitMillis(), redisConfig.getPass());
    }


    /**
     * 获取Jedis连接池
     *
     * @return Jedis连接池
     */
    public Pool<Jedis> getRedisPool() {
        return jedisPool;
    }

    /**
     * 获取Jedis客户端
     *
     * @return
     */
    public Jedis getJedis() {
        Jedis jedis = null;
        try {
            jedis = getRedisPool().getResource();
        } catch (Exception e) {
            LoggerHelper.instance().error("sorry,redis connect error !!", e);
        }
        return jedis;
    }

    /**
     * 关闭客户端连接
     *
     * @param jedis
     */
    public void closeJedis(Jedis jedis) {
        jedis.close();
    }

    public RedisHash Hash() {
        return HASH;
    }

    public RedisKeys Keys() {
        return KEYS;
    }

    public RedisList List() {
        return LIST;
    }

    public RedisSet Sets() {
        return SET;
    }

    public RedisZSet ZSet() {
        return Z_SET;
    }

    public RedisString String() {
        return STRING;
    }
}
