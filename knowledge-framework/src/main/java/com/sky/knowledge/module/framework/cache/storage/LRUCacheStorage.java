package com.sky.knowledge.module.framework.cache.storage;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.sky.knowledge.module.framework.cache.ICacheStorage;


/**
 * 
 * 
*******************************************
* <b style="font-family:微软雅黑"><small>Description:LRU缓存存储</small></b>   </br>
* <b style="font-family:微软雅黑"><small>HISTORY</small></b></br>
* <b style="font-family:微软雅黑"><small> ID      DATE    PERSON     REASON</small></b><br>
********************************************
* <div style="font-family:微软雅黑,font-size:70%"> 
* 1  2011-4-14 樊斌  新增
* </div>  
********************************************
 */
public class LRUCacheStorage<K, V> implements ICacheStorage<K, V> {
    
    private Map<K, V> map;
    
    public LRUCacheStorage() {
    	this.map = new ConcurrentHashMap<K, V>();
    }
    
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
        map.remove(key);
    }
    
    /**
     * 
     * @see com.deppon.foss.framework.cache.ICacheStorage#clear()
     * clear
     * @since:0.6
     */
    @Override
    public void clear() {
        map.clear();
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
    
    /**
     * 
     * putAll
     * @param m
     * @return void
     * @since:0.6
     */
    public void putAll(Map<K, V> m) {
    	this.map.putAll(m);
    }

    /**
     * 此方法暂无作用
     * @see com.deppon.foss.framework.cache.ICacheStorage#set(java.util.Map)
     * set
     * @param values
     * @since:0.6
     */
	@Override
	public void set(Map<K, V> values) {
	}
    
}
