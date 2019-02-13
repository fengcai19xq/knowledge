package com.sky.knowledge.module.framework.cache.storage;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;
import redis.clients.jedis.exceptions.JedisConnectionException;
import redis.clients.jedis.exceptions.JedisException;

import com.sky.knowledge.module.framework.server.util.CacheUtils;
import com.sky.knowledge.module.framework.shared.exception.redis.KeyIsNotFoundException;
import com.sky.knowledge.module.framework.shared.exception.redis.RedisCacheStorageException;
import com.sky.knowledge.module.framework.shared.exception.redis.RedisConnectionException;
import com.sky.knowledge.module.framework.shared.exception.redis.ValueIsBlankException;
import com.sky.knowledge.module.framework.shared.exception.redis.ValueIsNullException;


/**
 * 通过redis存储缓存数据
 * 1.支持序列化json
 * @description
 * @create xq
 * @date 2014-11-11
 */
public  class RedisCacheStorage<K,V>  {

	
    private ShardedJedisPool    shardedJedisPool;
	
	 /**
     * 日志
     */
    Log log = LogFactory.getLog(getClass());

    /**
     * 默认数据过期时间
     */
    private int expire = 3600 * 24;
    
    /** 
     * <p>存入数据，默认时效：3600 * 24</p> 
     * @author ningyu
     * @date 2012-10-22 下午5:24:47
     * @param key
     * @param value 
     * @return boolean 是否执行成功
     * @see com.deppon.foss.framework.cache.IRemoteCacheStore#set(java.lang.Object, java.lang.Object)
     */
    public boolean set(K key, V value) {
        return set(key, value, expire);
    }
    
    
    /**<p>存入有时效的数据</p> 
     * 
     * @description
     * @create xq
     * @date 2014-11-11
     */
    public boolean set(K key, V value, int exp) {
        
    	ShardedJedis j = null;
        String skey = CacheUtils.toJsonString(key);
        String svalue = CacheUtils.toJsonString(value);
        if(skey == null) {
            throw new RedisCacheStorageException("key does not allow for null!");
        }
        boolean borrowOrOprSuccess = true;
        try {
            j = shardedJedisPool.getResource();
            if(null==j)return false ;
            j.setex(skey, exp, svalue);
        } catch (JedisException e) {
            borrowOrOprSuccess = false;
            if (j != null) {
            	shardedJedisPool.returnBrokenResource(j);
            }
            log.error(e.getMessage(),e);
            return false;
//          client.handoverToSlave();
//          set(key, value, exp);
        } finally {
            if (borrowOrOprSuccess) {
                //返回到资源池
            	shardedJedisPool.returnResource(j);
            }
        }
        return true;
    }
    /**
     * <p>获取key对应的数据</p> 
     * @description
     * @create xq
     * @date 2014-11-11
     */
    @SuppressWarnings("unchecked")
    public V get(K key) {
    	ShardedJedis j = null;
        String skey = CacheUtils.toJsonString(key);
        if(skey == null) {
            throw new RedisCacheStorageException("key does not allow for null!");
        }
        try {
            j = shardedJedisPool.getResource();
            String re = j.get(skey);
            //返回到资源池
            shardedJedisPool.returnResource(j);
            if(re != null) {
                if(StringUtils.isBlank(re)) {
                    //key存在，value为空串
                    throw new ValueIsBlankException("key exists, value is blank!");
                } else if(StringUtils.equalsIgnoreCase(re, "nil")) {
                    //key不存在
                    throw new KeyIsNotFoundException("key is not found!");
                } else if(StringUtils.equalsIgnoreCase(re, "null")) {
                    //key存在，value为null
                    throw new ValueIsNullException("key exists, value is null!");
                } else {
                    return (V) CacheUtils.jsonParseObject(re);
                }
            } else {
                //key不存在
                throw new KeyIsNotFoundException("key is not found!");
            }
        } catch (JedisException e) {
            if (j != null) {
            	shardedJedisPool.returnBrokenResource(j);
            }
            log.error(e.getMessage(),e);
            throw new RedisConnectionException(e);
//            client.handoverToSlave();
//            return get(key);
        }
    }
   /**
    * <p>删除指定的缓存信息</p> 
    * @description
    * @create xq
    * @date 2014-11-11
    */
    public void remove(K key) {
        
        ShardedJedis j = null;
        String skey = CacheUtils.toJsonString(key);
        if(skey == null) {
            throw new RedisCacheStorageException("key does not allow for null!");
        }
        boolean borrowOrOprSuccess = true;
        try {
            j = shardedJedisPool.getResource();
            j.del(skey);
        } catch (JedisException e) {
            borrowOrOprSuccess = false;
            if (j != null) {
            	shardedJedisPool.returnBrokenResource(j);
            }
            log.error(e.getMessage(),e);
//          client.handoverToSlave();
//          remove(key);
        } finally {
            if (borrowOrOprSuccess) {
            	shardedJedisPool.returnResource(j);//返回到资源池
            }
        }
    }
   

