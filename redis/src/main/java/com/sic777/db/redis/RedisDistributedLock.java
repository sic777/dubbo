package com.sic777.db.redis;

import redis.clients.jedis.Jedis;

import java.util.Collections;

/**
 * <p>redis distributed lock
 *
 * @author sic777
 * @version 0.0.1
 * @since 0.0.1
 */
public class RedisDistributedLock {
    private static final Long RELEASE_SUCCESS = 1L;

    /**
     * try get distributed lock
     *
     * @param lockKey    lock key
     * @param clientFlag client flag
     * @param expireTime expire time
     * @return result
     */
    public static boolean tryGetDistributedLock(String lockKey, String clientFlag, int expireTime) {
        Jedis jedis = null;
        try {
            jedis = Redis.instance().getJedis();
            String result = jedis.set(lockKey, clientFlag, "NX", "EX", expireTime);
            return "OK".equals(result);
        } finally {
            Redis.instance().closeJedis(jedis);
        }
    }


    /**
     * release distributed lock
     *
     * @param lockKey    lpck key
     * @param clientFlag client flag
     * @return result
     */
    public static boolean releaseDistributedLock(String lockKey, String clientFlag) {
        Jedis jedis = null;
        try {
            jedis = Redis.instance().getJedis();
            String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
            Object result = jedis.eval(script, Collections.singletonList(lockKey), Collections.singletonList(clientFlag));
            return RELEASE_SUCCESS.equals(result);
        } finally {
            Redis.instance().closeJedis(jedis);
        }
    }
}
