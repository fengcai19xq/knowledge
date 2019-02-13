package com.sky.knowledge.module.framework.cache;

import java.util.Map;


/**
 * 抽象的数据缓存仓库
 * 
 * @author xq
 * 
 */
public interface ICacheStorage<K, V> {
    
    /**
     * 存放数据
     * 
     * @param key
     * @param value
     */
    void set(K key, V value);
    
    /**
     * 
     * @param values
     */
    void set(Map<K, V> values);
    
    /**
     * 获取数据
     * 
     * @param key
     */
    V get(K key);
    
    /**
     * 移除指定的数据
     * 
     * @param key
     */
    void remove(K key);
    
    /**
     * 移除所有的数据
     */
    void clear();
    
    /**
     * 
     * @return
     */
    Map<K, V> get();
}