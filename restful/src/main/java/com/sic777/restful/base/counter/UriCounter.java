package com.sic777.restful.base.counter;

/**
 * <p>
 *
 * @author sic777
 * @version 0.0.1
 * @since 0.0.1
 */
public class UriCounter {
    private final String uri;
    private final long count;

    public UriCounter(String uri, long count) {
        this.uri = uri;
        this.count = count;
    }

    public String getUri() {
        return uri;
    }

    public long getCount() {
        return count;
    }

    @Override
    public String toString() {
        return "UriCounter{" +
                "uri='" + uri + '\'' +
                ", count=" + count +
                '}';
    }
}
