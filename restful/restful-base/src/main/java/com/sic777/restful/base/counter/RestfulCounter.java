package com.sic777.restful.base.counter;

/**
 * <p>
 *
 * @author sic777
 * @version 0.0.1
 * @since 0.0.1
 */
public class RestfulCounter {
    private final String uri;
    private final String method;
    private final long count;

    public RestfulCounter(String uri, String method, long count) {
        this.uri = uri;
        this.method = method;
        this.count = count;
    }

    public String getUri() {
        return uri;
    }

    public String getMethod() {
        return method;
    }

    public long getCount() {
        return count;
    }

    @Override
    public String toString() {
        return "RestfulCounter{" +
                "uri='" + uri + '\'' +
                ", method='" + method + '\'' +
                ", count=" + count +
                '}';
    }
}
