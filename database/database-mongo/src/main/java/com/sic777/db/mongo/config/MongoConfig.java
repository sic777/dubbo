package com.sic777.db.mongo.config;

import com.sic777.utils.StringUtil;
import com.sic777.utils.container.ContainerGetter;
import com.sic777.utils.container.tuple.Tuple;
import com.sic777.utils.container.tuple.TwoTuple;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by Zhengzhenxie on 2017/9/29.
 */
public class MongoConfig {

    private static int connectionsPerHost;
    private static int threadsAllowedToBlockForConnectionMultiplier;
    private static int connectTimeout;
    private static int maxWaitTime;
    private static boolean socketKeepAlive;
    private static int socketTimeout;
    private static String username;
    private static String password;
    private static final Logger logger = LoggerFactory.getLogger(MongoConfig.class);

    public static List<TwoTuple<String, Integer>> hosts = ContainerGetter.copyOnWriteArrayList();

    /**
     * 初始化mongo配置
     *
     * @param jsonObject
     */
    public static void init(final JSONObject jsonObject) {
        try {
            parseHost(jsonObject);
            connectionsPerHost = jsonObject.getIntValue(MongoConstant.CONNECTIONS_PER_HOST);
            threadsAllowedToBlockForConnectionMultiplier = jsonObject.getIntValue(MongoConstant.THREADS_ALLOWED_TO_BLOCK_FOR_CONNECTION_MULTIPLIER);
            connectTimeout = jsonObject.getIntValue(MongoConstant.CONNECT_TIMEOUT);
            maxWaitTime = jsonObject.getIntValue(MongoConstant.MAX_WAIT_TIME);
            socketKeepAlive = jsonObject.getBoolean(MongoConstant.SOCKET_KEEP_ALIVE);
            socketTimeout = jsonObject.getIntValue(MongoConstant.SOCKET_TIMEOUT);
            username = jsonObject.getString(MongoConstant.USER_NAME);
            password = jsonObject.getString(MongoConstant.PASSWORD);
        } catch (Exception e) {
            logger.error("sorry,the mongo config init failed,plz check it..", e);
            System.exit(-1);
        }
    }

    /**
     * parse host
     *
     * @param jsonObject
     */
    private static void parseHost(JSONObject jsonObject) {
        String host = jsonObject.getString(MongoConstant.HOST);
        Integer port = StringUtil.getInt(jsonObject.getString(MongoConstant.PORT));
        if (host != null && port != null) {
            hosts.add(Tuple.tuple(host, port));
        } else {
            String hostsString = jsonObject.getString(MongoConstant.SHARD_HOSTS);
            if (hostsString != null) {
                for (String host_port : hostsString.split(",")) {
                    hosts.add(Tuple.tuple(host_port.split(":")[0], StringUtil.getInt((host_port.split(":")[1]))));
                }
            }
        }
        if (hosts.isEmpty()) {
            throw new UnsupportedOperationException("sorry, the host config is empty,plz check it");
        }
    }

    public static int getConnectionsPerHost() {
        return connectionsPerHost;
    }

    public static int getThreadsAllowedToBlockForConnectionMultiplier() {
        return threadsAllowedToBlockForConnectionMultiplier;
    }

    public static int getConnectTimeout() {
        return connectTimeout;
    }

    public static int getMaxWaitTime() {
        return maxWaitTime;
    }

    public static boolean isSocketKeepAlive() {
        return socketKeepAlive;
    }

    public static int getSocketTimeout() {
        return socketTimeout;
    }

    public static String getUsername() {
        return username;
    }

    public static String getPassword() {
        return password;
    }

}
