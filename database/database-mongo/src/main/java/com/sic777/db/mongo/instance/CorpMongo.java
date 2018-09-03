package com.sic777.db.mongo.instance;

import com.sic777.db.mongo.Mongo;
import com.sic777.utils.proguard.NoProguard;

/**
 * <p>Mongo企业桶</p>
 *
 * @author Zhengzhenxie
 * @version v1.0
 * @since 2017-12-26 14:53
 */
@NoProguard
public class CorpMongo extends Mongo {
    CorpMongo(String dbName) {
        super(dbName);
    }
}
