package com.sic777.common.utils.generator;


/**
 * <p>生成器接口
 *
 * @author sic777
 * @since 0.0.1
 */
public interface IGenerator<T> {

    T next() throws Exception;
}
