package com.project.gelingeducation.common.authentication.cache;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;
import com.project.gelingeducation.common.authentication.cache.IRedisManager;
import com.project.gelingeducation.common.authentication.cache.RedisCache;
import com.project.gelingeducation.common.authentication.cache.serializer.ObjectSerializer;
import com.project.gelingeducation.common.authentication.cache.serializer.RedisSerializer;
import com.project.gelingeducation.common.authentication.cache.serializer.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class RedisCacheManager implements CacheManager {

	private final Logger logger = LoggerFactory.getLogger(RedisCacheManager.class);

	// fast lookup by name map
	private final ConcurrentMap<String, Cache> caches = new ConcurrentHashMap<String, Cache>();
	private RedisSerializer keySerializer = new StringSerializer();
	private RedisSerializer valueSerializer = new ObjectSerializer();

	private com.project.gelingeducation.common.authentication.cache.IRedisManager redisManager;

	// expire time in seconds
	public static final int DEFAULT_EXPIRE = 1800;
	private int expire = DEFAULT_EXPIRE;

	/**
	 * The Redis key prefix for caches
	 */
	public static final String DEFAULT_CACHE_KEY_PREFIX = "cache:cache:";
	private String keyPrefix = DEFAULT_CACHE_KEY_PREFIX;

	public static final String DEFAULT_PRINCIPAL_ID_FIELD_NAME = "id";
	private String principalIdFieldName = DEFAULT_PRINCIPAL_ID_FIELD_NAME;

	@Override
	public <K, V> Cache<K, V> getCache(String name) throws CacheException {
		logger.debug("get cache, name=" + name);

		Cache cache = caches.get(name);

		if (cache == null) {
			cache = new RedisCache<K, V>(redisManager, keySerializer, valueSerializer, keyPrefix + name + ":", expire, principalIdFieldName);
			caches.put(name, cache);
		}
		return cache;
	}

	public com.project.gelingeducation.common.authentication.cache.IRedisManager getRedisManager() {
		return redisManager;
	}

	public void setRedisManager(IRedisManager redisManager) {
		this.redisManager = redisManager;
	}

	public String getKeyPrefix() {
		return keyPrefix;
	}

	public void setKeyPrefix(String keyPrefix) {
		this.keyPrefix = keyPrefix;
	}

	public RedisSerializer getKeySerializer() {
		return keySerializer;
	}

	public void setKeySerializer(RedisSerializer keySerializer) {
		this.keySerializer = keySerializer;
	}

	public RedisSerializer getValueSerializer() {
		return valueSerializer;
	}

	public void setValueSerializer(RedisSerializer valueSerializer) {
		this.valueSerializer = valueSerializer;
	}

	public int getExpire() {
		return expire;
	}

	public void setExpire(int expire) {
		this.expire = expire;
	}

	public String getPrincipalIdFieldName() {
		return principalIdFieldName;
	}

	public void setPrincipalIdFieldName(String principalIdFieldName) {
		this.principalIdFieldName = principalIdFieldName;
	}
}
