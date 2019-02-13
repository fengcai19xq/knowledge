package com.sky.knowledge.module.framework.cache.init.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.sky.knowledge.module.framework.cache.init.DefaultStrongCache;
import com.sky.knowledge.module.framework.cache.provider.IBatchCacheProvider;
import com.sky.knowledge.module.framework.shared.entity.IRole;


/**
 * 自动加载角色缓存对象
 * @description
 * @create xq
 * @date 2014-6-11
 */
@Component
public class RoleCache extends DefaultStrongCache<String, IRole> {
	public final static String UUID = IRole.class.getName();

	@Override
	public String getUUID() {
		return UUID;
	}

	@Resource(name = "roleCacheProvider")
	@Override
	public void setCacheProvider(
			IBatchCacheProvider<String, IRole> cacheProvider) {
		super.setCacheProvider(cacheProvider);
	}

}