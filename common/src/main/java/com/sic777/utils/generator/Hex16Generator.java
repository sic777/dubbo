package com.sic777.utils.generator;

import com.sic777.utils.proguard.NoProguard;

/**
 * <p>Hex16ID生成器
 *
 * @author sic777
 * @since 0.0.1
 */
@NoProguard
public class Hex16Generator implements IGenerator<String> {
    private static final Hex16Generator singleton = new Hex16Generator();

    public static final Hex16Generator instance() {
        return singleton;
    }

    private Hex16Generator() {
    }

    @Override
    public String next() throws Exception {
        long guid = GuidManager.instance().generateGuid(8);
        return Long.toHexString(guid);
    }
}
