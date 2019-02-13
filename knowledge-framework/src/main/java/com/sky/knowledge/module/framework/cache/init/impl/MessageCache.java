package com.sky.knowledge.module.framework.cache.init.impl;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.sky.knowledge.module.framework.cache.init.DefaultStrongCache;
import com.sky.knowledge.module.framework.cache.provider.IBatchCacheProvider;
import com.sky.knowledge.module.framework.shared.util.Properties;
/**
 * 自动加载各个模块message.properties文件
 * @description
 * @create xq
 * @date 2014-6-11
 */
@Component
public class MessageCache extends DefaultStrongCache<String, Map<String, Properties>> {
    
    public final static String UUID = MessageCache.class.getName();
        
    @SuppressWarnings("unchecked")
    @javax.annotation.Resource(name = "messageCacheProvider")
    public void setCacheProvider(@SuppressWarnings("rawtypes") IBatchCacheProvider cacheProvider) {
        super.setCacheProvider(cacheProvider);
    }

	@Override
	public String getUUID() {
		return UUID;
	}
}