package com.sic777.rmdb.bean.type;

/**
 * <p>
 *
 * @author sic777
 * @version 0.0.1
 * @since 0.0.1
 */
public enum Operation {
    /**
     * unknown
     */
    UNKNOWN(""),
    /**
     * 包含于任意一个值
     */
    IN("$in"),
    /**
     * 小于
     */
    LT("$lt"),
    /**
     * 小于或等于
     */
    LTE("$lte"),
    /**
     * 大于
     */
    GT("$gt"),
    /**
     * 大于或等于
     */
    GTE("$gte"),
    /**
     * 模糊匹配
     */
    LIKE("$like");
    private String op;

    Operation(String op) {
        this.op = op;
    }

    public String op() {
        return op;
    }

    public static Operation fromOp(String op) {
        if (null != op) {
            for (Operation o : Operation.values()) {
                if (o.op.equals(op)) {
                    return o;
                }
            }
        }
        return UNKNOWN;
    }
}
