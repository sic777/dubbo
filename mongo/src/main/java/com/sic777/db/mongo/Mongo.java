package com.sic777.db.mongo;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.FindOneAndUpdateOptions;
import com.mongodb.client.model.UpdateOptions;
import com.sic777.common.utils.container.ContainerGetter;
import com.sic777.common.utils.container.tuple.TwoTuple;
import com.sic777.db.mongo.config.MongoConfig;
import com.sic777.db.mongo.data.MongoQuery;
import com.sic777.db.mongo.data.MongoSearchQuery;
import com.sic777.logger.LoggerHelper;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.*;


/**
 * Created by Zhengzhenxie on 2017/9/12.
 */
public abstract class Mongo {
    private MongoDatabase mongoDatabase;
    private static MongoClient mongoClient;

    private static final int DEFAULT_OFFSET = 0;
    private static final int DEFAULT_LIMIT = 10;


    protected Mongo(String dbName) {
        mongoDatabase = mongoClient.getDatabase(dbName);
        LoggerHelper.info("init mongo database : " + dbName);
    }

    /**
     * 初始化mongo db
     */
    public static void init() {
        LoggerHelper.info("init mongo ...");
        MongoClientOptions.Builder potionBuilder = MongoClientOptions.builder();
        potionBuilder.connectionsPerHost(MongoConfig.CONNECTIONS_PER_HOST);
        potionBuilder.threadsAllowedToBlockForConnectionMultiplier(MongoConfig.THREADS_ALLOWED_TO_BLOCK_FOR_CONNECTION_MULTIPLIER);
        potionBuilder.connectTimeout(MongoConfig.CONNECT_TIMEOUT);
        potionBuilder.maxWaitTime(MongoConfig.MAX_WAIT_TIME);
        potionBuilder.socketKeepAlive(MongoConfig.SOCKET_KEEP_ALIVE);
        potionBuilder.socketTimeout(MongoConfig.SOCKET_TIMEOUT);

        List<ServerAddress> seeds = ContainerGetter.arrayList();
        List<MongoCredential> credentials = Arrays.asList(MongoCredential.createScramSha1Credential(MongoConfig.USER_NAME, "admin", MongoConfig.PASSWORD.toCharArray()));
        List<TwoTuple<String, Integer>> hosts = MongoConfig.hosts;
        for (TwoTuple<String, Integer> TwoTuple : hosts) {
            seeds.add(new ServerAddress(TwoTuple.first, TwoTuple.second));
        }
        mongoClient = new MongoClient(seeds, credentials, potionBuilder.build());
    }

    public MongoDatabase getMongoDatabase() {
        return mongoDatabase;
    }

    /**
     * 可视字段处理(利用JSONObject特性)
     *
     * @param fields
     * @param lists
     * @param clz
     * @param <T>
     * @return
     */
    public static <T> List<T> visibleFiled(Set<String> fields, List<T> lists, Class<T> clz) {
        List<T> rs = new ArrayList<>();
        for (T t : lists) {
            rs.add(visibleFiled(fields, t, clz));
        }
        return rs;
    }

    /**
     * 可视字段处理(利用JSONObject特性)
     *
     * @param fields
     * @param obj
     * @param clz
     * @param <T>
     * @return
     */
    public static <T> T visibleFiled(Set<String> fields, T obj, Class<T> clz) {
        JSONObject rs = new JSONObject();
        JSONObject js = (JSONObject) JSON.toJSON(obj);
        if (null == fields || fields.isEmpty()) {
            return js.toJavaObject(clz);
        }
        for (String field : fields) {
            rs.put(field, js.get(field));
        }
        return rs.toJavaObject(clz);
    }

