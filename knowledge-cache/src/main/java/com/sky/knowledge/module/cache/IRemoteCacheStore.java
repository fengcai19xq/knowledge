package com.sky.knowledge.module.cache;

/**
 * 
 * 
*******************************************
* <b style="font-family:微软雅黑"><small>Description:远程缓存接口</small></b>   </br>
* <b style="font-family:微软雅黑"><small>HISTORY</small></b></br>
* <b style="font-family:微软雅黑"><small> ID      DATE    PERSON     REASON</small></b><br>
********************************************
* <div style="font-family:微软雅黑,font-size:70%"> 
* 1  2011-4-14 樊斌  新增
* 2  2011-5-12 周丹枫 修改   
* </div>  
********************************************
 */
public interface IRemoteCacheStore<K, V> {

	/**
     * 主动向Cache更新指定的数据
     * 
     * @param k
     * @param v
     * @return boolean 是否执行成功
     */
    
    boolean set(K key, V value);
    
    /**
     * 主动向Cache更新指定的数据,指定过期时间
     * @param k
     * @param v
     * @param time
     * @return boolean 是否执行成功
     */
    boolean set(K key, V value, int exp);
    
    /**
     * 获取缓存
     * 
     * @param key 缓存Key
     * @return 缓存Value
     */
    V get(K key);
    
    /**
     * 删除指定的缓存信息
     * @param key 缓存Key
     */
    void remove(K key);
    
    /**
     * 删除多个key的缓存信息
     * @author ningyu
     * @date 2013-4-9 下午3:52:37
     * @param keys 动态参数 数组[]
     * @see
     */
    void removeMulti(K ... keys);
}
