package com.sic777.utils.generator;

import java.util.UUID;

/**
 * <p>UUID生成器
 *
 * @author sic777
 * @since 0.0.1
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