    /**
     * 获取mongo查询条件
     *
     * @param body
     * @param fieldMap
     * @return
     * @throws IllegalArgumentException
     */
    public static MongoSearchQuery funcParseSearchQuery(JSONObject body, Map<String, TwoTuple<String, MongoQuery.FieldType>> fieldMap) throws IllegalArgumentException {
        int offset = body.containsKey("offset") ? body.getInteger("offset") : DEFAULT_OFFSET;
        int limit = body.containsKey("limit") ? body.getInteger("limit") : DEFAULT_LIMIT;
        Document orderBson = new Document();
        JSONObject order = body.getJSONObject("order");
        if (order != null) {
            for (Map.Entry<String, Object> entry : order.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue().toString();
                orderBson.append(key, MongoQuery.OrderType.fromString(value).nosql());// TODO key可能需要根据field转换
            }
        }
        Set<String> filters = new HashSet<>();
        JSONArray filterArray = body.getJSONArray("filter");
        if (null != filterArray) {
            for (Object obj : filterArray) {
                if (!fieldMap.containsKey(obj)) {
                    throw new IllegalArgumentException(String.format("field '%s' not exists.", obj));
                }
                filters.add((String) obj);
            }
        }
        List<Bson> querys = new ArrayList<>();
        JSONObject query = body.getJSONObject("query");
        if (query != null) {
            for (Map.Entry<String, Object> entry : query.entrySet()) {
                String key = entry.getKey();//字段名
                if (!fieldMap.containsKey(key)) {
                    throw new IllegalArgumentException(String.format("field '%s' not exists.", key));
                }
                JSONObject value = (JSONObject) entry.getValue();
                for (Map.Entry<String, Object> entry_1 : value.entrySet()) {
                    String k = entry_1.getKey();//运算符
                    Object v = entry_1.getValue();//值
                    MongoQuery.OperateType OperateType = MongoQuery.OperateType.fromString(k);
                    if (OperateType == MongoQuery.OperateType.Unkown) {
                        throw new IllegalArgumentException(String.format("operate type'%s' unknown", k));
                    }
                    TwoTuple<String, MongoQuery.FieldType> tuple = fieldMap.get(key);
                    querys.add(OperateType.getQuery(tuple.first, tuple.second, v));
                }
            }
        }
        return new MongoSearchQuery(offset, limit, filters, querys.size() > 0 ? Filters.and(querys) : new Document(), orderBson);
    }

    /*                             新增操作                             */
    /*                             新增操作                             */
    /*                             新增操作                             */

    /**
     * 新增数据
     *
     * @param collectionName
     * @param doc
     */
    public void insert(String collectionName, Document doc) {
        MongoCollection<Document> collection = mongoDatabase.getCollection(collectionName);
        collection.insertOne(doc);
    }

    /**
     * 新增数据,只有object id
     *
     * @param collectionName
     * @param _id
     */
    public void insert(String collectionName, Object _id) {
        MongoCollection<Document> collection = mongoDatabase.getCollection(collectionName);

        Document doc = new Document();
        doc.append("_id", _id);

        collection.insertOne(doc);
    }

    /**
     * 批量插入
     *
     * @param collectionName
     * @param docs
     */
    public void insertMany(String collectionName, List<Document> docs) {
        MongoCollection<Document> collection = mongoDatabase.getCollection(collectionName);
        collection.insertMany(docs);
    }


    /**
     * 递增
     *
     * @param collectionName
     * @param filter
     * @param update
     */
    public void incByNum(String collectionName, Bson filter, Document update) {
        MongoCollection<Document> collection = mongoDatabase.getCollection(collectionName);
        collection.updateOne(filter, new Document("$inc", update), new UpdateOptions().upsert(true));
    }

    /**
     * 找到就递增，没找到就放弃
     *
     * @param collectionName
     * @param filter
     * @param update
     */
    public void findAndInc(String collectionName, Bson filter, Document update) {
        MongoCollection<Document> collection = mongoDatabase.getCollection(collectionName);
        collection.findOneAndUpdate(filter, new Document("$inc", update));
    }


    /*                             删除操作                             */
    /*                             删除操作                             */
    /*                             删除操作                             */

    /**
     * 根据id进行删除
     *
     * @param collectionName
     * @param id
     */
    public void deleteByID(String collectionName, Object id) {
        MongoCollection<Document> collection = mongoDatabase.getCollection(collectionName);
        collection.findOneAndDelete(Filters.eq("_id", id));
    }

    /**
     * 根据id列表进行删除
     *
     * @param collectionName
     * @param ids
     */
    public void deleteByIDs(String collectionName, List<Object> ids) {
        MongoCollection<Document> collection = mongoDatabase.getCollection(collectionName);
        collection.deleteMany(Filters.in("_id", ids));
    }

    /*                             修改操作                             */
    /*                             修改操作                             */
    /*                             修改操作                             */

    /**
     * 更新数据
     *
     * @param collectionName
     * @param filter
     * @param update
     */
    public void update(String collectionName, Bson filter, Document update) {
        MongoCollection<Document> collection = mongoDatabase.getCollection(collectionName);
        collection.updateOne(filter, new Document("$set", update), new UpdateOptions().upsert(true));
    }

    /**
     * 找到并且更新
     *
     * @param collectionName
     * @param filter
     * @param update
     */
    public void findOneAndUpdate(String collectionName, Bson filter, Document update) {
        MongoCollection<Document> collection = mongoDatabase.getCollection(collectionName);
        collection.findOneAndUpdate(filter, new Document("$set", update));
    }

