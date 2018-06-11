package com.sic777.dubbo.common.enums;

/**
 * <p>日志类型枚举</p>
 *
 * @author Zhengzhenxie
 * @version v1.0
 * @since 2018-04-24
 */
public enum ApplicationLogType {
    UNKNOWN("unknown"),
    LOG4J("log4j"),
    SLF4J("slf4j"),
    JCL("jcl"),
    JDK("jdk"),
    LOG4J2("log4j2");

    private final String type;

    ApplicationLogType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public static ApplicationLogType fromType(String type) {
        for (ApplicationLogType t : ApplicationLogType.values()) {
            if (t.getType().equals(type)) {
                return t;
            }
        }
        return ApplicationLogType.UNKNOWN;
    }
}
