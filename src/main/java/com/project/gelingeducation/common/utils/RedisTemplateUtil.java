package com.project.gelingeducation.common.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @Author: LL
 * @Description: Redis的工具
 */
@Component
public class RedisTemplateUtil {

    /**
     * spring封装的redis
     */
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 设置数据
     *
     * @param key     键
     * @param value   值
     * @param seconds 过期时间 秒
     */
    public void set(String key, Object value, Long seconds) {
        redisTemplate.opsForValue().set(key, value, seconds, TimeUnit.SECONDS);
    }

    /**
     * 设置数据
     *
     * @param key   键
     * @param value 值
     */
    public void set(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    /**
     * 获取值
     *
     * @param key 键
     * @return
     */
    public Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    /**
     * 删除数据
     * @param key 键
     */
    public void delete(String key) {
        redisTemplate.delete(key);
    }

    /**
     * 获取特定规则的key字符串set
     *
     * @param pattern 匹配的字符串
     * @return
     */
    public Set<String> keys(String pattern) {
        return redisTemplate.keys(pattern);
    }
}
