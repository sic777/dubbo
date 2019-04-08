package com.sic777.restful.base.spi.sign;

import java.util.Map;

/**
 * <p>
 *
 * @author sic777
 * @version 0.0.1
 * @since 0.0.1
 */
public class Sign {
    private final String requestId;
    private final String accessKey;
    private final String timestamp;
    private final String sign;
    private final Map<String, String[]> params;
    private final String uri;
    private final String body;

    public Sign(String requestId, String accessKey, String timestamp, String sign, Map<String, String[]> params, String uri, String body) {
        this.requestId = requestId;
        this.accessKey = accessKey;
        this.timestamp = timestamp;
        this.sign = sign;
        this.params = params;
        this.uri = uri;
        this.body = body;
    }

    public String getRequestId() {
        return requestId;
    }

    public String getAccessKey() {
        return accessKey;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getSign() {
        return sign;
    }

    public Map<String, String[]> getParams() {
        return params;
    }

    public String getUri() {
        return uri;
    }

    public String getBody() {
        return body;
    }
}
