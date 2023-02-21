package org.jpmh.dao;

import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class CacheHelper<Key, Value>{

    private Class<Key> cacheKey;
    private Class<Value> cacheValue;
    private String nameCache;

    private CacheManager cacheManager;
    private Cache<Key, Value> exampleCache;

    protected CacheHelper(Class<Key> cacheKey, Class<Value> cacheValue, String nameCache) {
        this.cacheKey = cacheKey;
        this.cacheValue = cacheValue;
        this.nameCache = nameCache;
    }

    protected void initCacheHelper(int heapSize) {
        cacheManager = CacheManagerBuilder.newCacheManagerBuilder().build();
        cacheManager.init();

        exampleCache = cacheManager.createCache(nameCache, CacheConfigurationBuilder
                .newCacheConfigurationBuilder(cacheKey, cacheValue, ResourcePoolsBuilder.heap(heapSize)));
    }

    public Cache<Key, Value> getExampleCacheFromCacheManager() {
        return cacheManager.getCache(nameCache, cacheKey, cacheValue);
    }

    protected void add(Key key, Value value) {
        Cache<Key, Value> cache = getExampleCacheFromCacheManager();
        cache.put(key, value);
    }

    protected Value get(Key key) {
        Cache<Key, Value> cache = getExampleCacheFromCacheManager();
        return cache.get(key);
    }

    protected Value replace(Key key, Value value) {
        Cache<Key, Value> cache = getExampleCacheFromCacheManager();
        return cache.replace(key, value);
    }

    protected void delete(Key key) {
        Cache<Key, Value> cache = getExampleCacheFromCacheManager();
        cache.remove(key);
    }

    protected List<Value> getAll() {
        List<Cache.Entry<Key, Value>> listEntry = StreamSupport
                .stream(getExampleCacheFromCacheManager().spliterator(), false)
                .collect(Collectors.toList());

        List<Value> listObject = listEntry
                .stream()
                .map(Cache.Entry::getValue)
                .collect(Collectors.toList());
        return listObject;
    }

    protected Cache<Key, Value> getExampleCache() {
        return exampleCache;
    }

    protected void setExampleCacheCache(Cache<Key, Value> exampleCacheCache) {
        this.exampleCache = exampleCacheCache;
    }

}
