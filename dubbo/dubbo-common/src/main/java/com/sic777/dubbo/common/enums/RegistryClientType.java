package com.sic777.dubbo.common.enums;

/**
 * <p>注册中心客户端类型枚举</p>
 *
 * @author Zhengzhenxie
 * @version v1.0
 * @since 2018-04-24
 */
public enum RegistryClientType {
    UNKNOWN("unknown"),
    CURATOR("curator"),
    ZKCLIENT("zkclient");

    private final String type;

    RegistryClientType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public static RegistryClientType fromType(String type) {
        for (RegistryClientType t : RegistryClientType.values()) {
            if (t.getType().equals(type)) {
                return t;
            }
        }
        return RegistryClientType.UNKNOWN;
    }
}