    /**
     * 获取key对应的数据
     * @description
     * @create xq
     * @date 2014-11-11
     */
    @SuppressWarnings("unchecked")
    public V hget(String cacheId,K key){
       
        if(StringUtils.isBlank(cacheId)) {
            throw new RedisCacheStorageException("cacheId does not allow for null!");
        }
        ShardedJedis j = null;
        String skey = CacheUtils.toJsonString(key);
        if(skey == null) {
            throw new RedisCacheStorageException("key does not allow for null!");
        }
        String re = null;
        try {
            j = shardedJedisPool.getResource();
            re = j.hget(cacheId, skey);
            shardedJedisPool.returnResource(j);//返回到资源池
            if(re != null) {
                if(StringUtils.isBlank(re)) {
                    //key存在，value为空串
                    throw new ValueIsBlankException("key exists, value is blank!");
                } else if(StringUtils.equalsIgnoreCase(re, "nil")) {
                    //key不存在
                    throw new KeyIsNotFoundException("key is not found!");
                } else if(StringUtils.equalsIgnoreCase(re, "null")) {
                    //key存在，value为null
                    throw new ValueIsNullException("key exists, value is null!");
                } else {
                    return (V) CacheUtils.jsonParseObject(re);
                }
            } else {
                //key不存在
                throw new KeyIsNotFoundException("key is not found!");
            }
        } catch (JedisException e) {
            if (j != null) {
            	shardedJedisPool.returnBrokenResource(j);
            }
            log.error(e.getMessage(),e);
            throw new RedisConnectionException(e);
//            client.handoverToSlave();
//            return hget(cacheId, key);
        }
    }
    
   /**
    * 存入数据
    * @description
    * @create xq
    * @date 2014-11-11
    */
    public boolean hset(String cacheId,K key,V value){
       
        if(StringUtils.isBlank(cacheId)) {
            throw new RedisCacheStorageException("cacheId does not allow for null!");
        }
        ShardedJedis j = null;
        String skey = CacheUtils.toJsonString(key);
        String svalue = CacheUtils.toJsonString(value);
        if(skey == null) {
            throw new RedisCacheStorageException("key does not allow for null!");
        }
        boolean borrowOrOprSuccess = true;
        try {
            j = shardedJedisPool.getResource();
            j.hset(cacheId, skey, svalue);
        } catch (JedisException e) {
            borrowOrOprSuccess = false;
            if (j != null) {
            	shardedJedisPool.returnBrokenResource(j);
            }
            log.error(e.getMessage(),e);
            return false;
//          client.handoverToSlave();
//          hset(cacheId,key,value);
        } finally {
            if (borrowOrOprSuccess) {
            	shardedJedisPool.returnResource(j);
            }
        }
        return true;
    }
    
   /**
    * 获取数据
    * @description
    * @create xq
    * @date 2014-11-11
    */
    @SuppressWarnings("unchecked")
    public Map<K, V> hget(String cacheId){
       
        if(StringUtils.isBlank(cacheId)) {
            throw new RedisCacheStorageException("cacheId does not allow for null!");
        }
        ShardedJedis j = null;
        try {
            j = shardedJedisPool.getResource();
            Map<String,String> map = j.hgetAll(cacheId);
            shardedJedisPool.returnResource(j);//返回到资源池
            Map<K, V> result = null;
            //由string转成对象
            if(map != null) {
                for(Map.Entry<String,String> entry : map.entrySet()) {
                    if(result == null) {
                        result = new HashMap<K,V>();
                    }
                    result.put((K) CacheUtils.jsonParseObject(entry.getKey()), (V) CacheUtils.jsonParseObject(entry.getValue()));
                }
                return result;
            }
        } catch (JedisException e) {
            if (j != null) {
            	shardedJedisPool.returnBrokenResource(j);
            }
            log.error(e.getMessage(),e);
            throw new RedisConnectionException(e);
//          client.handoverToSlave();
//          return hget(cacheId);
        }
        return null;
    }
    
