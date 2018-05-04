package com.sic777.utils.ssl;


import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.ssl.TrustStrategy;

import javax.net.ssl.SSLContext;
import java.io.*;
import java.net.URL;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/**
 * <p></p>
 *
 * @author Zhengzhenxie
 * @version v1.0
 * @since 2018-02-10 17:13
 */
public class SSlContextManager {

    /**
     * 装载证书
     *
     * @param keyStore      证书实体
     * @param trustKeyStore 信任库实体
     * @return
     */
    public static SSLContext load(KeyStore keyStore, TrustKeyStore trustKeyStore) {
        InputStream ksis = null;
        SSLContext context = null;
        try {
            String keyStoreField = keyStore.getField();
            String keyStoreType = keyStore.getType().getType();
            String keyStorePass = keyStore.getPwd();
            if (keyStore.isLocal()) {//本地文件
                ksis = new FileInputStream(new File(keyStoreField));// 私钥证书
            } else {//网络资源
                URL url = new URL(keyStoreField);
                ksis = new BufferedInputStream(url.openStream());// 私钥证书
            }
            java.security.KeyStore ks = java.security.KeyStore.getInstance(keyStoreType != null ? keyStoreType : KeyStoreType.PKCS12.getType());
            ks.load(ksis, keyStorePass.toCharArray());

            SSLContextBuilder builder = SSLContexts.custom();
            builder.loadKeyMaterial(ks, keyStorePass.toCharArray());

            if (null != trustKeyStore && trustKeyStore.getField() != null) { // 如果有服务器证书
                String trustKeyStoreField = trustKeyStore.getField();
                String trustKeyStorePass = trustKeyStore.getPwd();
                if (trustKeyStore.isLocal()) {//本地文件
                    builder.loadTrustMaterial(new File(trustKeyStoreField), trustKeyStorePass.toCharArray());
                } else {//网络资源
                    URL url = new URL(trustKeyStoreField);
                    builder.loadTrustMaterial(url, trustKeyStorePass.toCharArray());
                }
            } else {//如果无配置服务器证书,暂默认服务器可信
                builder.loadTrustMaterial(null, new TrustStrategy() {
                    //信任所有
                    public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                        return true;
                    }
                });
            }
            context = builder.build();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != ksis) {
                    ksis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return context;
    }

    /**
     * 装载证书
     *
     * @param keyStore 证书实体
     * @return
     */
    public static SSLContext load(KeyStore keyStore) {
        return load(keyStore, null);
    }
}
