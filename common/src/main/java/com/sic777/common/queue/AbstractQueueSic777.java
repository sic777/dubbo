package com.sic777.common.queue;

/**
 * <p>
 *
 * @author sic777
 * @version 0.0.1
 * @since 0.0.1
 */
public abstract class AbstractQueueSic777 {
    /**
     * spi 唯一标识
     */
    private final String spiKey;

    public AbstractQueueSic777(String spiKey) {
        this.spiKey = spiKey;
    }

    public String getSpiKey() {
        return spiKey;
    }

    @Override
    public String toString() {
        return "AbstractQueueSic777{" +
                "spiKey='" + spiKey + '\'' +
                '}';
    }
}
