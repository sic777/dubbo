package com.sic777.rmdb.bean.type;

/**
 * <p>
 *
 * @author sic777
 * @version 0.0.1
 * @since 0.0.1
 */
public enum Sort {
    ASC("asc"),
    DESC("desc");
    private String key;

    Sort(String key) {
        this.key = key;
    }

    public String key() {
        return key;
    }

    public static Sort fromKey(String key) {
        if (null != key) {
            for (Sort s : Sort.values()) {
                if (s.key.equals(key)) {
                    return s;
                }
            }
        }
        return ASC;
    }
}
