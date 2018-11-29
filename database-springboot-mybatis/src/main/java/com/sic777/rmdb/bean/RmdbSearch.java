package com.sic777.rmdb.bean;

import java.util.List;
import java.util.Set;

/**
 * <p>
 *
 * @author sic777
 * @version 0.0.1
 * @since 0.0.1
 */
public class RmdbSearch {
    private final int pageNum;
    private final int pageSize;
    private final Set<String> filters;
    private final List<Query> query;
    private final List<Order> orders;

    public RmdbSearch(int pageNum, int pageSize, Set<String> filters, List<Query> query, List<Order> orders) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.filters = filters;
        this.query = query;
        this.orders = orders;
    }

    public int getPageNum() {
        return pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public Set<String> getFilters() {
        return filters;
    }

    public List<Query> getQuery() {
        return query;
    }

    public List<Order> getOrders() {
        return orders;
    }

    @Override
    public String toString() {
        return "RmdbSearch{" +
                "pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                ", filters=" + filters +
                ", query=" + query +
                ", orders=" + orders +
                '}';
    }
}
