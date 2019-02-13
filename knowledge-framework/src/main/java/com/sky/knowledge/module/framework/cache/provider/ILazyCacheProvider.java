package com.sky.knowledge.module.framework.cache.provider;

import java.util.Date;
import java.util.Map;

import com.sky.knowledge.module.framework.cache.ICacheProvider;


/**
 * 
 * 
*******************************************
* <b style="font-family:微软雅黑"><small>Description:延迟加载缓存</small></b>   </br>
* <b style="font-family:微软雅黑"><small>HISTORY</small></b></br>
* <b style="font-family:微软雅黑"><small> ID      DATE    PERSON     REASON</small></b><br>
********************************************
* <div style="font-family:微软雅黑,font-size:70%"> 
* 1 2011-4-14 樊斌  新增
* </div>  
********************************************
 */
public interface ILazyCacheProvider<K, V> extends ICacheProvider<K, V> {
    /**
     * 加载单个元素
     * get
     * @param key
     * @return
     * @return V
     * @since:
     */
    V get(K key);
    
    /**
     * 加载最近被更新的数据
     * getUpdateObjectMaps
     * @param time
     * @return
     * @return Map<K,V>
     * @since:
     */
    Map<K, V> getUpdateObjectMaps(Date time);
}
