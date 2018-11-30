package com.sic777.queue;

import com.sic777.common.queue.AbstractQueueSic777;

/**
 * <p>
 *
 * @author sic777
 * @version 0.0.1
 * @since 0.0.1
 */
public class Sic777Queue extends AbstractQueueSic777 {
    private final int id;
    private final String name;

    public Sic777Queue(String spiKey, int id, String name) {
        super(spiKey);
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Sic777Queue{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}

