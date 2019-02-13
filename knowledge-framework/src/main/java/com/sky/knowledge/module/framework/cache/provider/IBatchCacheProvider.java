package com.sky.knowledge.module.framework.cache.provider;

import java.util.Map;

import com.sky.knowledge.module.framework.cache.ICacheProvider;

/**
 * 批量加载缓存接口
 * @description
 * @create xq
 * @date 2014-6-10
 */
public interface IBatchCacheProvider<K, V> extends ICacheProvider<K, V> {
    
	/**
	 * 批量加载数据
	 * get
	 * @return
	 * @return Map<K,V>
	 * @since: 0.6
	 */
    Map<K, V> get();
}