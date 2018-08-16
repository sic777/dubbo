package com.sic777.db.mongo.type;

/**
 * <p></p>
 *
 * @author Zhengzhenxie
 * @version v1.0
 * @since 2018-08-15
 */
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