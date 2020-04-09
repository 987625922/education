package com.project.gelingeducation.common.function;

@FunctionalInterface
public interface JedisExecutor<T, R> {
    R excute(T t) throws Exception;
}
