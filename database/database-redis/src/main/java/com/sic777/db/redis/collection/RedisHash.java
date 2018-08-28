package com.sic777.db.redis.collection;

import com.sic777.db.redis.Redis;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * <p>对redis存储类型为HashMap的操作</p>
 *
 * @author Zhengzhenxie
 * @version v1.0
 * @since 2018-02-11 10:58
 */
public class RedisHash {
    /**
     * 从hash中删除指定的存储
     *
     * @param key
     * @param fields 存储的名字
     * @return 状态码，1成功，0失败
     */
    public long hdel(String key, String... fields) {
        Jedis jedis = null;
        try {
            jedis = Redis.instance().getRedisPool().getResource();
            long s = jedis.hdel(key, fields);
            return s;
        } finally {
            if (jedis != null) {
                Redis.instance().closeJedis(jedis);
            }
        }
    }


    /**
     * 从hash中删除指定的存储
     *
     * @param keys
     * @return
     */
    public long hdelKeys(String... keys) {
        Jedis jedis = null;
        try {
            jedis = Redis.instance().getRedisPool().getResource();
            long s = jedis.del(keys);
            return s;
        } finally {
            if (jedis != null) {
                Redis.instance().closeJedis(jedis);
            }
        }
    }

    /**
     * 从hash中删除指定的存储
     *
     * @param key
     * @return
     */
    public long hdel(String key) {
        Jedis jedis = null;
        try {
            jedis = Redis.instance().getRedisPool().getResource();
            long s = jedis.del(key);
            return s;
        } finally {
            if (jedis != null) {
                Redis.instance().closeJedis(jedis);
            }
        }
    }

    /**
     * 测试hash中指定的存储是否存在
     *
     * @param key
     * @param field 存储的名字
     * @return 1存在，0不存在
     */
    public boolean hexists(String key, String field) {
        Jedis jedis = null;
        try {
            jedis = Redis.instance().getRedisPool().getResource();
            boolean s = jedis.hexists(key, field);
            return s;
        } finally {
            if (jedis != null) {
                Redis.instance().closeJedis(jedis);
            }
        }
    }

    /**
     * 返回hash中指定存储位置的值
     *
     * @param key
     * @param field 存储的名字
     * @return 存储对应的值
     */
    public String hget(String key, String field) {
        Jedis jedis = null;
        try {
            jedis = Redis.instance().getRedisPool().getResource();
            String s = jedis.hget(key, field);
            return s;
        } finally {
            if (jedis != null) {
                Redis.instance().closeJedis(jedis);
            }
        }
    }

    public byte[] hget(byte[] key, byte[] field) {
        Jedis jedis = null;
        try {
            jedis = Redis.instance().getRedisPool().getResource();
            byte[] s = jedis.hget(key, field);
            return s;
        } finally {
            if (jedis != null) {
                Redis.instance().closeJedis(jedis);
            }
        }
    }

    /**
     * 以Map的形式返回hash中的存储和值
     *
     * @param key
     * @return
     */
    public Map<String, String> hgetAll(String key) {
        Jedis jedis = null;
        try {
            jedis = Redis.instance().getRedisPool().getResource();
            Map<String, String> map = jedis.hgetAll(key);
            return map;
        } finally {
            if (jedis != null) {
                Redis.instance().closeJedis(jedis);
            }
        }
    }

    /**
     * 以Map的形式返回hash中的存储和值
     *
     * @param key
     * @return
     */
    public Map<String, String> hgetAll(int db_index, String key) {
        Jedis jedis = null;
        try {
            jedis = Redis.instance().getRedisPool().getResource();
            jedis.select(db_index);
            Map<String, String> map = jedis.hgetAll(key);
            return map;
        } finally {
            if (jedis != null) {
                Redis.instance().closeJedis(jedis);
            }
        }
    }

    public void hsetAll(String key, Map<String, String> map) {
        Jedis jedis = null;
        try {
            jedis = Redis.instance().getRedisPool().getResource();
            Pipeline pipeline = jedis.pipelined();
            for (Map.Entry<String, String> entry : map.entrySet()) {
                pipeline.hset(key, entry.getKey(), entry.getValue());
            }
            pipeline.sync();
        } finally {
            if (jedis != null) {
                Redis.instance().closeJedis(jedis);
            }
        }
    }

    public void hsetAll(int db_index, String key, Map<String, String> hash) {
        Jedis jedis = null;
        try {
            jedis = Redis.instance().getRedisPool().getResource();
            jedis.select(db_index);
            jedis.hmset(key, hash);
        } finally {
            if (jedis != null) {
                Redis.instance().closeJedis(jedis);
            }
        }
    }

