package com.sic777.db.redis.collection;

import com.sic777.db.redis.Redis;
import redis.clients.jedis.Jedis;

import java.util.Set;

/**
 * <p>对redis存储类型为set的操作</p>
 *
 * @author Zhengzhenxie
 * @version v1.0
 * @since 2018-02-11 10:58
 */
public class RedisSet {

    /**
     * 向Set添加一条记录
     *
     * @param key
     * @param members
     * @return 如果member已存在返回0, 否则返回1
     */
    public long sadd(String key, String... members) {
        Jedis jedis = null;
        try {
            jedis = Redis.instance().getRedisPool().getResource();
            long s = jedis.sadd(key, members);
            return s;
        } finally {
            if (jedis != null) {
                Redis.instance().closeJedis(jedis);
            }
        }
    }

    /**
     * 批量向Set添加记录
     *
     * @param keys
     * @param members
     * @return 如果member已存在返回0, 否则返回1
     */
    public long sadd(byte[] keys, byte[] members) {
        Jedis jedis = null;
        try {
            jedis = Redis.instance().getRedisPool().getResource();
            long s = jedis.sadd(keys, members);
            return s;
        } finally {
            if (jedis != null) {
                Redis.instance().closeJedis(jedis);
            }
        }
    }

    /**
     * 获取给定key中元素个数
     *
     * @param key
     * @return 元素个数
     */
    public long scard(String key) {
        Jedis jedis = null;
        try {
            jedis = Redis.instance().getRedisPool().getResource();
            long len = jedis.scard(key);
            return len;
        } finally {
            if (jedis != null) {
                Redis.instance().closeJedis(jedis);
            }
        }
    }

    /**
     * 返回从第一组和所有的给定集合之间的差异的成员
     *
     * @param keys
     * @return 差异的成员集合
     */
    public Set<String> sdiff(String... keys) {
        Jedis jedis = null;
        try {
            jedis = Redis.instance().getRedisPool().getResource();
            Set<String> set = jedis.sdiff(keys);
            return set;
        } finally {
            if (jedis != null) {
                Redis.instance().closeJedis(jedis);
            }
        }
    }

    /**
     * 这个命令等于sdiff,但返回的不是结果集,而是将结果集存储在新的集合中，如果目标已存在，则覆盖。
     *
     * @param neyKey 新结果集的key
     * @param keys   比较的集合
     * @return 新集合中的记录数
     **/
    public long sdiffstore(String neyKey, String... keys) {
        Jedis jedis = null;
        try {
            jedis = Redis.instance().getRedisPool().getResource();
            long s = jedis.sdiffstore(neyKey, keys);
            return s;
        } finally {
            if (jedis != null) {
                Redis.instance().closeJedis(jedis);
            }
        }
    }

    /**
     * 返回给定集合交集的成员,如果其中一个集合为不存在或为空，则返回空Set
     *
     * @param keys
     * @return 交集成员的集合
     **/
    public Set<String> sinter(String... keys) {
        Jedis jedis = null;
        try {
            jedis = Redis.instance().getRedisPool().getResource();
            Set<String> set = jedis.sinter(keys);
            return set;
        } finally {
            if (jedis != null) {
                Redis.instance().closeJedis(jedis);
            }
        }
    }

    /**
     * 这个命令等于sinter,但返回的不是结果集,而是将结果集存储在新的集合中，如果目标已存在，则覆盖。
     *
     * @param neyKey 新结果集的key
     * @param keys   比较的集合
     * @return 新集合中的记录数
     **/
    public long sinterstore(String neyKey, String... keys) {
        Jedis jedis = null;
        try {
            jedis = Redis.instance().getRedisPool().getResource();
            long s = jedis.sinterstore(neyKey, keys);
            return s;
        } finally {
            if (jedis != null) {
                Redis.instance().closeJedis(jedis);
            }
        }
    }

