package com.sic777.utils.http;

import java.util.Map;

import com.sic777.utils.container.ContainerGetter;

/**
 * <p>Http响应对象s</p>
 *
 * @author Zhengzhenxie
 * @version v1.0
 * @since 2018-08-16
 */

public class HttpResponse {
    /**
     * HTTP状态码
     */
    private int code;
    /**
     * HTTP响应头
     */
    private Map<String, String> headers = ContainerGetter.hashMap();
    /**
     * HTTP响应内容
     */
    private String content;

    public HttpResponse(int code) {
        this.code = code;
    }

    public int getResponseCode() {
        return this.code;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setHeader(String name, String value) {
        headers.put(name, value);
    }

    public String getHeader(String name) {
        return headers.get(name);
    }

    @Override
    public String toString() {
        return "HttpResponse [code=" + code + ", headers=" + headers + ", content=" + content + "]";
    }
}
