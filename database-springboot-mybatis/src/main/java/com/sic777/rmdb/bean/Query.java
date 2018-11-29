package com.sic777.rmdb.bean;

import com.sic777.rmdb.bean.type.Operation;

/**
 * <p>
 *
 * @author sic777
 * @version 0.0.1
 * @since 0.0.1
 */
public class Query {
    private final String key;
    private final Operation op;
    private final Object value;

    public Query(String key, Operation op, Object value) {
        this.key = key;
        this.op = op;
        this.value = value;
    }

    public String getKey() {
        return key;
    }


    public Operation getOp() {
        return op;
    }


    public Object getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Query{" +
                "key='" + key + '\'' +
                ", op=" + op +
                ", value=" + value +
                '}';
    }
}
