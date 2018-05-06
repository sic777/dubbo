package com.sic777.db.redis;


import com.sic777.db.redis.collection.*;
import com.sic777.utils.properties.PropertiesUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.util.Pool;

import java.io.IOException;
import java.util.Properties;
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

    /**
     * 仅初始化一次
     */
    private final AtomicBoolean init = new AtomicBoolean(false);


    private static final Logger logger = LoggerFactory.getLogger(Redis.class);
    private JedisPool jedisPool;
    private static AtomicBoolean isInit = new AtomicBoolean(false);
    private RedisHash HASH = new RedisHash();
    private RedisKeys KEYS = new RedisKeys();
    private RedisList LIST = new RedisList();
    private RedisSets SETS = new RedisSets();
    private RedisSortSets SORTSETS = new RedisSortSets();
    private RedisStrings STRINGS = new RedisStrings();


    /**
     * redis初始化
     *
     * @param propsPath
     * @throws IOException
     */
    public void init(final String propsPath) throws IOException {
        this.init(PropertiesUtil.load(propsPath));
    }

    /**
     * redis初始化
     *
     * @param props
     */
    public void init(final Properties props) {
        if (init.compareAndSet(false, true)) {
            logger.info("init redis config ...");
            if (!Redis.isInit.compareAndSet(false, true)) {
                return;
            }
            RedisConfig.init(props);//初始化redis配置文件
            initPool();
        }
    }

    /**
     * 初始化连接池
     */
    private void initPool() {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(RedisConfig.getMaxTotal());
        config.setMaxIdle(RedisConfig.getMaxIdle());
        config.setMaxWaitMillis(RedisConfig.getMaxWaitMillis());
        config.setTestOnBorrow(RedisConfig.isTestOnBorrow());
        config.setTestOnReturn(RedisConfig.isTestOnReturn());
        config.setTestWhileIdle(RedisConfig.isTestWhileIdle());
        jedisPool = new JedisPool(config, RedisConfig.getIp(), RedisConfig.getPort(), RedisConfig.getMaxWaitMillis(), RedisConfig.getPass());
    }


    /**
     * 获取Jedis连接池
     *
     * @return
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
            logger.error("sorry,redis connect error !!", e);
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

    public RedisHash HASH() {
        return HASH;
    }

    public RedisKeys KEYS() {
        return KEYS;
    }

    public RedisList LIST() {
        return LIST;
    }

    public RedisSets SETS() {
        return SETS;
    }

    public RedisSortSets SORTSETS() {
        return SORTSETS;
    }

    public RedisStrings STRINGS() {
        return STRINGS;
    }
}
