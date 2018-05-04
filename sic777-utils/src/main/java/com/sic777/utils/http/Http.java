package com.sic777.utils.http;

import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.Map;

/**
 * <p>Http客户端</p>
 *
 * @author Zhengzhenxie<br>
 *         <br>2017-11-17 10:14
 * @version v1.0
 * @since 1.7
 */
public class Http {
    private final static int DEFAULT_SOCKET_TIMEOUT = 10000;
    private final static int DEFAULT_CONNECT_TIMEOUT = 10000;

    /**
     * @param url
     * @param body
     * @param headers
     * @param connectTimeout
     * @param socketTimeout
     * @return
     * @throws IOException
     */
    public static String post(String url, String body, Map<String, Object> headers, int connectTimeout, int socketTimeout) throws IOException {
        HttpPost post = new HttpPost(url);
        RequestConfig.Builder builder = RequestConfig.custom();
        if (0 != connectTimeout) {
            builder.setConnectTimeout(connectTimeout);
        } else {
            builder.setConnectTimeout(DEFAULT_CONNECT_TIMEOUT);
        }
        if (0 != socketTimeout) {
            builder.setSocketTimeout(socketTimeout);
        } else {
            builder.setSocketTimeout(DEFAULT_SOCKET_TIMEOUT);
        }
        RequestConfig requestConfig = builder.build();
        post.setConfig(requestConfig);
        StringEntity reqEntity = new StringEntity(body, "UTF-8");
        reqEntity.setContentEncoding("UTF-8");
        reqEntity.setContentType("application/json");
        reqEntity.setChunked(true);
        if (headers != null && !headers.isEmpty()) {
            for (Map.Entry<String, Object> entry : headers.entrySet()) {
                if (entry.getValue() != null) {
                    post.setHeader(entry.getKey(), entry.getValue().toString());
                }
            }
        }
        post.setEntity(reqEntity);
        CloseableHttpResponse res = PoolHttpConnectionManager.instance().getHttpClient().execute(post);
        HttpEntity respEntity = res.getEntity();
        String respCharset = "UTF-8";
        if (null != ContentType.getOrDefault(respEntity).getCharset()) {
            respCharset = ContentType.getOrDefault(respEntity).getCharset().toString();
        }
        String inResponse = EntityUtils.toString(respEntity, respCharset);
        res.close();
        return inResponse;
    }

    /**
     * @param url
     * @param body
     * @param headers
     * @param socketTimeout
     * @return
     * @throws IOException
     */
    public static String post(String url, String body, Map<String, Object> headers, int socketTimeout) throws IOException {
        return post(url, body, headers, DEFAULT_CONNECT_TIMEOUT, socketTimeout);
    }

    /**
     * @param url
     * @param body
     * @param headers
     * @return
     * @throws IOException
     */
    public static String post(String url, String body, Map<String, Object> headers) throws IOException {
        return post(url, body, headers, DEFAULT_SOCKET_TIMEOUT);
    }
}
