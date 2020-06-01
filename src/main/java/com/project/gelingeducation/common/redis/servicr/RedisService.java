package com.project.gelingeducation.common.redis.servicr;

import com.project.gelingeducation.common.exception.AllException;
import org.aspectj.lang.JoinPoint;
import redis.clients.jedis.Jedis;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Set;

public interface RedisService {
    /**
     * 获取 redis key 数量
     *
     * @return Map
     */
    Map<String, Object> getKeysSize() throws AllException;

    /**
     * 获取 redis 内存信息
     *
     * @return Map
     */
    Map<String, Object> getMemoryInfo() throws AllException;

    /**
     * 获取 key
     *
     * @param pattern 正则
     * @return Set
     */
    Set<String> getKeys(String pattern) throws AllException;

    /**
     * get命令
     *
     * @param key key
     * @return String
     */
    String get(String key) throws AllException;

    /**
     * set命令
     *
     * @param key   key
     * @param value value
     * @return String
     */
    String set(String key, String value) throws AllException;

    /**
     * set 命令
     *
     * @param key         key
     * @param value       value
     * @param milliscends 毫秒
     * @return String
     */
    String set(String key, String value, Long milliscends) throws AllException;

    /**
     * del命令
     *
     * @param key key
     * @return Long
     */
    Long del(String... key) throws AllException;

    /**
     * exists命令
     *
     * @param key key
     * @return Boolean
     */
    Boolean exists(String key) throws AllException;

    /**
     * pttl命令
     *
     * @param key key
     * @return Long
     */
    Long pttl(String key) throws AllException;

    /**
     * pexpire命令
     *
     * @param key         key
     * @param milliscends 毫秒
     * @return Long
     */
    Long pexpire(String key, Long milliscends) throws AllException;


    /**
     * zadd 命令
     *
     * @param key    key
     * @param score  score
     * @param member value
     */
    Long zadd(String key, Double score, String member) throws AllException;

    /**
     * zrangeByScore 命令
     *
     * @param key key
     * @param min min
     * @param max max
     * @return Set<String>
     */
    Set<String> zrangeByScore(String key, String min, String max) throws AllException;

    /**
     * zremrangeByScore 命令
     *
     * @param key   key
     * @param start start
     * @param end   end
     * @return Long
     */
    Long zremrangeByScore(String key, String start, String end) throws AllException;

    /**
     * zrem 命令
     *
     * @param key     key
     * @param members members
     * @return Long
     */
    Long zrem(String key, String... members) throws AllException;

    /**判断是否存在key*/
    public boolean containKey(String key) throws AllException;

    /**获取key*/
    public String getKeyForAop(JoinPoint joinPoint, HttpServletRequest request);
}