    /**
     * 向数组push一个文档
     *
     * @param collectionName
     * @param filter
     * @param update
     */
    public void push(String collectionName, Bson filter, Document update) {
        MongoCollection<Document> collection = mongoDatabase.getCollection(collectionName);
        collection.updateOne(filter, new Document("$push", update));
    }

    /**
     * 向数组pull一个文档
     *
     * @param collectionName
     * @param filter
     * @param update
     */
    public void pull(String collectionName, Bson filter, Document update) {
        MongoCollection<Document> collection = mongoDatabase.getCollection(collectionName);
        collection.updateOne(filter, new Document("$pull", update));
    }

    /**
     * 更新多个
     *
     * @param collectionName
     * @param filter
     * @param update
     */
    public void updateMulti(String collectionName, Bson filter, Document update) {
        MongoCollection<Document> collection = mongoDatabase.getCollection(collectionName);
        collection.updateMany(filter, new Document("$set", update), new UpdateOptions().upsert(true));
    }

    /*                             查找操作                             */
    /*                             查找操作                             */
    /*                             查找操作                             */

    /**
     * 根据id查找
     *
     * @param collectionName
     * @param id
     * @return
     */
    public Document findById(String collectionName, Object id) {
        MongoCollection<Document> collection = mongoDatabase.getCollection(collectionName);
        Document document = collection.find(Filters.eq("_id", id)).first();
        if (null == document) {
            document = new Document();
        }
        return document;
    }

    /**
     * 根据条件查询一个
     *
     * @param collectionName
     * @param filter
     * @return
     */
    public Document queryOne(String collectionName, Bson filter) {
        MongoCollection<Document> collection = mongoDatabase.getCollection(collectionName);
        Document document = collection.find(filter).first();
        if (null == document) {
            document = new Document();
        }
        return document;
    }

    /**
     * 根据条件查询
     *
     * @param collectionName
     * @param filter
     * @return
     */
    public MongoCursor<Document> query(String collectionName, Bson filter) {
        MongoCollection<Document> collection = mongoDatabase.getCollection(collectionName);
        return collection.find(filter).iterator();
    }

    /**
     * 根据条件查询
     *
     * @param collectionName
     * @param searchQuery
     * @return
     */
    public MongoCursor<Document> query(String collectionName, MongoSearchQuery searchQuery) {
        MongoCollection<Document> collection = mongoDatabase.getCollection(collectionName);
        return collection.find(searchQuery.getQuery())
                .limit(searchQuery.getLimit())
                .skip(searchQuery.getOffset())
                .sort(searchQuery.getSort())
                .iterator();
    }

    /**
     * 根据条件获取总数
     *
     * @param collectionName
     * @param filter
     * @return
     */
    public long count(String collectionName, Bson filter) {
        MongoCollection<Document> collection = mongoDatabase.getCollection(collectionName);
        return collection.count(filter);
    }


    /*                             系统操作                             */
    /*                             系统操作                             */
    /*                             系统操作                             */

    /**
     * 查看是否存在集合名称
     *
     * @param collectionName
     * @return
     */
    public boolean exist(String collectionName) {
        boolean exist = false;
        MongoCursor<String> cursor = mongoDatabase.listCollectionNames().iterator();
        while (cursor.hasNext()) {
            if (collectionName.equals(cursor.next())) {
                exist = true;
                break;
            }
        }
        return exist;
    }

    /**
     * 创建索引
     *
     * @param collectionName
     * @param index
     */
    public void createIndex(String collectionName, Bson index) {
        MongoCollection<Document> collection = mongoDatabase.getCollection(collectionName);
        collection.createIndex(index);
    }

    /**
     * 查询某个索引是否存在
     *
     * @param collectionName
     * @param indexName
     * @return
     */
    public boolean exsitIndex(String collectionName, String indexName) {
        boolean exist = false;
        MongoCollection<Document> collection = mongoDatabase.getCollection(collectionName);
        MongoCursor<Document> cursor = collection.listIndexes().iterator();
        while (cursor.hasNext()) {
            Document index = cursor.next();
            String name = index.getString("name");
            if (name.equals(indexName)) {
                exist = true;
                break;
            }
        }

        return exist;
    }

    /*                             其他操作                             */
    /*                             其他操作                             */
    /*                             其他操作                             */

    /**
     * 更新或插入 (找到就更新，没找到就插入新数据)
     *
     * @param collectionName
     * @param filter
     * @param update
     */
    public void upsert(String collectionName, Bson filter, Document update) {
        MongoCollection<Document> collection = mongoDatabase.getCollection(collectionName);
        collection.findOneAndUpdate(filter, new Document("$set", update), new FindOneAndUpdateOptions().upsert(true));
    }
}
