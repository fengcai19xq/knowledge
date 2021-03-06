package com.sky.knowledge.module.framework.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public class CacheManager<K, V> {

	@SuppressWarnings("rawtypes")
	private static final CacheManager INSTANCE = new CacheManager();

	/**
	 * 保存所有缓存实例
	 */
	private final Map<String, ICache<K, V>> uuidCaches = new ConcurrentHashMap<String, ICache<K, V>>();

	/**
	 *  禁止从外部拿到实例
	  * 创建一个新的实例 CacheManager.
	  * @since 0.6
	 */
	private CacheManager() {
	}

	@SuppressWarnings("rawtypes")
	public static CacheManager getInstance() {
		return INSTANCE;
	}

	/**
	 * 系统启动后自动注册所有的缓存类别
	 * 
	 * @param cache
	 */
	public void registerCacheProvider(ICache<K, V> cache) {
		// 不允许UUID重复，应用必须在实现的Cache接口确保命名不重复
		String uuid = cache.getUUID();
		if (uuidCaches.containsKey(uuid)) {
			throw new CacheConfigException("Dumplicate uuid " + uuid
					+ " to cache provider " + cache.getClass().getName()
					+ " and " + uuidCaches.get("uuid").getClass().getName());
		}

		uuidCaches.put(uuid, cache);
	}

	/**
	 * 根据uuid获取缓存实例
	 * getCache
	 * @param uuid
	 * @return
	 * @return ICache<K,V>
	 * @since:
	 */
	public ICache<K, V> getCache(String uuid) {
		ICache<K, V> cache = uuidCaches.get(uuid);
		if (cache == null) {
			throw new CacheConfigException(
					"No register cache provider for cache UUID " + uuid);
		}
		return cache;
	}


	public void shutdown() {
		uuidCaches.clear();
	}
}