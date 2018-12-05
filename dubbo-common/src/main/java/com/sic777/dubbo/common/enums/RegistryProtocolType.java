package com.sic777.dubbo.common.enums;

import com.sic777.common.utils.proguard.NoProguard;

/**
 * <p>注册中心协议类型枚举</p>
 *
 * @author Zhengzhenxie
 * @version v1.0
 * @since 2018-04-24
 */
@NoProguard
public enum RegistryProtocolType {
    UNKNOWN("unknown"),
    ZOOKEEPER("zookeeper"),
    MULTICAST("multicast"),
    REDIS("redis");

    private final String type;

    RegistryProtocolType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public static RegistryProtocolType fromType(String type) {
        for (RegistryProtocolType t : RegistryProtocolType.values()) {
            if (t.getType().equals(type)) {
                return t;
            }
        }
        return RegistryProtocolType.UNKNOWN;
    }
}
