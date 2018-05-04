package com.sic777.db.cassandra;

import com.datastax.driver.core.*;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.datastax.driver.core.querybuilder.Select;
import com.sic777.utils.container.ContainerGetter;
import com.sic777.utils.properties.PropertiesUtil;
import com.sic777.utils.string.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * <p>Cassandra操作类</p>
 *
 * @author Zhengzhenxie
 * @version v1.0
 * @since 2018-02-11 10:58
 */
public final class Cassandra {
    private final Logger logger = LoggerFactory.getLogger(Cassandra.class);

    /**
     * 仅初始化一次
     */
    private final AtomicBoolean init = new AtomicBoolean(false);
    /**
     * 缓存keyspace与session
     */
    private final ConcurrentHashMap<String, Session> keyspaceSessionMap = ContainerGetter.concurHashMap();
    /**
     * cassandra集群
     */
    private Cluster cluster;

    private Cassandra() {
    }

    private static Cassandra instance = new Cassandra();

    public static Cassandra instance() {
        return instance;
    }

    /**
     * Cassandra初始化
     *
     * @param propsPath
     * @throws UnsupportedOperationException
     * @throws IOException
     */
    public void init(final String propsPath) throws UnsupportedOperationException, IOException {
        this.init(PropertiesUtil.load(propsPath));
    }

    /**
     * Cassandra初始化
     *
     * @param props
     * @throws UnsupportedOperationException
     */
    public void init(final Properties props) throws UnsupportedOperationException {
        if (init.compareAndSet(false, true)) {
            logger.info("init cassandra ...");
            String hosts = props.getProperty(CassandraConstant.CASSANDRA_HOSTS);//host集群地址
            int port = Integer.parseInt(props.getProperty(CassandraConstant.CASSANDRA_PORT));//cassandra端口
            String userName = props.getProperty(CassandraConstant.CASSANDRA_USER_NAME);//cassandra用户名
            String password = props.getProperty(CassandraConstant.CASSANDRA_PD);//cassandra密码
            int fetchSize = Integer.parseInt(props.getProperty(CassandraConstant.CASSANDRA_FETCH_SIZE));//最大返回数据量
            int maxReqPerConn = Integer.parseInt(props.getProperty(CassandraConstant.CASSANDRA_MAX_REQUEST_PER_CONNECTION));//每个连接最大请求数
            int minConnPerHost = Integer.parseInt(props.getProperty(CassandraConstant.CASSANDRA_MIN_CONNECTION_PER_HOST));//每台机器最少连接数
            int maxConnPerHost = Integer.parseInt(props.getProperty(CassandraConstant.CASSANDRA_MAX_CONNECTION_PER_HOST));//每台机器最大连接数
            String keySpaces = props.getProperty(CassandraConstant.CASSANDRA_KEYSPACES);

            PoolingOptions poolingOptions = new PoolingOptions();
            // 每个连接的最大请求数
            poolingOptions.setMaxRequestsPerConnection(HostDistance.LOCAL, maxReqPerConn);
            // 表示集群里的机器至少有MIN个连接 最多有MAX个连接
            poolingOptions
                    .setCoreConnectionsPerHost(HostDistance.LOCAL, minConnPerHost)
                    .setMaxConnectionsPerHost(HostDistance.LOCAL, maxConnPerHost)
                    .setCoreConnectionsPerHost(HostDistance.REMOTE, minConnPerHost)
                    .setMaxConnectionsPerHost(HostDistance.REMOTE, maxConnPerHost);
            if (hosts != null && !hosts.trim().isEmpty()) {//初始化集群
                Cluster.Builder b = addContactPoint(Cluster.builder(), hosts.split(",\\s*"))
                        .withPort(port)
                        .withCredentials(userName, password)
                        .withPoolingOptions(poolingOptions);
                if (0 != fetchSize) {
                    b.withQueryOptions(new QueryOptions().setFetchSize(fetchSize));
                }
                cluster = b.build();
                if (keySpaces != null && !keySpaces.trim().isEmpty()) {//初始化keySpaces
                    String[] ks = keySpaces.split(",\\s*");
                    for (String k : ks) {
                        keyspaceSessionMap.putIfAbsent(k, cluster.connect(k));
                        logger.info("init cassandra keyspace : " + k);
                    }
                } else {
                    throw new UnsupportedOperationException("Please define Cassandra keyspace for property: cassandra.keySpaces");
                }
            } else {
                throw new UnsupportedOperationException("Please define Cassandra contact points for property: cassandra.hosts");
            }
        }
    }

