package com.sic777.db.mongo.data;


import java.util.List;

import com.sic777.db.mongo.type.DataFieldType;
import com.sic777.utils.DateFormatTools;
import com.sic777.utils.container.ContainerGetter;
import org.bson.Document;
import org.bson.conversions.Bson;

import com.alibaba.fastjson.JSONArray;
import com.mongodb.client.model.Filters;


/**
 * <p></p>
 *
 * @author Zhengzhenxie
 * @version v1.0
 * @since 2018-08-15
 */

public class MongoQuery {

    public static enum FieldType {

        String, Integer, Short, Date, Boolean, Double

    }

    public static enum OpreateType {

        /**
         * 未知
         */
        Unkown("unkown") {
            @Override
            public Bson getQuery(String field, MongoQuery.FieldType fieldType, Object value) {
                return new Document();
            }

            @Override
            public Bson getNosqlQuery(String field, DataFieldType fieldType, Object value) {
                return new Document();
            }
        },

        NIN("$nin") {
            @Override
            public Bson getQuery(String field, MongoQuery.FieldType fieldType, Object value) {
                JSONArray array = JSONArray.parseArray(value.toString());
                Object[] values = new Object[array.size()];
                for (int ix = 0, len = array.size(); ix < len; ++ix) {
                    // array.set(ix, funcGetDataValue(fieldType,
                    // array.getString(ix)));
                    values[ix] = funcGetDataValue(fieldType, array.getString(ix));
                }
                return Filters.nin(field, values);
            }

            @Override
            public Bson getNosqlQuery(String field, DataFieldType fieldType, Object value) {
                JSONArray array = JSONArray.parseArray(value.toString());
                Object[] values = new Object[array.size()];
                for (int ix = 0, len = array.size(); ix < len; ++ix) {
                    // array.set(ix, funcGetDataValue(fieldType,
                    // array.getString(ix)));
                    values[ix] = funcGetDataValue(fieldType, array.getString(ix));
                }
                return Filters.nin(field, values);
            }
        },

        /**
         * 包含于
         */
        IN("$in") {
            @Override
            public Bson getQuery(String field, MongoQuery.FieldType fieldType, Object value) {
                JSONArray array = JSONArray.parseArray(value.toString());
                Object[] values = new Object[array.size()];
                for (int ix = 0, len = array.size(); ix < len; ++ix) {
                    values[ix] = funcGetDataValue(fieldType, array.getString(ix));
                }
                return Filters.in(field, values);
            }

            @Override
            public Bson getNosqlQuery(String field, DataFieldType fieldType, Object value) {
                JSONArray array = JSONArray.parseArray(value.toString());
                Object[] values = new Object[array.size()];
                for (int ix = 0, len = array.size(); ix < len; ++ix) {
                    values[ix] = funcGetDataValue(fieldType, array.getString(ix));
                }
                return Filters.in(field, values);
            }
        },
        /**
         * 小于
         */
        LT("$lt") {
            @Override
            public Bson getQuery(String field, MongoQuery.FieldType fieldType, Object value) {
                return Filters.lt(field, funcGetDataValue(fieldType, value));
            }

            @Override
            public Bson getNosqlQuery(String field, DataFieldType fieldType, Object value) {
                return Filters.lt(field, funcGetDataValue(fieldType, value));
            }
        },
        /**
         * 小于或等于
         */
        LTE("$lte") {
            @Override
            public Bson getQuery(String field, MongoQuery.FieldType fieldType, Object value) {
                return Filters.lte(field, funcGetDataValue(fieldType, value));
            }

            @Override
            public Bson getNosqlQuery(String field, DataFieldType fieldType, Object value) {
                return Filters.lte(field, funcGetDataValue(fieldType, value));
            }
        },
        /**
         * 大于
         */
        GT("$gt") {
            @Override
            public Bson getQuery(String field, MongoQuery.FieldType fieldType, Object value) {
                return Filters.gt(field, funcGetDataValue(fieldType, value));
            }

            @Override
            public Bson getNosqlQuery(String field, DataFieldType fieldType, Object value) {
                return Filters.gt(field, funcGetDataValue(fieldType, value));
            }
        },
        /**
         * 大于或等于
         */
        GTE("$gte") {
            @Override
            public Bson getQuery(String field, MongoQuery.FieldType fieldType, Object value) {
                return Filters.gte(field, funcGetDataValue(fieldType, value));
            }

            @Override
            public Bson getNosqlQuery(String field, DataFieldType fieldType, Object value) {
                return Filters.gte(field, funcGetDataValue(fieldType, value));
            }
        },

