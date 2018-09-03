package com.sic777.dubbo.common.enums;

import com.sic777.utils.proguard.NoProguard;

/**
 * <p>注册中心客户端类型枚举</p>
 *
 * @author Zhengzhenxie
 * @version v1.0
 * @since 2018-04-24
 */
@NoProguard
public enum RegistryTransportType {
    UNKNOWN("unknown"),
    NETTY("netty"),
    MINA("mina");

    private final String type;

    RegistryTransportType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public static RegistryTransportType fromType(String type) {
        for (RegistryTransportType t : RegistryTransportType.values()) {
            if (t.getType().equals(type)) {
                return t;
            }
        }
        return RegistryTransportType.UNKNOWN;
    }
}
