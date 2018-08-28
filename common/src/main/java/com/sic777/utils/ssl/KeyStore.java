package com.sic777.utils.ssl;


/**
 * <p>证书实体
 *
 * @author sic777
 * @since 0.0.1
 */
public class KeyStore {
    /**
     * 证书文件
     */
    private final String field;
    /**
     * 证书密码
     */
    private final String pwd;
    /**
     * 证书类型
     */
    private final KeyStoreType type;
    /**
     * 证书是否存储在本地
     */
    private final boolean local;


    public KeyStore(String field, String pwd) {
        this.field = field;
        this.pwd = pwd;
        this.type = KeyStoreType.PKCS12;
        this.local = true;
    }

    public KeyStore(String field, String pwd, boolean local) {
        this.field = field;
        this.pwd = pwd;
        this.type = KeyStoreType.PKCS12;
        this.local = local;
    }

    public KeyStore(String field, String pwd, KeyStoreType type) {
        this.field = field;
        this.pwd = pwd;
        this.type = type;
        this.local = true;
    }

    public KeyStore(String field, String pwd, KeyStoreType type, boolean local) {
        this.field = field;
        this.pwd = pwd;
        this.type = type;
        this.local = local;
    }

    public String getField() {
        return field;
    }

    public String getPwd() {
        return pwd;
    }

    public KeyStoreType getType() {
        return type;
    }

    public boolean isLocal() {
        return local;
    }

    @Override
    public String toString() {
        return "KeyStore{" +
                "field='" + field + '\'' +
                ", pwd='" + pwd + '\'' +
                ", type=" + type +
                ", local=" + local +
                '}';
    }
}
