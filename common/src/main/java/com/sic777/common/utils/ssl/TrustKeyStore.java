package com.sic777.common.utils.ssl;


/**
 * <p>信任库实体
 *
 * @author sic777
 * @since 0.0.1
 */
public class TrustKeyStore {
    /**
     * 信任库文件
     */
    private final String field;
    /**
     * 信任库密码
     */
    private final String pwd;
    /**
     * 信任库是否存储在本地
     */
    private final boolean local;

    public TrustKeyStore(String field, String pwd) {
        this.field = field;
        this.pwd = pwd;
        this.local = true;
    }

    public TrustKeyStore(String field, String pwd, boolean local) {
        this.field = field;
        this.pwd = pwd;
        this.local = local;
    }

    public String getField() {
        return field;
    }

    public String getPwd() {
        return pwd;
    }

    public boolean isLocal() {
        return local;
    }

    @Override
    public String toString() {
        return "TrustKeyStore{" +
                "field='" + field + '\'' +
                ", pwd='" + pwd + '\'' +
                ", local=" + local +
                '}';
    }
}
