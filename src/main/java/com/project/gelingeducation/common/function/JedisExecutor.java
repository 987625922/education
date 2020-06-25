package com.project.gelingeducation.common.function;


/**
 * @author MrBird
 */
@FunctionalInterface
public interface JedisExecutor<T, R> {
    R excute(T t) throws Exception;
}
