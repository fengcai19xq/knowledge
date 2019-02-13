package com.sky.knowledge.module.framework.cache.init.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.sky.knowledge.module.framework.cache.init.DefaultRedisCache;
import com.sky.knowledge.module.framework.cache.provider.IRedisCacheProvider;
import com.sky.knowledge.module.framework.shared.entity.IBaseData;
/**
 * 系统基础资料 缓存
 * @description
 * @create xq
 * @date 2014-11-11
 */
@Component
public class BaseDataRedisCache extends DefaultRedisCache<String, IBaseData>{

public final static String UUID = IBaseData.class.getName();
	
	@Override
	public String getUUID() {
		return UUID;
	}

	@Resource(name = "baseDataCacheProvider")
	@Override
	public void setCacheProvider(
			IRedisCacheProvider<String, IBaseData> cacheProvider) {
		super.setCacheProvider(cacheProvider);
	}

	@Override
	public void destroy() throws Exception {
		// TODO Auto-generated method stub
		
	}
}
