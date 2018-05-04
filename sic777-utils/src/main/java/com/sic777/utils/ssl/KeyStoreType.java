package com.sic777.utils.ssl;

/**
 * <p>证书类型</p>
 *
 * @author Zhengzhenxie
 * @version v1.0
 * @since 2018-02-10 17:12
 */
public enum KeyStoreType {
    /**
     * PKCS12
     */
    PKCS12("PKCS12");


    private final String type;

    private KeyStoreType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public static KeyStoreType fromType(String type) {
        for (KeyStoreType t : values()) {
            if (t.type.equals(type)) {
                return t;
            }
        }
        return PKCS12;
    }
}
