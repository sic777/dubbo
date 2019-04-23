package com.sic777.dubbo.common.enums;

/**
 * <p>集群方式枚举</p>
 *
 * @author Zhengzhenxie
 * @version v1.0
 * @since 2018-04-24
 */
public enum ClusterType {
    /**
     * 未知
     */
    UNKNOWN("unknown"),
    /**
     * 快速失败，只发起一次调用，失败立即报错。通常用于非幂等性的写操作，比如新增记录。
     */
    FAILOVER("failover"),
    /**
     * 失败安全，出现异常时，直接忽略。通常用于写入审计日志等操作。
     */
    FAILFAST("failfast"),
    /**
     * 失败自动恢复，后台记录失败请求，定时重发。通常用于消息通知操作。
     */
    LEAST_ACTIVE("failsafe"),
    /**
     * 并行调用多个服务器，只要一个成功即返回。通常用于实时性要求较高的读操作，但需要浪费更多服务资源。可通过 forks="2" 来设置最大并行数。
     */
    FAILBACK("failback"),
    /**
     * 广播调用所有提供者，逐个调用，任意一台报错则报错 2。通常用于通知所有提供者更新缓存或日志等本地资源信息。
     */
    FOWKING("forking");

    private final String type;

    ClusterType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public static ClusterType fromType(String type) {
        for (ClusterType t : ClusterType.values()) {
            if (t.type.equals(type)) {
                return t;
            }
        }
        return ClusterType.UNKNOWN;
    }
}