    /**
     * 服务器列表
     *
     * @param builder
     * @param hosts
     */
    private Cluster.Builder addContactPoint(Cluster.Builder builder, String[] hosts) {
        for (String host : hosts) {
            builder.addContactPoint(host);
        }
        return builder;
    }

    /**
     * 根据keyspace获取session
     *
     * @param keyspace
     * @return
     * @throws UnsupportedOperationException
     */
    private Session getSession(String keyspace) throws UnsupportedOperationException {
        Session s = keyspaceSessionMap.get(keyspace);
        if (null == s) {
            throw new UnsupportedOperationException("keyspace [" + keyspace + "]not found");
        }
        return s;
    }

    /**
     * 执行
     *
     * @param session
     * @param statement
     * @return
     * @throws UnsupportedOperationException
     */
    public ResultSet execute(Session session, Statement statement) {
        return session.execute(statement);
    }

    /**
     * 执行
     *
     * @param session
     * @param cql
     * @return
     * @throws UnsupportedOperationException
     */
    public ResultSet execute(Session session, String cql) {
        return session.execute(cql);
    }

    /********************************** CRUD ********************************/
    /********************************** CRUD ********************************/
    /********************************** CRUD ********************************/

    /**
     * 插入数据
     *
     * @param keyspace
     * @param table
     * @param names
     * @param values
     * @return
     * @throws UnsupportedOperationException
     */
    public final ResultSet insert(String keyspace, String table, String[] names, Object[] values) throws UnsupportedOperationException {
        return execute(getSession(keyspace), QueryBuilder.insertInto(keyspace, table).values(names, values));
    }

    /**
     * 插入数据
     *
     * @param keyspace
     * @param table
     * @param names
     * @param values
     * @return
     * @throws UnsupportedOperationException
     */
    public final ResultSet insert(String keyspace, String table, List<String> names, List<Object> values) throws UnsupportedOperationException {
        return execute(getSession(keyspace), QueryBuilder.insertInto(keyspace, table).values(names, values));
    }

