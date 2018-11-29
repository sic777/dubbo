package com.sic777.rmdb.bean;

import com.sic777.rmdb.bean.type.Sort;

/**
 * <p>
 *
 * @author sic777
 * @version 0.0.1
 * @since 0.0.1
 */
public class Order {
    private final String key;
    private final Sort sort;

    public Order(String key, Sort sort) {
        this.key = key;
        this.sort = sort;
    }

    public String getKey() {
        return key;
    }

    public Sort getSort() {
        return sort;
    }

    @Override
    public String toString() {
        return "Order{" +
                "key='" + key + '\'' +
                ", sort=" + sort +
                '}';
    }
}