    /**
     * 删除
     * @description
     * @create xq
     * @date 2014-11-11
     */
    public void hremoveMulti(final String cacheId,final K ... keys) {
        
        if(StringUtils.isBlank(cacheId)) {
            throw new RedisCacheStorageException("cacheId does not allow for null!");
        }
        if(keys == null) {
            throw new RedisCacheStorageException("keys does not allow for null!");
        }
        ShardedJedis j = null;
        String[] skeys = new String[keys.length];
        for(int i = 0;i<keys.length;i++) {
            skeys[i] = CacheUtils.toJsonString(keys[i]);
        }
        boolean borrowOrOprSuccess = true;
        try {
            j = shardedJedisPool.getResource();
            j.hdel(cacheId, skeys);
        } catch (JedisException e) {
            borrowOrOprSuccess = false;
            if (j != null) {
            	shardedJedisPool.returnBrokenResource(j);
            }
            log.error(e.getMessage(),e);
//            client.handoverToSlave();
//            hremove(cacheId, key);
        } finally {
            if (borrowOrOprSuccess) {
            	shardedJedisPool.returnResource(j);
            }
        }
    }
    
    /**
     * 删除
     * @description
     * @create xq
     * @date 2014-11-11
     */
    public void hremove(final String cacheId,final K key) {
       
        if(StringUtils.isBlank(cacheId)) {
            throw new RedisCacheStorageException("cacheId does not allow for null!");
        }
        ShardedJedis j = null;
        String skey = CacheUtils.toJsonString(key);
        if(skey == null) {
            throw new RedisCacheStorageException("key does not allow for null!");
        }
        boolean borrowOrOprSuccess = true;
        try {
            j = shardedJedisPool.getResource();
            j.hdel(cacheId, skey);
        } catch (JedisException e) {
            borrowOrOprSuccess = false;
            if (j != null) {
            	shardedJedisPool.returnBrokenResource(j);
            }
            log.error(e.getMessage(),e);
//            client.handoverToSlave();
//            hremove(cacheId, key);
        } finally {
            if (borrowOrOprSuccess) {
            	shardedJedisPool.returnResource(j);
            }
        }
    }
    
    public void hremove(final String cacheId) {
       
        if(StringUtils.isBlank(cacheId)) {
            throw new RedisCacheStorageException("cacheId does not allow for null!");
        }
        boolean borrowOrOprSuccess = true;
        ShardedJedis j = null;
        try {
            j = shardedJedisPool.getResource();
            j.del(cacheId);
        } catch (JedisException e) {
            borrowOrOprSuccess = false;
            if (j != null) {
            	shardedJedisPool.returnBrokenResource(j);
            }
            log.error(e.getMessage(),e);
//            client.handoverToSlave();
//            hremove(cacheId);
        } finally {
            if (borrowOrOprSuccess) {
            	shardedJedisPool.returnResource(j);
            }
        }
    }
    
    /**
     * <p>初始化map</p>
     * @description
     * @create xq
     * @date 2014-11-11
     */
    public void initializationStrongCache(final String cacheId, Map<K,V> map) {
        
        boolean borrowOrOprSuccess = true;
        ShardedJedis j = null;
        try {
            j = shardedJedisPool.getResource();
            boolean isSuccess =  j.exists(cacheId);
            if(!isSuccess) {//是否已存在相应的cacheId
                for(Map.Entry<K, V> entry : map.entrySet()) {
                    String key = CacheUtils.toJsonString(entry.getKey());
                    String value = CacheUtils.toJsonString(entry.getValue());
                    if(key == null || value == null) {
                        log.error("storage cache initialization error: key and value does not allow for null!");
                        j.setnx(cacheId, "false");
                        return;
                    }
                    j.hsetnx(cacheId, key, value);
                }
                j.setnx(cacheId, "true");
               
                } 
        } catch(JedisConnectionException e) {
            borrowOrOprSuccess = false;
            if (j != null) {
            	shardedJedisPool.returnBrokenResource(j);
            }
            log.error(e.getMessage(),e);
//            client.handoverToSlave();
//            initializationStrongCache(cacheId, map);
        } finally {
            if (borrowOrOprSuccess) {
            	shardedJedisPool.returnResource(j);
            }
        }
    }


	public ShardedJedisPool getShardedJedisPool() {
		return shardedJedisPool;
	}


	public void setShardedJedisPool(ShardedJedisPool shardedJedisPool) {
		this.shardedJedisPool = shardedJedisPool;
	}
    
    
}
