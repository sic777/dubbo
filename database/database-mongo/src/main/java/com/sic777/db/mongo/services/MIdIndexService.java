package com.sic777.db.mongo.services;

import com.sic777.db.mongo.config.MongoCollectionName;
import com.sic777.db.mongo.instance.Sic777Mongo;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class MIdIndexService {

    private static final MIdIndexService singleton = new MIdIndexService();

    public static final MIdIndexService instance() {
        return singleton;
    }

    private MIdIndexService() {
    }

    /**
     * 初始化Id索引表
     */
    public void init() {
        MongoDatabase mongoDatabase = Sic777Mongo.instance().getMongoDatabase();
        com.mongodb.client.MongoCollection<Document> collection = mongoDatabase.getCollection(MongoCollectionName.ID_INDEX);
        Document document = new Document().append("_id", 0);
        long count = collection.count(document);
        if (count <= 0) {
            collection.insertOne(document);
        }
    }

    /**
     * 递增ID索引
     *
     * @param count
     * @return
     */
    public Integer incr(int count) {
        MongoDatabase mongoDatabase = Sic777Mongo.instance().getMongoDatabase();
        com.mongodb.client.MongoCollection<Document> collection = mongoDatabase.getCollection(MongoCollectionName.ID_INDEX);
        Document filter = new Document().append("_id", 0);
        Document update = new Document().append("$inc", new Document().append("id_index", count));
        Document result = collection.findOneAndUpdate(filter, update);
        return result.getInteger("id_index") + count;
    }
}
