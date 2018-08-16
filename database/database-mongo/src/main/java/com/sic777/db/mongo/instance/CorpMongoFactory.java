package com.sic777.db.mongo.instance;


import com.sic777.db.mongo.config.MongoDataBase;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * <p>根据数据库名称获取mongo操作类</p>
 *
 * @author Zhengzhenxie
 * @version v1.0
 * @since 2017-12-26 14:56
 */
public class CorpMongoFactory {
    /**
     * 数据库名与mongo映射
     */
    private static Map<String, CorpMongo> dataBaseName2MongoMap = new ConcurrentHashMap<>();

    public static CorpMongo instance(String corpId) {
        if (null == corpId || corpId.trim().length() == 0) {
            throw new UnsupportedOperationException();
        }
        String dbName = String.format(MongoDataBase.CORP_DB_NAME, corpId);

        CorpMongo mongo = dataBaseName2MongoMap.get(dbName);
        if (null == mongo) {
            synchronized (dbName) {
                mongo = dataBaseName2MongoMap.get(dbName);
                if (mongo == null) {
                    mongo = new CorpMongo(dbName);
                    dataBaseName2MongoMap.put(dbName, mongo);
                }
            }
        }
        return mongo;
    }
}

