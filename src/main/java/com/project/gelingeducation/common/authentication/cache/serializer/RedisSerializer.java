package com.project.gelingeducation.common.authentication.cache.serializer;

import com.project.gelingeducation.common.authentication.cache.exception.SerializationException;

public interface RedisSerializer<T> {

    byte[] serialize(T t) throws SerializationException;

    T deserialize(byte[] bytes) throws SerializationException;
}
