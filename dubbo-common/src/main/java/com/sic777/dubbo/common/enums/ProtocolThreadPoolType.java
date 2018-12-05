package com.sic777.dubbo.common.enums;

import com.sic777.common.utils.proguard.NoProguard;

/**
 * <p>协议线程池类型枚举</p>
 *
 * @author Zhengzhenxie
 * @version v1.0
 * @since 2018-04-24
 */
@NoProguard
public enum ProtocolThreadPoolType {
    UNKNOWN("unknown"),
    FIXED("fixed"),
    CACHED("cached");

    private final String type;

    ProtocolThreadPoolType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public static ProtocolThreadPoolType fromType(String type) {
        for (ProtocolThreadPoolType t : ProtocolThreadPoolType.values()) {
            if (t.getType().equals(type)) {
                return t;
            }
        }
        return ProtocolThreadPoolType.UNKNOWN;
    }
}
