package com.sky.knowledge.module.framework.cache.init.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.sky.knowledge.module.framework.cache.init.DefaultLRUCache;
import com.sky.knowledge.module.framework.cache.provider.ILazyCacheProvider;
import com.sky.knowledge.module.framework.shared.entity.IUser;


/**
 * 用户对象缓存
 * @author ztjie
 *
 */
@Component
public class UserCache extends DefaultLRUCache<String, IUser>{
	public final static String UUID = IUser.class.getName();
	
	@Override
	public String getUUID() {
		return UUID;
	}

	@Resource(name = "userCacheProvider")
	public void setCacheProvider(ILazyCacheProvider<String, IUser> cacheProvider) {
		super.setCacheProvider(cacheProvider);
	}

	
	
}