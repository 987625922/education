package com.project.gelingeducation.common.function;

@FunctionalInterface
public interface CacheSelector<T> {
    T select() throws Exception;
}
