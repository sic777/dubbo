package com.sic777.db.cassandra;


import java.util.Date;

/**
 * <p>Cassandra查询实体</p>
 *
 * @author Zhengzhenxie<br>
 *         <br>2017-12-19 15:58
 * @version v1.0
 * @since 2018-02-11 11:02
 */
public final class CassandraQuery {
    /**
     * keyspace
     */
    private String keyspace;
    /**
     * 表名
     */
    private String table;
    /**
     * 查询主键
     */
    private String key;
    /**
     * 查询主键的值
     */
    private Object value;
    /**
     * 查询的列
     */
    private String[] columns;
    /**
     * 时间键
     */
    private String timeKey;
    /**
     * 开始时间值
     */
    private Date beginValue;
    /**
     * 结束时间值
     */
    private Date endValue;
    /**
     * 排序
     */
    private SortType sortType;
    /**
     * 排序的key
     */
    private String sortKey;
    /**
     * 限制返回数据量
     */
    private int fetchSize;
    /**
     * 分页状态
     */
    private String pagingState;

    public CassandraQuery() {
    }

    public CassandraQuery(String keyspace, String table, String key, Object value, String[] columns, String timeKey, Date beginValue, Date endValue, SortType sortType, String sortKey, int fetchSize, String pagingState) {
        this.keyspace = keyspace;
        this.table = table;
        this.key = key;
        this.value = value;
        this.columns = columns;
        this.timeKey = timeKey;
        this.beginValue = beginValue;
        this.endValue = endValue;
        this.sortType = sortType;
        this.sortKey = sortKey;
        this.fetchSize = fetchSize;
        this.pagingState = pagingState;
    }

    public String getKeyspace() {
        return keyspace;
    }

    public String getTable() {
        return table;
    }

    public String getKey() {
        return key;
    }

    public Object getValue() {
        return value;
    }

    public String[] getColumns() {
        return columns;
    }

    public String getTimeKey() {
        return timeKey;
    }

    public Date getBeginValue() {
        return beginValue;
    }

    public Date getEndValue() {
        return endValue;
    }

    public SortType getSortType() {
        return sortType;
    }

    public String getSortKey() {
        return sortKey;
    }

    public int getFetchSize() {
        return fetchSize;
    }

    public String getPagingState() {
        return pagingState;
    }

    public CassandraQuery setColumns(String[] columns) {
        this.columns = columns;
        return this;
    }

    public CassandraQuery setTable(String table) {
        this.table = table;
        return this;
    }

    public CassandraQuery setKey(String key) {
        this.key = key;
        return this;
    }

    public CassandraQuery setValue(Object value) {
        this.value = value;
        return this;
    }

    public CassandraQuery setTimeKey(String timeKey) {
        this.timeKey = timeKey;
        return this;
    }

    public CassandraQuery setBeginValue(Date beginValue) {
        this.beginValue = beginValue;
        return this;
    }

    public CassandraQuery setEndValue(Date endValue) {
        this.endValue = endValue;
        return this;
    }

    public CassandraQuery setSortType(SortType sortType) {
        this.sortType = sortType;
        return this;
    }

    public CassandraQuery setSortKey(String sortKey) {
        this.sortKey = sortKey;
        return this;
    }

    public CassandraQuery setFetchSize(int fetchSize) {
        this.fetchSize = fetchSize;
        return this;
    }

    public CassandraQuery setPagingState(String pagingState) {
        this.pagingState = pagingState;
        return this;
    }

    public CassandraQuery setKeyspace(String keyspace) {
        this.keyspace = keyspace;
        return this;
    }
}
