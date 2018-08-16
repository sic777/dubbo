package com.sic777.db.mongo.type;

/**
 * <p></p>
 *
 * @author Zhengzhenxie
 * @version v1.0
 * @since 2018-08-15
 */
public enum MongoLogicalType {
    /**
     * 未知类型
     */
    Unkown(""),
    /**
     * 与关系
     */
    And("AND"),
    /**
     * 或关系
     */
    Or("OR"),;

    private String logical;

    MongoLogicalType(String logical) {
        this.logical = logical;
    }

    public String logical() {
        return logical;
    }

    public static final MongoLogicalType fromLogical(String logical) {
        for (MongoLogicalType logicalType : values()) {
            if (logicalType.logical.equals(logical)) {
                return logicalType;
            }
        }
        return Unkown;
    }
}
