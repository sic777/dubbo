package com.sic777.common.utils.http;

import java.util.Map;

import com.sic777.common.utils.container.ContainerGetter;


/**
 * <p>Http响应对象
 *
 * @author sic777
 * @since 0.0.1
 */
public class HttpResponse {
    /**
     * HTTP状态码
     */
    private final int code;
    /**
     * HTTP响应头
     */
    private Map<String, String> headers = ContainerGetter.hashMap();
    /**
     * HTTP响应内容
     */
    private String content;

    private byte[] contentByte;

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

    public byte[] getContentByte() {
        return contentByte;
    }

    public void setContentByte(byte[] contentByte) {
        this.contentByte = contentByte;
    }

    @Override
    public String toString() {
        return "HttpResponse [code=" + code + ", headers=" + headers + ", content=" + content + "]";
    }
}
