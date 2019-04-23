package com.sic777.common.utils.ssl;



/**
 * <p>证书类型
 *
 * @author sic777
 * @since 0.0.1
 */
public enum KeyStoreType {
    /**
     * PKCS12
     */
    PKCS12("PKCS12");


    private final String type;

    KeyStoreType(String type) {
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
