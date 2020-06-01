package com.project.gelingeducation.common.redis.servicr.impl;

import com.project.gelingeducation.common.exception.AllException;
import com.project.gelingeducation.common.exception.StatusEnum;
import com.project.gelingeducation.common.function.JedisExecutor;
import com.project.gelingeducation.common.redis.servicr.RedisService;
import com.project.gelingeducation.common.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Client;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

@Slf4j
@Service
public class RedisServiceImpl implements RedisService {
    @Autowired
    JedisPool jedisPool;

    private static String separator = System.getProperty("line.separator");

    /**
     * 处理 jedis请求
     *
     * @param j 处理逻辑，通过 lambda行为参数化
     * @return 处理结果
     */
    private <T> T excuteByJedis(JedisExecutor<Jedis, T> j) throws AllException {
        try (Jedis jedis = jedisPool.getResource()) {
            return j.excute(jedis);
        } catch (Exception e) {
            throw new AllException(StatusEnum.REDIS_CONNECT_ERROR);
        }
    }


    @Override
    public Map<String, Object> getKeysSize() throws AllException {
        Long dbSize = this.excuteByJedis(
                j -> {
                    Client client = j.getClient();
                    client.dbSize();
                    return client.getIntegerReply();
                }
        );
        Map<String, Object> map = new HashMap<>();
        map.put("create_time", System.currentTimeMillis());
        map.put("dbSize", dbSize);
        return map;
    }

    @Override
    public Map<String, Object> getMemoryInfo() throws AllException {
        String info = this.excuteByJedis(
                j -> {
                    Client client = j.getClient();
                    client.info();
                    return client.getBulkReply();
                }
        );
        String[] strs = Objects.requireNonNull(info).split(separator);
        Map<String, Object> map = null;
        for (String s : strs) {
            String[] detail = s.split(":");
            if ("used_memory".equals(detail[0])) {
                map = new HashMap<>();
                map.put("used_memory", detail[1].substring(0, detail[1].length() - 1));
                map.put("create_time", System.currentTimeMillis());
                break;
            }
        }
        return map;
    }

    @Override
    public Set<String> getKeys(String pattern) throws AllException {
        return this.excuteByJedis(j -> j.keys(pattern));
    }

    @Override
    public String get(String key) throws AllException {
        return this.excuteByJedis(j -> j.get(key.toLowerCase()));
    }

    @Override
    public String set(String key, String value) throws AllException {
        return this.excuteByJedis(j -> j.set(key.toLowerCase(), value));
    }

    @Override
    public String set(String key, String value, Long milliscends) throws AllException {
        String result = this.set(key.toLowerCase(), value);
        this.pexpire(key, milliscends);
        return result;
    }

    @Override
    public Long del(String... key) throws AllException {
        return this.excuteByJedis(j -> j.del(key));
    }

    @Override
    public Boolean exists(String key) throws AllException {
        return this.excuteByJedis(j -> j.exists(key));
    }

    @Override
    public Long pttl(String key) throws AllException {
        return this.excuteByJedis(j -> j.pttl(key));
    }

    @Override
    public Long pexpire(String key, Long milliseconds) throws AllException {
        return this.excuteByJedis(j -> j.pexpire(key, milliseconds));
    }

    @Override
    public Long zadd(String key, Double score, String member) throws AllException {
        return this.excuteByJedis(j -> j.zadd(key, score, member));
    }

    @Override
    public Set<String> zrangeByScore(String key, String min, String max) throws AllException {
        return this.excuteByJedis(j -> j.zrangeByScore(key, min, max));
    }

    @Override
    public Long zremrangeByScore(String key, String start, String end) throws AllException {
        return this.excuteByJedis(j -> j.zremrangeByScore(key, start, end));
    }

    @Override
    public Long zrem(String key, String... members) throws AllException {
        return this.excuteByJedis(j -> j.zrem(key, members));
    }

    @Override
    public boolean containKey(String key) throws AllException {
        boolean b;
        Jedis jedis = null;
        try{
            jedis = jedisPool.getResource();
            b = jedis.exists(key);
            return b;
        }catch (Exception e){
            log.error("Redis server error:："+e.getMessage());
            return false;
        }finally {
            returnResource(jedis);
        }
    }

    /**释放jedis实例资源*/
    public void returnResource(Jedis jedis) {
        if(jedis != null){
            jedis.close();
        }
    }

    @Override
    public String getKeyForAop(JoinPoint joinPoint,
                               HttpServletRequest request) {
        //获取参数的序列化
        Object[] objects = joinPoint.getArgs();
        String args = JsonUtils.jsonToString(objects[0]);
        //获取请求url
        String url = request.getRequestURI();
        //获取请求的方法
        String method = request.getMethod();
        //获取当前日期,规避默认init情况
        String date = LocalDate.now().toString();
        //key值获取
        return args + url + method + date;
    }
}
