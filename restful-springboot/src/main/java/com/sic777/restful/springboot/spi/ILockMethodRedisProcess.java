package com.sic777.restful.springboot.spi;


/**
 * <p>
 *
 * @author sic777
 * @version 0.0.1
 * @since 0.0.1
 */
public interface ILockMethodRedisProcess {
    /**
     * redis处理实现
     *
     * @param lockKey key
     * @param expire  过期时间/秒
     * @return 设置结果
     * @throws Exception
     */
    boolean process(String lockKey, int expire) throws Exception;
}
