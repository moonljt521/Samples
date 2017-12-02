package com.example.test_lambda;

/**
 * author: moon
 * created on: 17/12/1 下午5:51
 * description:
 */
public interface Function<T,R> {

//    R apply(T t);

    R apply(T t, R r);
}
