package com.sic777.utils.ssl;

/**
 * <p>信任库</p>
 *
 * @author Zhengzhenxie
 * @version v1.0
 * @since 2018-02-10 17:13
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
}
