package com.sic777.db.mongo.data;

import org.bson.conversions.Bson;

import java.util.Set;

/**
 * <p></p>
 *
 * @author Zhengzhenxie
 * @version v1.0
 * @since 2018-08-15
 */
public class MongoSearchQuery {
    private final int offset;
    private final int limit;
    private final Set<String> filters;
    private Bson query;
    private final Bson sort;

    public MongoSearchQuery(int offset, int limit, Set<String> filters, Bson query, Bson sort) {
        super();
        this.offset = offset;
        this.limit = limit;
        this.filters = filters;
        this.query = query;
        this.sort = sort;
    }

    public int getOffset() {
        return offset;
    }

    public int getLimit() {
        return limit;
    }

    public Set<String> getFilters() {
        return filters;
    }

    public Bson getQuery() {
        return query;
    }

    public void setQuery(Bson query) {
        this.query = query;
    }

    public Bson getSort() {
        return sort;
    }

}
