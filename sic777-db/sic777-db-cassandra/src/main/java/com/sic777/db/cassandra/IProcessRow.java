package com.sic777.db.cassandra;

import com.datastax.driver.core.Row;

/**
 * <p>数据集处理接口</p>
 *
 * @author Zhengzhenxie
 * @version v1.0
 * @since 2018-02-11 11:50
 */
public interface IProcessRow<T> {
    T process(Row row);
}
