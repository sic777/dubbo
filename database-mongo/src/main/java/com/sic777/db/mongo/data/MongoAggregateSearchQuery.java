package com.sic777.db.mongo.data;

import com.sic777.db.mongo.type.MongoLogicalType;
import com.sic777.utils.proguard.NoProguard;
import org.bson.conversions.Bson;

import java.util.Map;
import java.util.Set;

/**
 * <p></p>
 *
 * @author Zhengzhenxie
 * @version v1.0
 * @since 2018-08-15
 */
@NoProguard
public class MongoAggregateSearchQuery {

    private final int offset;
    private final int limit;
    private final MongoLogicalType logical;
    private final Map<String, Set<String>> filterMap;
    private Map<String, Bson> queryMap;
    private final Map<String, Bson> sortMap;

    public MongoAggregateSearchQuery(int offset, int limit,
                                     MongoLogicalType logical, Map<String, Set<String>> filterMap,
                                     Map<String, Bson> queryMap, Map<String, Bson> sortMap) {
        super();
        this.offset = offset;
        this.limit = limit;
        this.logical = logical;
        this.filterMap = filterMap;
        this.queryMap = queryMap;
        this.sortMap = sortMap;
    }

    public Map<String, Bson> getQueryMap() {
        return queryMap;
    }

    public void setQueryMap(Map<String, Bson> queryMap) {
        this.queryMap = queryMap;
    }

    public int getOffset() {
        return offset;
    }

    public int getLimit() {
        return limit;
    }

    public MongoLogicalType getLogical() {
        return logical;
    }

    public Map<String, Set<String>> getFilterMap() {
        return filterMap;
    }

    public Map<String, Bson> getSortMap() {
        return sortMap;
    }

}
