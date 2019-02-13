package com.sky.knowledge.module.framework.cache.storage;

import java.util.HashMap;
import java.util.Map;

import com.sky.knowledge.module.framework.cache.ICacheStorage;

/**
 * 存储缓存数据
 * @description
 * @create xq
 * @date 2014-6-10
 */
public class StrongCacheStorage<K, V> implements ICacheStorage<K, V> {
    /**
     * 
     */
    private volatile HashMap<K, V> map;
    
    /**
     * 
     * @see com.deppon.foss.framework.cache.ICacheStorage#set(java.lang.Object, java.lang.Object)
     * set
     * @param key
     * @param value
     * @since:0.6
     */
    @Override
    public void set(K key, V value) {
        map.put(key, value);
    }

    /**
     * 
     * @see com.deppon.foss.framework.cache.ICacheStorage#get(java.lang.Object)
     * get
     * @param key
     * @return
     * @since:0.6
     */
    @Override
    public V get(K key) {
        return map.get(key);
    }
    
    /**
     * 
     * @see com.deppon.foss.framework.cache.ICacheStorage#remove(java.lang.Object)
     * remove
     * @param key
     * @since:0.6
     */
    @Override
    public void remove(K key) {
    }
    
    /**
     * 
     * @see com.deppon.foss.framework.cache.ICacheStorage#clear()
     * clear
     * @since:0.6
     */
    @Override
    public void clear() {
    }
    
    /**
     * 
     * @see com.deppon.foss.framework.cache.ICacheStorage#set(java.util.Map)
     * set
     * @param values
     * @since:0.6
     */
    @Override
    public void set(Map<K, V> values) {
        map = (HashMap<K, V>) values;
    }
    
    /**
     * 
     * @see com.deppon.foss.framework.cache.ICacheStorage#get()
     * get
     * @return
     * @since:0.6
     */
    @Override
    public Map<K, V> get() {
        return map;
    }
}