package com.sic777.utils.generator;

/**
 * <p>ID生成器</p>
 *
 * @author Zhengzhenxie<br>
 *         <br>2018-01-01
 * @version v1.0
 * @since 1.7
 */
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
