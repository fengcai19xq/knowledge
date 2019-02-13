package com.sky.knowledge.module.framework.server.web.message;

import java.util.Map;

import com.sky.knowledge.module.framework.cache.init.DefaultStrongCache;


/**
 * 
 * 
*******************************************
* <b style="font-family:微软雅黑"><small>Description:动态国际化缓存</small></b>   </br>
* <b style="font-family:微软雅黑"><small>HISTORY</small></b></br>
* <b style="font-family:微软雅黑"><small> ID      DATE    PERSON     REASON</small></b><br>
********************************************
* <div style="font-family:微软雅黑,font-size:70%"> 
* 1 2011-3-28 周丹枫 新增
* </div>  
********************************************
 */
public class AbstractDynamicMessageCache<K, V> extends DefaultStrongCache<String, Map<String, String>> {
    
    public static final String UUID = AbstractDynamicMessageCache.class.getName();

	@Override
	public String getUUID() {
		return UUID;
	}
}