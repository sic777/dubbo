package com.sic777.db.mongo.instance;

import com.sic777.db.mongo.Mongo;
import com.sic777.db.mongo.config.MongoDataBase;
import com.sic777.utils.proguard.NoProguard;

/**
 * Created by Zhengzhenxie on 2017/9/29.
 */
@NoProguard
public class Sic777Mongo extends Mongo {
    private static Sic777Mongo instance = new Sic777Mongo();

    public static Sic777Mongo instance() {
        return instance;
    }

    private Sic777Mongo() {
        super(MongoDataBase.SIC777);
    }
}
