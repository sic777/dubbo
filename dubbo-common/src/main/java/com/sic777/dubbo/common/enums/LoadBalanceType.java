package com.sic777.dubbo.common.enums;


/**
 * <p>负载均衡策略枚举</p>
 *
 * @author Zhengzhenxie
 * @version v1.0
 * @since 2018-04-24
 */
public enum LoadBalanceType {
    /**
     * 未配置
     */
    UNKNOWN("unknown"),
    /**
     * 随机
     */
    RANDOM("random"),
    /**
     * 轮循
     */
    ROUND_ROBIN("roundrobin"),
    /**
     * 最少活跃
     */
    LEAST_ACTIVE("leastactive");

    private final String type;

    LoadBalanceType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public static LoadBalanceType fromType(String type) {
        for (LoadBalanceType t : LoadBalanceType.values()) {
            if (t.type.equals(type)) {
                return t;
            }
        }
        return LoadBalanceType.UNKNOWN;
    }
}
