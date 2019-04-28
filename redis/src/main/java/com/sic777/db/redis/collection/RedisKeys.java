package com.sic777.db.redis.collection;

import com.sic777.db.redis.Redis;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.SortingParams;
import redis.clients.util.SafeEncoder;

import java.util.List;
import java.util.Set;

/**
 * <p>对redis key的操作</p>
 *
 * @author Zhengzhenxie
 * @version v1.0
 * @since 2018-02-11 10:58
 */
public class RedisKeys {
    /**
     * 更改key
     *
     * @param oldKey
     * @param neyKey
     * @return 状态码
     */
    public String rename(String oldKey, String neyKey) {
        return rename(SafeEncoder.encode(oldKey), SafeEncoder.encode(neyKey));
    }

    /**
     * 更改key,仅当新key不存在时才执行
     *
     * @param oldKey
     * @param neyKey
     * @return 状态码
     */
    public long renamenx(String oldKey, String neyKey) {
        Jedis jedis = null;
        try {
            jedis = Redis.instance().getRedisPool().getResource();
            long status = jedis.renamenx(oldKey, neyKey);
            return status;
        } finally {
            Redis.instance().closeJedis(jedis);
        }
    }

    /**
     * 批量更改key
     *
     * @param oldKeys
     * @param neyKeys
     * @return 状态码
     */
    public String rename(byte[] oldKeys, byte[] neyKeys) {
        Jedis jedis = null;
        try {
            jedis = Redis.instance().getRedisPool().getResource();
            String status = jedis.rename(oldKeys, neyKeys);
            return status;
        } finally {
            Redis.instance().closeJedis(jedis);
        }
    }

    /**
     * 设置key的过期时间
     *
     * @param key
     * @param seconds
     * @return 影响的记录数
     */
    public long expired(String key, int seconds) {
        Jedis jedis = null;
        try {
            jedis = Redis.instance().getRedisPool().getResource();
            long count = jedis.expire(key, seconds);
            return count;
        } finally {
            Redis.instance().closeJedis(jedis);
        }
    }

    /**
     * 设置key的过期时间,它是距历元（即格林威治标准时间 1970 年 1 月 1 日的 00:00:00，格里高利历）的偏移量。
     *
     * @param key
     * @param unixTime
     * @return 影响的记录数
     */
    public long expireAt(String key, long unixTime) {
        Jedis jedis = null;
        try {
            jedis = Redis.instance().getRedisPool().getResource();
            long count = jedis.expireAt(key, unixTime);
            return count;
        } finally {
            Redis.instance().closeJedis(jedis);
        }
    }

    /**
     * 查询key的过期时间
     *
     * @param key
     * @return 以秒为单位的时间表示
     */
    public long ttl(String key) {
        Jedis jedis = null;
        try {
            jedis = Redis.instance().getRedisPool().getResource();
            return jedis.ttl(key);
        } finally {
            Redis.instance().closeJedis(jedis);
        }
    }

    /**
     * 取消对key过期时间的设置
     *
     * @param key
     * @return 影响的记录数
     */
    public long persist(String key) {
        Jedis jedis = null;
        try {
            jedis = Redis.instance().getRedisPool().getResource();
            long count = jedis.persist(key);
            return count;
        } finally {
            Redis.instance().closeJedis(jedis);
        }
    }

    /**
     * 不定量删除key对应的记录
     *
     * @param keys
     * @return 删除的记录数
     */
    public long del(String... keys) {
        Jedis jedis = null;
        try {
            jedis = Redis.instance().getRedisPool().getResource();
            long count = jedis.del(keys);
            return count;
        } finally {
            Redis.instance().closeJedis(jedis);
        }
    }

    /**
     * 不定量删除keys对应的记录
     *
     * @param keys
     * @return 删除的记录数
     */
    public long del(byte[]... keys) {
        Jedis jedis = null;
        try {
            jedis = Redis.instance().getRedisPool().getResource();
            long count = jedis.del(keys);
            return count;
        } finally {
            Redis.instance().closeJedis(jedis);
        }
    }

    /**
     * 判断key是否存在
     *
     * @param key
     * @return boolean
     */
    public boolean exists(String key) {
        Jedis jedis = null;
        try {
            jedis = Redis.instance().getRedisPool().getResource();
            boolean exists = jedis.exists(key);
            return exists;
        } finally {
            Redis.instance().closeJedis(jedis);
        }
    }

    /**
     * 判断key是否存在
     *
     * @param db_index 数据库编号
     * @param key
     * @return
     */
    public boolean exists(int db_index, String key) {
        Jedis jedis = null;
        try {
            jedis = Redis.instance().getRedisPool().getResource();
            jedis.select(db_index);
            boolean exists = jedis.exists(key);
            return exists;
        } finally {
            Redis.instance().closeJedis(jedis);
        }
    }

    /**
     * 对List,Set,SortSet进行排序,尽量规避数据集庞大情景使用该方法
     *
     * @param key
     * @return 集合的全部记录
     **/
    public List<String> sort(String key) {
        Jedis jedis = null;
        try {
            jedis = Redis.instance().getRedisPool().getResource();
            List<String> list = jedis.sort(key);
            return list;
        } finally {
            Redis.instance().closeJedis(jedis);
        }
    }

    /**
     * 对List,Set,SortSet进行排序或limit
     *
     * @param key
     * @param params 定义排序类型或limit的起止位置.
     * @return 全部或部分记录
     **/
    public List<String> sort(String key, SortingParams params) {
        Jedis jedis = null;
        try {
            jedis = Redis.instance().getRedisPool().getResource();
            List<String> list = jedis.sort(key, params);
            return list;
        } finally {
            Redis.instance().closeJedis(jedis);
        }
    }

    /**
     * 返回指定key存储的类型
     *
     * @param key
     * @return
     **/
    public String type(String key) {
        Jedis jedis = null;
        try {
            jedis = Redis.instance().getRedisPool().getResource();
            String type = jedis.type(key);
            return type;
        } finally {
            Redis.instance().closeJedis(jedis);
        }
    }

    /**
     * 查找所有匹配给定的模式的键
     *
     * @param pattern key的表达式,*表示多个，？表示一个
     */
    public Set<String> kyes(String pattern) {
        Jedis jedis = null;
        try {
            jedis = Redis.instance().getRedisPool().getResource();
            Set<String> set = jedis.keys(pattern);
            return set;
        } finally {
            Redis.instance().closeJedis(jedis);
        }
    }
}
