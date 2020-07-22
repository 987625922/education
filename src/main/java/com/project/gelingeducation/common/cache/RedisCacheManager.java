package com.project.gelingeducation.common.cache;

import org.springframework.cache.Cache;
import org.springframework.cache.support.AbstractCacheManager;

import java.util.Collection;

/**
 * spring-cache对redis封装的缓存管理器
 * @author LL
 */
public class RedisCacheManager extends AbstractCacheManager {

    private Collection<? extends RedisCache> caches;

    public void setCaches(Collection<? extends RedisCache> caches) {
        this.caches = caches;
    }

    @Override
    protected Collection<? extends Cache> loadCaches() {
        return caches;
    }
}
