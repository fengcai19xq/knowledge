package com.sky.knowledge.module.framework.cache;

import java.util.Map;


/**
 * 缓存接口
 * 
 * @author xq
 *
 * @param <K> 缓存Key类型
 * @param <V> 缓存Value类型
 */
public interface ICache<K, V> {

    /**
     * 标记当前Cache的UUID
     * @return
     */
    String getUUID();
    
    
    /**
	 * 获取缓存
	 * 
	 * @param key 缓存Key
	 * @return 缓存Value
	 */
	V get(K key);
	
	/**
	 * 一次性取出所有内容
	 * @return
	 */
	Map<K,V> get();

}