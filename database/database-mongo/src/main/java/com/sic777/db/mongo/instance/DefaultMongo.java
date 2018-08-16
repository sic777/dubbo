package com.sic777.db.mongo.instance;


import com.sic777.db.mongo.Mongo;
import com.sic777.db.mongo.config.MongoDataBase;

/**
 * Created by Zhengzhenxie on 2017/9/29.
 */
public class DefaultMongo extends Mongo {
    private static DefaultMongo instance = new DefaultMongo();

    public static DefaultMongo instance() {
        return instance;
    }

    private DefaultMongo() {
        super(MongoDataBase.DEFAULT);

    }

}
