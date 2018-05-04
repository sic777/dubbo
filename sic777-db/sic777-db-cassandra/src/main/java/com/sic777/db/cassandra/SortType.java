package com.sic777.db.cassandra;

/**
 * <p>排序类型</p>
 *
 * @author Zhengzhenxie
 * @version v1.0
 * @since 2018-02-11 11:02
 */
public enum SortType {
    /**
     * 升序
     */
    Asc("asc"),
    /**
     * 降序
     */
    Desc("desc");


    private final String type;

    public String type() {
        return type;
    }

    SortType(String type) {
        this.type = type;
    }

    public static final SortType fromType(String type) {
        if (null == type) {
            return Asc;
        }
        for (SortType scope : values()) {
            if (type.equals(scope.type())) {
                return scope;
            }
        }
        return Asc;
    }
}
