package com.sic777.db.mongo.type;

import com.sic777.common.utils.proguard.NoProguard;

/**
 * <p></p>
 *
 * @author Zhengzhenxie
 * @version v1.0
 * @since 2018-08-15
 */
@NoProguard
public enum DataFieldType {

    Unkown("unkown"),

    String("string"),

    Int("int"),

    Bool("boolean"),

    Date("date"),

    Float("float");
    private final String desc;

    DataFieldType(String d) {
        this.desc = d;
    }

    public String desc() {
        return desc;
    }

    public static final DataFieldType from(String desc) {
        for (DataFieldType dt : values()) {
            if (dt.desc().equals(desc)) {
                return dt;
            }
        }
        return Unkown;
    }
}