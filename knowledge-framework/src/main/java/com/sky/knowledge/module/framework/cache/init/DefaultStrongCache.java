package com.sky.knowledge.module.framework.cache.init;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import com.sky.knowledge.module.framework.cache.CacheManager;
import com.sky.knowledge.module.framework.cache.ICache;
import com.sky.knowledge.module.framework.cache.provider.IBatchCacheProvider;
import com.sky.knowledge.module.framework.cache.storage.StrongCacheStorage;

/**
 * 实现InitializingBean接口，服务启动时会自动调用
 * @description
 * @create xq
 * @date 2014-6-11
 */
public abstract class DefaultStrongCache<K, V> implements ICache<K, V>, InitializingBean, DisposableBean {

	private IBatchCacheProvider<K, V> cacheProvider;

	private StrongCacheStorage<K, V> cacheStorage;

	private long interval = 15L * 60 * 1000;
	
	private ReloadThread thread;

	public DefaultStrongCache() {
		this.cacheStorage = new StrongCacheStorage<K, V>();
	}

	public void setTimeOut(int interval) {
		this.interval = ((long) interval) * 60 * 1000;
	}

	@SuppressWarnings("unchecked")
	public void afterPropertiesSet() throws Exception {
		Map<K, V> map = this.cacheProvider.get();
		cacheStorage.set(map);
		//注册cache
		CacheManager.getInstance().registerCacheProvider(this);
		Date startDate = this.cacheProvider.getLastModifyTime();
		//启动刷新线程
		thread = new ReloadThread(startDate, "STRONGCACHE_"
				+ this.getUUID());
		thread.setDaemon(true);//将该线程标记为守护线程
		thread.start();
	}

	public void setCacheProvider(IBatchCacheProvider<K, V> cacheProvider) {
		this.cacheProvider = cacheProvider;
	}

	@Override
	final public V get(K key) {
		return cacheStorage.get(key);
	}

	@Override
	final public Map<K, V> get() {
		return cacheStorage.get();
	}
	
	/**
	 * 应用停止时终止线程
	 * @see org.springframework.beans.factory.DisposableBean#destroy()
	 * destroy
	 * @throws Exception
	 * @since:
	 */
	@Override
	public void destroy() throws Exception {
		while (thread.state != 2) {
			synchronized (this) {
				this.wait(30l * 1000);
			}
		}
		thread.interrupt();
	}
	
	private class ReloadThread extends Thread {
		private Date startTime;
		
		private volatile int state;

		ReloadThread(Date startTime, String threadName) {
			super(threadName);
			this.startTime = startTime;
		}

		@Override
		public void run() {
			while (true) {
				try {
					state = 2;
					Thread.sleep(interval);
				} catch (InterruptedException e) {
					e.printStackTrace();
					break;
				}
				try {
					state = 1;
					//如果最后更新时间早于当前时间
					//更新所有数据
					Date lastTime = cacheProvider.getLastModifyTime();
					if (lastTime.after(startTime)) {
						Map<K, V> map = cacheProvider.get();
						cacheStorage.set(map);
						startTime = lastTime;
					}
				} catch (Exception ex) {
					ex.printStackTrace();
					break;
				}
			}
		}

	}
}