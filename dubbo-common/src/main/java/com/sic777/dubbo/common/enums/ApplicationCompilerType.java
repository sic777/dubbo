package com.sic777.dubbo.common.enums;

import com.sic777.common.utils.proguard.NoProguard;

/**
 * <p>Java字节码编译器枚举</p>
 *
 * @author Zhengzhenxie
 * @version v1.0
 * @since 2018-04-24
 */
@NoProguard
public enum ApplicationCompilerType {
    UNKNOWN("unknown"),
    JAVASSIST("javassist"),
    JDK("jdk");

    private final String type;

    ApplicationCompilerType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public static ApplicationCompilerType fromType(String type) {
        for (ApplicationCompilerType t : ApplicationCompilerType.values()) {
            if (t.getType().equals(type)) {
                return t;
            }
        }
        return ApplicationCompilerType.UNKNOWN;
    }
}
