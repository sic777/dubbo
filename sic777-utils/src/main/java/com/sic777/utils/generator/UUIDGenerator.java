package com.sic777.utils.generator;

import java.util.UUID;

/**
 * <p>UUID生成器</p>
 *
 * @author Zhengzhenxie<br>
 *         <br>2018-01-01
 * @version v1.0
 * @since 1.7
 */
public class UUIDGenerator implements IGenerator<String> {
    private static final UUIDGenerator singleton = new UUIDGenerator();

    public static final UUIDGenerator instance() {
        return singleton;
    }

    private UUIDGenerator() {
    }

    @Override
    public String next() {
        return UUID.randomUUID().toString();
    }

}
