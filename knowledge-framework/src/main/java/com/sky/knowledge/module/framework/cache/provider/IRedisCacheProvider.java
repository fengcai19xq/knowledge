package com.sky.knowledge.module.framework.cache.provider;

import java.util.Map;

import com.sky.knowledge.module.framework.cache.ICacheProvider;

public interface IRedisCacheProvider<K,V> extends ICacheProvider<K,V>{
	/**
	 * 批量加载数据
	 * get
	 * @return
	 * @return Map<K,V>
	 * @since: 0.6
	 */
    Map<K, V> get();
}
