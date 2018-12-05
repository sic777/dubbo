package com.sic777.dubbo.common.enums;

import com.sic777.common.utils.proguard.NoProguard;

/**
 * <p>服务提供者协议类型枚举</p>
 *
 * @author Zhengzhenxie
 * @version v1.0
 * @since 2018-04-24
 */
@NoProguard
public enum DubboProtocolType {
    UNKNOWN("unknown"),
    DUBBO("dubbo"),
    RMI("rmi"),
    HESSIAN("hessian"),
    HTTP("http"),
    WEBSERVICE("webservice"),
    THRIFT("thrift"),
    MEMCACHED("memcached"),
    REDIS("redis");

    private final String type;

    DubboProtocolType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public static DubboProtocolType fromType(String type) {
        for (DubboProtocolType t : DubboProtocolType.values()) {
            if (t.getType().equals(type)) {
                return t;
            }
        }
        return DubboProtocolType.UNKNOWN;
    }
}