        /**
         * 等于
         */
        EQ("$eq") {
            @Override
            public Bson getQuery(String field, MongoQuery.FieldType fieldType, Object value) {
                return Filters.eq(field, funcGetDataValue(fieldType, value));
            }

            @Override
            public Bson getNosqlQuery(String field, DataFieldType fieldType, Object value) {
                return Filters.eq(field, funcGetDataValue(fieldType, value));
            }
        },

        /**
         * 等于
         */
        NE("$ne") {
            @Override
            public Bson getQuery(String field, MongoQuery.FieldType fieldType, Object value) {
                return Filters.ne(field, funcGetDataValue(fieldType, value));
            }

            @Override
            public Bson getNosqlQuery(String field, DataFieldType fieldType, Object value) {
                return Filters.ne(field, funcGetDataValue(fieldType, value));
            }
        },

        /**
         * 模糊匹配,只支持字符串
         */
        LIKE("$like") {
            @Override
            public Bson getQuery(String field, MongoQuery.FieldType fieldType, Object value) {
                return Filters.regex(field, "^.*" + value.toString().replaceAll("\\.", "\\\\."), "i");// 不区分大小写
            }

            @Override
            public Bson getNosqlQuery(String field, DataFieldType fieldType, Object value) {
                return Filters.regex(field, "^.*" + value.toString().replaceAll("\\.", "\\\\."), "i");// 不区分大小写
            }
        },

        /**
         * mongo中熟悉为数组时,逻辑与查询
         */
        ALL("$all") {
            @Override
            public Bson getQuery(String field, FieldType fieldType, Object value) {
                JSONArray array = JSONArray.parseArray(value.toString());
                List<Object> valueList = ContainerGetter.arrayList();
                for (int ix = 0, len = array.size(); ix < len; ++ix) {
                    valueList.add(funcGetDataValue(fieldType, array.getString(ix)));
                }
                return Filters.all(field, valueList);
            }

            @Override
            public Bson getNosqlQuery(String field, DataFieldType fieldType, Object value) {
                JSONArray array = JSONArray.parseArray(value.toString());
                List<Object> valueList = ContainerGetter.arrayList();
                for (int ix = 0, len = array.size(); ix < len; ++ix) {
                    valueList.add(funcGetDataValue(fieldType, array.getString(ix)));
                }
                return Filters.all(field, valueList);
            }

        };
        private final String opt;

        private OpreateType(String opt) {
            this.opt = opt;
        }

        public abstract Bson getQuery(String field, MongoQuery.FieldType fieldType, Object value);

        public abstract Bson getNosqlQuery(String field, DataFieldType fieldType, Object value);

        public String opreate() {
            return opt;
        }

        public static final OpreateType fromString(String opt) {
            for (OpreateType ot : values()) {
                if (ot.opreate().equals(opt)) {
                    return ot;
                }
            }
            return Unkown;
        }

        protected Object funcGetDataValue(DataFieldType fieldType, Object v) {
            switch (fieldType) {
                case Bool:
                    return null != v && Boolean.parseBoolean(v.toString());
                case Date:
                    return DateFormatTools.funcGetDateWithFormat(v.toString(), "yyyy-MM-dd'T'HH:mm:ss.SS'Z'");
                case Int:
                    return Integer.parseInt(v.toString());
                case String:
                    return v.toString();
                case Float:
                    return Float.parseFloat(v.toString());
                default:
                    throw new UnsupportedOperationException();
            }
        }

        /**
         * 将对应的字段值转为对应类型
         *
         * @param fieldType
         * @param v
         * @return
         */
        protected Object funcGetDataValue(MongoQuery.FieldType fieldType, Object v) {
            switch (fieldType) {
                case Boolean:
                    return null != v && Boolean.parseBoolean(v.toString());
                case Date:
                    return DateFormatTools.funcGetDateWithFormat(v.toString(), "yyyy-MM-dd'T'HH:mm:ss.SS'Z'");
                case Integer:
                    return Integer.parseInt(v.toString());
                case String:
                    return v.toString();
                case Short:
                    return Float.parseFloat(v.toString());
                case Double:
                    return Double.parseDouble(v.toString());
                default:
                    throw new UnsupportedOperationException();
            }
        }

    }

    public static enum OrderType {
        /**
         * 未知
         */
        Unkown("unkown", -1),
        /**
         * 降序
         */
        DESC("desc", -1),
        /**
         * 升序
         */
        ASC("asc", 1),;

        private final String mysql;
        private final int nosql;

        private OrderType(String type, int nosql) {
            this.mysql = type;
            this.nosql = nosql;
        }

        public String mysql() {
            return mysql;
        }

        public int nosql() {
            return nosql;
        }

        public static final OrderType fromString(String type) {
            for (OrderType ot : values()) {
                if (ot.mysql().equalsIgnoreCase(type)) {
                    return ot;
                }
            }
            return OrderType.Unkown;
        }
    }
}