    /**
     * 添加一个对应关系
     *
     * @param key
     * @param field
     * @param value
     * @return 状态码 1成功，0失败，field已存在将更新，也返回0
     **/
    public long hset(String key, String field, String value) {
        Jedis jedis = null;
        try {
            jedis = Redis.instance().getRedisPool().getResource();
            long s = jedis.hset(key, field, value);
            return s;
        } finally {
            if (jedis != null) {
                Redis.instance().closeJedis(jedis);
            }
        }
    }

    public long hset(String key, String field, byte[] value) {
        Jedis jedis = null;
        try {
            jedis = Redis.instance().getRedisPool().getResource();
            long s = jedis.hset(key.getBytes(), field.getBytes(), value);
            return s;
        } finally {
            if (jedis != null) {
                Redis.instance().closeJedis(jedis);
            }
        }
    }

    /**
     * 添加对应关系，只有在field不存在时才执行
     *
     * @param key
     * @param field
     * @param value
     * @return 状态码 1成功，0失败field已存
     **/
    public long hsetnx(String key, String field, String value) {
        Jedis jedis = null;
        try {
            jedis = Redis.instance().getRedisPool().getResource();
            long s = jedis.hsetnx(key, field, value);
            return s;
        } finally {
            if (jedis != null) {
                Redis.instance().closeJedis(jedis);
            }
        }
    }

    /**
     * 获取hash中value的集合
     *
     * @param key
     * @return
     */
    public List<String> hvals(String key) {
        Jedis jedis = null;
        try {
            jedis = Redis.instance().getRedisPool().getResource();
            List<String> list = jedis.hvals(key);
            return list;
        } finally {
            if (jedis != null) {
                Redis.instance().closeJedis(jedis);
            }
        }
    }

    /**
     * 在指定的存储位置加上指定的数字，存储位置的值必须可转为数字类型
     *
     * @param key
     * @param field 存储位置
     * @param value 要增加的值,可以是负数
     * @return 增加指定数字后，存储位置的值
     */
    public long hincrby(String key, String field, long value) {
        Jedis jedis = null;
        try {
            jedis = Redis.instance().getRedisPool().getResource();
            long s = jedis.hincrBy(key, field, value);
            return s;
        } finally {
            if (jedis != null) {
                Redis.instance().closeJedis(jedis);
            }
        }
    }

    /**
     * 返回指定hash中的所有存储名字,类似Map中的keySet方法
     *
     * @param key
     * @return 存储名称的集合
     */
    public Set<String> hkeys(String key) {
        Jedis jedis = null;
        try {
            jedis = Redis.instance().getRedisPool().getResource();
            Set<String> set = jedis.hkeys(key);
            return set;
        } finally {
            if (jedis != null) {
                Redis.instance().closeJedis(jedis);
            }
        }
    }

    /**
     * 获取hash中存储的个数，类似Map中size方法
     *
     * @param key
     * @return long 存储的个数
     */
    public long hlen(String key) {
        Jedis jedis = null;
        try {
            jedis = Redis.instance().getRedisPool().getResource();
            long len = jedis.hlen(key);
            return len;
        } finally {
            if (jedis != null) {
                Redis.instance().closeJedis(jedis);
            }
        }
    }

    /**
     * 根据多个key，获取对应的value，返回List,如果指定的key不存在,List对应位置为null
     *
     * @param key
     * @param fields 存储位置
     * @return
     */
    public List<String> hmget(String key, String... fields) {
        Jedis jedis = null;
        try {
            jedis = Redis.instance().getRedisPool().getResource();
            List<String> list = jedis.hmget(key, fields);
            return list;
        } finally {
            if (jedis != null) {
                Redis.instance().closeJedis(jedis);
            }
        }
    }

    public List<byte[]> hmget(byte[] key, byte[]... fields) {
        Jedis jedis = null;
        try {
            jedis = Redis.instance().getRedisPool().getResource();
            List<byte[]> list = jedis.hmget(key, fields);
            return list;
        } finally {
            if (jedis != null) {
                Redis.instance().closeJedis(jedis);
            }
        }
    }

    /**
     * 添加对应关系，如果对应关系已存在，则覆盖
     *
     * @param key
     * @param map 对应关系
     * @return 状态，成功返回OK
     */
    public String hmset(String key, Map<String, String> map) {
        Jedis jedis = null;
        try {
            jedis = Redis.instance().getRedisPool().getResource();
            String s = jedis.hmset(key, map);
            return s;
        } finally {
            if (jedis != null) {
                Redis.instance().closeJedis(jedis);
            }
        }
    }

    /**
     * 添加对应关系，如果对应关系已存在，则覆盖
     *
     * @param key
     * @param map 对应关系
     * @return 状态，成功返回OK
     */
    public String hmset(byte[] key, Map<byte[], byte[]> map) {
        Jedis jedis = null;
        try {
            jedis = Redis.instance().getRedisPool().getResource();
            String s = jedis.hmset(key, map);
            return s;
        } finally {
            if (jedis != null) {
                Redis.instance().closeJedis(jedis);
            }
        }
    }

}
