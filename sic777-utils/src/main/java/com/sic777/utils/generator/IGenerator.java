package com.sic777.utils.generator;

public interface IGenerator<T> {

    T next() throws Exception;
}