    /**
     * 确定一个给定的值是否存在
     *
     * @param key
     * @param member 要判断的值
     * @return 存在返回1，不存在返回0
     **/
    public boolean sismember(String key, String member) {
        Jedis jedis = null;
        try {
            jedis = Redis.instance().getRedisPool().getResource();
            boolean s = jedis.sismember(key, member);
            return s;
        } finally {
            if (jedis != null) {
                Redis.instance().closeJedis(jedis);
            }
        }
    }

    /**
     * 返回集合中的所有成员
     *
     * @param key
     * @return 成员集合
     */
    public Set<String> smembers(String key) {
        Jedis jedis = null;
        try {
            jedis = Redis.instance().getRedisPool().getResource();
            Set<String> set = jedis.smembers(key);
            return set;
        } finally {
            if (jedis != null) {
                Redis.instance().closeJedis(jedis);
            }
        }
    }

    public Set<byte[]> smembers(byte[] key) {
        Jedis jedis = null;
        try {
            jedis = Redis.instance().getRedisPool().getResource();
            Set<byte[]> set = jedis.smembers(key);
            return set;
        } finally {
            if (jedis != null) {
                Redis.instance().closeJedis(jedis);
            }
        }
    }

    /**
     * 将成员从源集合移出放入目标集合 <br>
     * 如果源集合不存在或不包哈指定成员，不进行任何操作，返回0<br>
     * 否则该成员从源集合上删除，并添加到目标集合，如果目标集合中成员已存在，则只在源集合进行删除
     *
     * @param srcKey 源集合
     * @param dstKey 目标集合
     * @param member 源集合中的成员
     * @return 状态码，1成功，0失败
     */
    public long smove(String srcKey, String dstKey, String member) {
        Jedis jedis = null;
        try {
            jedis = Redis.instance().getRedisPool().getResource();
            long s = jedis.smove(srcKey, dstKey, member);
            return s;
        } finally {
            if (jedis != null) {
                Redis.instance().closeJedis(jedis);
            }
        }
    }

    /**
     * 从集合中删除成员
     *
     * @param key
     * @return 被删除的成员
     */
    public String spop(String key) {
        Jedis jedis = null;
        try {
            jedis = Redis.instance().getRedisPool().getResource();
            String s = jedis.spop(key);
            return s;
        } finally {
            if (jedis != null) {
                Redis.instance().closeJedis(jedis);
            }
        }
    }

    /**
     * 从集合中删除指定成员
     *
     * @param key
     * @param members 要删除的成员
     * @return 状态码，成功返回1，成员不存在返回0
     */
    public long srem(String key, String... members) {
        Jedis jedis = null;
        try {
            jedis = Redis.instance().getRedisPool().getResource();
            long s = jedis.srem(key, members);
            return s;
        } finally {
            if (jedis != null) {
                Redis.instance().closeJedis(jedis);
            }
        }
    }

    /**
     * 合并多个集合并返回合并后的结果，合并后的结果集合并不保存<br>
     *
     * @param keys
     * @return 合并后的结果集合
     */
    public Set<String> sunion(String... keys) {
        Jedis jedis = null;
        try {
            jedis = Redis.instance().getRedisPool().getResource();
            Set<String> set = jedis.sunion(keys);
            return set;
        } finally {
            if (jedis != null) {
                Redis.instance().closeJedis(jedis);
            }
        }
    }

    /**
     * 合并多个集合并将合并后的结果集保存在指定的新集合中，如果新集合已经存在则覆盖
     *
     * @param neyKey 新集合的key
     * @param keys   要合并的集合
     **/
    public long sunionstore(String neyKey, String... keys) {
        Jedis jedis = null;
        try {
            jedis = Redis.instance().getRedisPool().getResource();
            long s = jedis.sunionstore(neyKey, keys);
            return s;
        } finally {
            if (jedis != null) {
                Redis.instance().closeJedis(jedis);
            }
        }
    }
}
