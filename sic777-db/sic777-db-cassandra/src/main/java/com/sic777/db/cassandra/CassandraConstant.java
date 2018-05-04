package com.sic777.db.cassandra;

/**
 * <p>Cassandra属性常量</p>
 *
 * @author Zhengzhenxie
 * @version v1.0
 * @since 2018-02-11 10:58
 */
interface CassandraConstant {
    /**
     * cassandra ip
     */
    String CASSANDRA_HOSTS = "cassandra.hosts";

    /**
     * cassandra port
     */
    String CASSANDRA_PORT = "cassandra.port";

    /**
     * init cassandra keyspace
     */
    String CASSANDRA_KEYSPACES = "cassandra.keySpaces";

    /**
     * cassandra user name
     */
    String CASSANDRA_USER_NAME = "cassandra.user";

    /**
     * cassandra password
     */
    String CASSANDRA_PD = "cassandra.password";

    /**
     * cassandra fetch size
     */
    String CASSANDRA_FETCH_SIZE = "cassandra.fetchSize";

    /**
     * cassandra max request per connection
     */
    String CASSANDRA_MAX_REQUEST_PER_CONNECTION = "cassandra.maxRequestPerConnection";

    /**
     * cassandra min connection per host
     */
    String CASSANDRA_MIN_CONNECTION_PER_HOST = "cassandra.minConnectionPerHost";

    /**
     * cassandra max connection per host
     */
    String CASSANDRA_MAX_CONNECTION_PER_HOST = "cassandra.maxConnectionPerHost";

}