    /**
     * 插入数据
     *
     * @param keyspace
     * @param table
     * @param params
     * @return
     * @throws UnsupportedOperationException
     */
    public final ResultSet insert(String keyspace, String table, Map<String, Object> params) throws UnsupportedOperationException {
        if (null == params) {
            return null;
        }
        List<String> names = new ArrayList<>();
        List<Object> values = new ArrayList<>();
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            names.add(entry.getKey());
            values.add(entry.getValue());
        }
        return execute(getSession(keyspace), QueryBuilder.insertInto(keyspace, table).values(names, values));
    }


    /**
     * 查询
     *
     * @param query
     * @return
     * @throws InterruptedException
     * @throws ExecutionException
     * @throws TimeoutException
     * @throws UnsupportedOperationException
     */
    public final ResultSet select(CassandraQuery query) throws InterruptedException, ExecutionException, TimeoutException, UnsupportedOperationException {
        Select.Builder sb = null != query.getColumns() ? QueryBuilder
                .select(query.getColumns()) : QueryBuilder.select();

        Select select = sb.from(query.getKeyspace(), query.getTable());

        Select.Where where = select.where(QueryBuilder.eq(query.getKey(), query.getValue()));

        if (!StringUtil.isEmpty(query.getTimeKey())) {
            if (null != query.getBeginValue()) {
                where.and(QueryBuilder.gt(query.getTimeKey(), query.getBeginValue()));
            }
            if (null != query.getEndValue()) {
                where.and(QueryBuilder.lt(query.getTimeKey(), query.getEndValue()));
            }
        }
        if (query.getSortKey() != null) {
            select.orderBy(SortType.Asc == query.getSortType() ? QueryBuilder.asc(query.getSortKey())
                    : QueryBuilder.desc(query.getSortKey()));
        }
        if (0 != query.getFetchSize()) {
            select.setFetchSize(query.getFetchSize());
        }
        if (null != query.getPagingState()) {
            select.setPagingState(PagingState.fromString(query.getPagingState()));
        }
        ResultSet rs = execute(getSession(query.getKeyspace()), select);//rs.getExecutionInfo().getPagingState();//分页状态
        return rs;
    }

    /**
     * 分页查询
     *
     * @param page
     * @param query
     * @param process
     * @return
     * @throws InterruptedException
     * @throws ExecutionException
     * @throws TimeoutException
     * @throws UnsupportedOperationException
     */
    public List selectByPaging(int page, CassandraQuery query, IProcessRow<?> process) throws InterruptedException, ExecutionException, TimeoutException, UnsupportedOperationException {
        ResultSet rs = select(query);
        List<Object> results = new ArrayList<>();
        Set<String> set = new HashSet<>();
        PagingState pageState;
        int preFetchSize = query.getFetchSize() >> 1;
        for (Row row : rs) {
            //记录分页状态
            pageState = rs.getExecutionInfo().getPagingState();
            if (null != pageState) {
                set.add(pageState.toString());
            } else {
                set.add("");
            }
            //如果分页状态还没到达请求的分页数，丢弃掉前面的数据
            if (set.size() < page) {
                continue;
            }
            //如果分页状态已经超过请求的分页数，丢弃后面的数据
            if (set.size() > page) {
                break;
            }
            //预加载数据
            if (rs.getAvailableWithoutFetching() == preFetchSize && !rs.isFullyFetched()) {
                rs.fetchMoreResults();
            }
            //处理返回的业务数据
            if (null != process) {
                results.add(process.process(row));
            } else {
                results.add(row);
            }
        }
        return results;
    }

    /**
     * 分页查询
     *
     * @param page
     * @param query
     * @return
     * @throws InterruptedException
     * @throws ExecutionException
     * @throws TimeoutException
     * @throws UnsupportedOperationException
     */
    @SuppressWarnings("unchecked")
    public final List<Row> selectByPaging(int page, CassandraQuery query) throws InterruptedException, ExecutionException, TimeoutException, UnsupportedOperationException {
        return selectByPaging(page, query, null);
    }


    /**
     * 目前测试 主键支持Integer和String类型
     *
     * @param query
     * @return
     */
    public final long count(CassandraQuery query) {
        StringBuffer sb = new StringBuffer();
        Object value = query.getValue();
        sb.append("select count(1) from ")
                .append(query.getKeyspace())
                .append(".")
                .append(query.getTable())
                .append(" where ")
                .append(query.getKey())
                .append(" = ")
                .append(value instanceof String ? "'" + StringUtil.getString(value) + "'" : value);
        if (!StringUtil.isEmpty(query.getTimeKey())) {
            if (null != query.getBeginValue()) {
                sb.append(" and ")
                        .append(query.getTimeKey())
                        .append(" > ")
                        .append(query.getBeginValue().getTime());
            }
            if (null != query.getEndValue()) {
                sb.append(" and ")
                        .append(query.getTimeKey())
                        .append(" < ")
                        .append(query.getEndValue().getTime());
            }
        }
        return execute(getSession(query.getKeyspace()), sb.toString()).one().getLong(0);
    }

    /**
     * 判断cassandra返回结果是否包含某字段
     *
     * @param row
     * @param column
     * @return
     */
    public boolean existsColumn(Row row, String column) {
        return row.getColumnDefinitions().contains(column);
    }

}
