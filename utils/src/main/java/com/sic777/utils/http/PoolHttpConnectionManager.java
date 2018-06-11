package com.sic777.utils.http;

import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

/**
 * <p>Http线程池</p>
 *
 * @author Zhengzhenxie<br>
 *         <br>2017-11-17 10:14
 * @version v1.0
 * @since 1.7
 */
public class PoolHttpConnectionManager {
    private static PoolHttpConnectionManager singleton;
    private final CloseableHttpClient httpClient;
    private final PoolingHttpClientConnectionManager cm;

    public static PoolHttpConnectionManager instance() {
        if (null == singleton) {
            synchronized (PoolHttpConnectionManager.class) {
                if (singleton == null)
                    singleton = new PoolHttpConnectionManager();
            }
        }
        return singleton;
    }

    public void init(int max, int per) {
        cm.setMaxTotal(max);
        cm.setDefaultMaxPerRoute(per);
    }

    private PoolHttpConnectionManager() {
        RegistryBuilder<ConnectionSocketFactory> builder = RegistryBuilder.create();
        builder.register("http", PlainConnectionSocketFactory.INSTANCE);
        cm = new PoolingHttpClientConnectionManager();
        httpClient = HttpClients.custom().setConnectionManager(cm).build();
    }

    public CloseableHttpClient getHttpClient() {
        return httpClient;
    }
}
