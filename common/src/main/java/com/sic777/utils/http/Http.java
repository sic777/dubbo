package com.sic777.utils.http;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.StatusLine;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.*;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Map;


/**
 * <p>Http客户端
 *
 * @author sic777
 * @since 0.0.1
 */
public class Http {
    private Http() {
    }

    private final static int DEFAULT_SOCKET_TIMEOUT = 10000;
    private final static int DEFAULT_CONNECT_TIMEOUT = 10000;

    private final static Logger logger = LoggerFactory.getLogger(Http.class);


    /**
     * @param url            目标地址
     * @param params         url参数
     * @param headers        请求头
     * @param connectTimeout 链接超时
     * @param socketTimeout  socket超时
     * @return
     * @throws IOException
     */
    public static HttpResponse delete(String url, Map<String, Object> params, Map<String, String> headers, int connectTimeout, int socketTimeout) throws IOException {
        url = url(params, url);
        HttpDelete delete = new HttpDelete(url);
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(0 != connectTimeout ? connectTimeout : DEFAULT_CONNECT_TIMEOUT)
                .setSocketTimeout(0 != socketTimeout ? socketTimeout : DEFAULT_SOCKET_TIMEOUT)
                .build();
        delete.setConfig(requestConfig);
        if (headers != null && !headers.isEmpty()) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                if (entry.getValue() != null) {
                    delete.setHeader(entry.getKey(), entry.getValue());
                }
            }
        }
        CloseableHttpResponse res = PoolHttpConnectionManager.instance().getHttpClient().execute(delete);
        return ret(res);
    }

    /**
     * @param url            目标地址
     * @param headerMap      请求头参数
     * @param connectTimeout 链接超时
     * @param socketTimeout  socket超时
     * @return
     * @throws IOException
     */
    public static HttpResponse delete(String url, Map<String, String> headerMap, int connectTimeout, int socketTimeout) throws IOException {
        return delete(url, null, headerMap, connectTimeout, socketTimeout);
    }

    /**
     * @param url       目标地址
     * @param headerMap 请求头参数
     * @return
     * @throws IOException
     */
    public static HttpResponse delete(String url, Map<String, String> headerMap) throws IOException {
        return delete(url, null, headerMap, DEFAULT_CONNECT_TIMEOUT, DEFAULT_SOCKET_TIMEOUT);
    }

    /**
     * @param url 目标地址
     * @return
     * @throws IOException
     */
    public static HttpResponse delete(String url) throws IOException {
        return delete(url, null, null, DEFAULT_CONNECT_TIMEOUT, DEFAULT_SOCKET_TIMEOUT);
    }

    /**
     * @param url            目标地址
     * @param body           请求体
     * @param headers        请求头
     * @param connectTimeout 链接超时
     * @param socketTimeout  socket超时
     * @return
     * @throws IOException
     */
    public static HttpResponse put(String url, String body, Map<String, String> headers, int connectTimeout, int socketTimeout) throws IOException {
        HttpPut put = new HttpPut(url);
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(0 != connectTimeout ? connectTimeout : DEFAULT_CONNECT_TIMEOUT)
                .setSocketTimeout(0 != socketTimeout ? socketTimeout : DEFAULT_SOCKET_TIMEOUT)
                .build();
        put.setConfig(requestConfig);
        StringEntity reqEntity = new StringEntity(body, "UTF-8");
        reqEntity.setContentEncoding("UTF-8");
        reqEntity.setContentType("application/json");
        reqEntity.setChunked(true);
        if (headers != null && !headers.isEmpty()) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                if (entry.getValue() != null) {
                    put.setHeader(entry.getKey(), entry.getValue());
                }
            }
        }
        put.setEntity(reqEntity);
        CloseableHttpResponse res = PoolHttpConnectionManager.instance().getHttpClient().execute(put);
        return ret(res);
    }

    /**
     * @param url     目标地址
     * @param body    请求体
     * @param headers 请求头
     * @return
     * @throws IOException
     */
    public static HttpResponse put(String url, String body, Map<String, String> headers) throws IOException {
        return put(url, body, headers, DEFAULT_CONNECT_TIMEOUT, DEFAULT_SOCKET_TIMEOUT);
    }

    /**
     * @param url  目标地址
     * @param body 请求体
     * @return
     * @throws IOException
     */
    public static HttpResponse put(String url, String body) throws IOException {
        return put(url, body, null, DEFAULT_CONNECT_TIMEOUT, DEFAULT_SOCKET_TIMEOUT);
    }

    /**
     * @param url 目标地址
     * @return
     * @throws IOException
     */
    public static HttpResponse put(String url) throws IOException {
        return put(url, null, null, DEFAULT_CONNECT_TIMEOUT, DEFAULT_SOCKET_TIMEOUT);
    }

    /**
     * @param url            目标地址
     * @param body           请求体
     * @param headers        请求头
     * @param connectTimeout 链接超时
     * @param socketTimeout  socket超时
     * @return
     * @throws IOException
     */
    public static HttpResponse post(String url, String body, Map<String, String> headers, int connectTimeout, int socketTimeout) throws IOException {
        HttpPost post = new HttpPost(url);
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(0 != connectTimeout ? connectTimeout : DEFAULT_CONNECT_TIMEOUT)
                .setSocketTimeout(0 != socketTimeout ? socketTimeout : DEFAULT_SOCKET_TIMEOUT)
                .build();
        post.setConfig(requestConfig);
        StringEntity reqEntity = new StringEntity(body, "UTF-8");
        reqEntity.setContentEncoding("UTF-8");
        reqEntity.setContentType("application/json");
        reqEntity.setChunked(true);
        if (headers != null && !headers.isEmpty()) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                if (entry.getValue() != null) {
                    post.setHeader(entry.getKey(), entry.getValue());
                }
            }
        }
        post.setEntity(reqEntity);
        CloseableHttpResponse res = PoolHttpConnectionManager.instance().getHttpClient().execute(post);
        return ret(res);
    }

    /**
     * @param url     目标地址
     * @param body    请求体
     * @param headers 请求头
     * @return
     * @throws IOException
     */
    public static HttpResponse post(String url, String body, Map<String, String> headers) throws IOException {
        return post(url, body, headers, DEFAULT_CONNECT_TIMEOUT, DEFAULT_SOCKET_TIMEOUT);
    }

    /**
     * @param url  目标地址
     * @param body 请求体
     * @return
     * @throws IOException
     */
    public static HttpResponse post(String url, String body) throws IOException {
        return post(url, body, null, DEFAULT_CONNECT_TIMEOUT, DEFAULT_SOCKET_TIMEOUT);
    }

    /**
     * @param url 目标地址
     * @return
     * @throws IOException
     */
    public static HttpResponse post(String url) throws IOException {
        return post(url, null, null, DEFAULT_CONNECT_TIMEOUT, DEFAULT_SOCKET_TIMEOUT);
    }

    /**
     * @param url            目标地址
     * @param replaceParams  替换参数
     * @param params         url参数
     * @param headerMap      请求头参数
     * @param connectTimeout 链接超时
     * @param socketTimeout  socket超时
     * @return
     * @throws IOException
     */
    public static HttpResponse get(String url, Map<String, String> replaceParams, Map<String, Object> params, Map<String, String> headerMap, int connectTimeout, int socketTimeout) throws IOException {
        url = url(params, url);
        if (null != replaceParams) {
            for (String key : replaceParams.keySet()) {
                url = url.replace(key, replaceParams.get(key));
            }
        }
        HttpGet httpGet = new HttpGet(url);
        if (headerMap != null && !headerMap.isEmpty()) {
            for (Map.Entry<String, String> entry : headerMap.entrySet()) {
                httpGet.setHeader(entry.getKey(), entry.getValue());
            }
        }
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(0 != connectTimeout ? connectTimeout : DEFAULT_CONNECT_TIMEOUT)
                .setSocketTimeout(0 != socketTimeout ? socketTimeout : DEFAULT_SOCKET_TIMEOUT)
                .build();
        httpGet.setConfig(requestConfig);
        CloseableHttpResponse res = PoolHttpConnectionManager.instance().getHttpClient().execute(httpGet);
        return ret(res);
    }

    /**
     * @param url           目标地址
     * @param replaceParams 替换参数
     * @param params        url参数
     * @param headerMap     请求头参数
     * @return
     * @throws IOException
     */
    public static HttpResponse get(String url, Map<String, String> replaceParams, Map<String, Object> params, Map<String, String> headerMap) throws IOException {
        return get(url, replaceParams, params, headerMap, DEFAULT_CONNECT_TIMEOUT, DEFAULT_SOCKET_TIMEOUT);
    }

    /**
     * @param url            目标地址
     * @param params         url参数
     * @param headerMap      请求头参数
     * @param connectTimeout 链接超时
     * @param socketTimeout  socket超时
     * @return
     * @throws IOException
     */
    public static HttpResponse get(String url, Map<String, Object> params, Map<String, String> headerMap, int connectTimeout, int socketTimeout) throws IOException {
        return get(url, null, params, headerMap, connectTimeout, socketTimeout);
    }

    /**
     * @param url       目标地址
     * @param params    url参数
     * @param headerMap 请求头参数
     * @return
     * @throws IOException
     */
    public static HttpResponse get(String url, Map<String, Object> params, Map<String, String> headerMap) throws IOException {
        return get(url, null, params, headerMap, DEFAULT_CONNECT_TIMEOUT, DEFAULT_SOCKET_TIMEOUT);
    }

    /**
     * @param url       目标地址
     * @param headerMap 请求头参数
     * @return
     * @throws IOException
     */
    public static HttpResponse get(String url, Map<String, String> headerMap) throws IOException {
        return get(url, null, null, headerMap, DEFAULT_CONNECT_TIMEOUT, DEFAULT_SOCKET_TIMEOUT);
    }

    /**
     * @param url 目标地址
     * @return
     * @throws IOException
     */
    public static HttpResponse get(String url) throws IOException {
        return get(url, null, null, null, DEFAULT_CONNECT_TIMEOUT, DEFAULT_SOCKET_TIMEOUT);
    }


    /**
     * 处理url
     *
     * @param params
     * @param url
     * @return
     */
    private static String url(Map<String, Object> params, String url) {
        StringBuffer param = new StringBuffer();
        if (null != params) {
            int i = 0;
            int size = params.size() - 1;
            for (String key : params.keySet()) {
                if (i == size) {
                    param.append("?").append(key).append("=").append(params.get(key));
                } else {
                    param.append("?").append(key).append("=").append(params.get(key)).append("&");
                }
                i++;
            }
            url += param.toString();
        }
        return url;
    }


    /**
     * 响应
     *
     * @param res
     * @return
     * @throws IOException
     */
    private static HttpResponse ret(CloseableHttpResponse res) throws IOException {
        //status
        StatusLine statusLine = res.getStatusLine();
        //response bean
        HttpResponse httpResponse = new HttpResponse(statusLine.getStatusCode());
        //headers
        for (Header header : res.getAllHeaders()) {
            httpResponse.setHeader(header.getName(), header.getValue());
        }
        //entity
        HttpEntity respEntity = res.getEntity();
        String respCharset = "UTF-8";
        if (null != ContentType.getOrDefault(respEntity).getCharset()) {
            respCharset = ContentType.getOrDefault(respEntity).getCharset().toString();
        }
        String inResponse = EntityUtils.toString(respEntity, respCharset);
        logger.debug("Http response content:\r\n" + inResponse);
        res.close();
        httpResponse.setContent(inResponse);
        return httpResponse;
    }
}
