package com.sic777.db.mongo.config;

import com.sic777.utils.ConfigureManager;
import com.sic777.utils.StringUtil;
import com.sic777.utils.container.ContainerGetter;
import com.sic777.utils.container.tuple.Tuple;
import com.sic777.utils.container.tuple.TwoTuple;

import java.util.List;

/**
 * Created by Zhengzhenxie on 2017/9/29.
 */
public class MongoConfig {

    public static int CONNECTIONS_PER_HOST = ConfigureManager.instance().getInt(MongoConstant.CONNECTIONS_PER_HOST);

    public static int THREADS_ALLOWED_TO_BLOCK_FOR_CONNECTION_MULTIPLIER = ConfigureManager.instance().getInt(MongoConstant.THREADS_ALLOWED_TO_BLOCK_FOR_CONNECTION_MULTIPLIER);

    public static int CONNECT_TIMEOUT = ConfigureManager.instance().getInt(MongoConstant.CONNECT_TIMEOUT);

    public static int MAX_WAIT_TIME = ConfigureManager.instance().getInt(MongoConstant.MAX_WAIT_TIME);

    public static boolean SOCKET_KEEP_ALIVE = ConfigureManager.instance().getBool(MongoConstant.SOCKET_KEEP_ALIVE);

    public static int SOCKET_TIMEOUT = ConfigureManager.instance().getInt(MongoConstant.SOCKET_TIMEOUT);

    public static String USER_NAME = ConfigureManager.instance().getString(MongoConstant.USER_NAME);

    public static String PASSWORD = ConfigureManager.instance().getString(MongoConstant.PASSWORD);

    private static String HOST = ConfigureManager.instance().getString(MongoConstant.HOST);
    private static Integer PORT = ConfigureManager.instance().getInt(MongoConstant.PORT);

    public static List<TwoTuple<String, Integer>> hosts = ContainerGetter.copyOnWriteArrayList();

    static {
        if (HOST != null && PORT != null) {
            hosts.add(Tuple.tuple(HOST, PORT));
        } else {
            String hostsString = ConfigureManager.instance().getString(MongoConstant.SHARD_HOSTS);
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
}
