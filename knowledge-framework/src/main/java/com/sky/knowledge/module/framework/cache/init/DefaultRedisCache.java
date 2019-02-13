package com.sky.knowledge.module.framework.cache.init;

import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import com.sky.knowledge.module.framework.cache.CacheManager;
import com.sky.knowledge.module.framework.cache.ICache;
import com.sky.knowledge.module.framework.cache.provider.IRedisCacheProvider;
import com.sky.knowledge.module.framework.cache.storage.RedisCacheStorage;
import com.sky.knowledge.module.framework.shared.exception.redis.KeyIsNotFoundException;
import com.sky.knowledge.module.framework.shared.exception.redis.RedisConnectionException;
import com.sky.knowledge.module.framework.shared.exception.redis.ValueIsBlankException;
import com.sky.knowledge.module.framework.shared.exception.redis.ValueIsNullException;
/**
 * 系统基础资料 缓存 加载者
 * @description
 * @create xq
 * @date 2014-11-11
 */
public abstract class DefaultRedisCache<K,V> implements ICache<K,V>,InitializingBean,DisposableBean {

	private IRedisCacheProvider<K,V> cacheProvider ;
	
	@Resource(name="storage")
	private RedisCacheStorage<K,V>  cacheStorage;
	
	private long interval = 2L * 60 * 1000;
	
	 /**
     * 缓存最后一次修改时间,作为刷新缓存的时间戳
     */
    private Date modifyTime;
    
    /**
     * 自动刷新缓存线程
     */
    private ReloadThread thread;
    
    public DefaultRedisCache() {
    	
	}
	
	@SuppressWarnings("unchecked")
	public void afterPropertiesSet() throws Exception {
		Map<K, V> map = this.cacheProvider.get();
		cacheStorage.initializationStrongCache(getUUID(),map);
		//注册cache
		CacheManager.getInstance().registerCacheProvider(this);
		modifyTime = this.cacheProvider.getLastModifyTime();
		//启动刷新线程
		thread = new ReloadThread("STRONGCACHE_"
				+ this.getUUID());
		thread.setDaemon(true);//将该线程标记为守护线程
		thread.start();
	}
	
	   @Override
	    public V get(K key) {
	        V value = null;
	        try {
	            value = cacheStorage.hget(getUUID(),key);
	        } catch(ValueIsBlankException e) {
	            //key存在，value为空串
	            return null;
	        } catch(ValueIsNullException ex) {
	            //key存在，value为null
	            return null;
	        } catch(KeyIsNotFoundException ex1) {
	            //key不存在
	            return null;
	        } catch(RedisConnectionException exx) {
	            //redis 连接出现异常
	            return null;
	        }
	        return value;
	    }

	    @Override
	    public Map<K, V> get() {
	        try {
	            return cacheStorage.hget(getUUID());
	        } catch(RedisConnectionException e) {
	            //redis 连接出现异常  
	            return cacheProvider.get();
	        }
	        
	    }
	    
	    public void setCacheProvider(IRedisCacheProvider<K, V> cacheProvider) {
	        this.cacheProvider = cacheProvider;
	    }
	    
	    public void setCacheStorage(RedisCacheStorage<K, V> cacheStorage) {
	        this.cacheStorage = cacheStorage;
	    }
	 /**
     * 
     * <p>刷新策略</p> 
     * @author ningyu
     * @date 2013-3-28 上午10:40:04
     * @return 
     * @see com.deppon.foss.framework.cache.RefreshableCache#refresh()
     */
    public boolean refresh() {
        Date lastTime = cacheProvider.getLastModifyTime();
        if (modifyTime != null && lastTime != null && lastTime.after(modifyTime)) {
            Map<K, V> map = cacheProvider.get();
            cacheStorage.hremove(getUUID());
            for (Map.Entry<K, V> entry : map.entrySet()) {
                cacheStorage.hset(getUUID(), entry.getKey(), entry.getValue());
            }
            modifyTime = lastTime;
            return true;
        }
        return false;
    }
    
	private class ReloadThread extends Thread {
		 private volatile int state;

	        ReloadThread(String threadName) {
	            super(threadName);
	        }

	        @Override
	        public void run() {
	            while (true) {
	                try {
	                    state = 2;
	                    //刷新间隔时间
	                    Thread.sleep(interval);
	                } catch (InterruptedException e) {
	                    e.printStackTrace();
	                    break;
	                }
	                try {
	                    state = 1;
	                    //如果最后更新时间早于当前时间
	                    //更新所有数据
	                    refresh();
	                } catch (Exception ex) {
	                	ex.printStackTrace();
	                    break;
	                }
	            }
	        }   

	}
}
