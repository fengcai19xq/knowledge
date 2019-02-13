package com.sky.knowledge.module.framework.cache.init.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.sky.knowledge.module.framework.cache.init.DefaultStrongCache;
import com.sky.knowledge.module.framework.cache.provider.IBatchCacheProvider;
import com.sky.knowledge.module.framework.shared.entity.IFunction;


/**
 * 自动加载菜单缓存对象
 * @description
 * @create xq
 * @date 2014-6-11
 */
@Component
public class FunctionCache extends DefaultStrongCache<String, IFunction>{
	
	public final static String UUID = IFunction.class.getName();
	
	@Override
	public String getUUID() {
		return UUID;
	}

	@Resource(name = "functionCacheProvider")
	@Override
	public void setCacheProvider(
			IBatchCacheProvider<String, IFunction> cacheProvider) {
		super.setCacheProvider(cacheProvider);
	}

	
	
	
	
}

