package com.sky.knowledge.module.framework.cache.provider.impl;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.sky.knowledge.module.framework.cache.provider.IBatchCacheProvider;
import com.sky.knowledge.module.framework.server.context.AppContext;
import com.sky.knowledge.module.framework.server.dao.IFunctionDao;
import com.sky.knowledge.module.framework.shared.domain.Function;
import com.sky.knowledge.module.framework.shared.entity.IFunction;


/**
 * 功能权限缓存数据提供对象
 * @description
 * @create xq
 * @date 2014-6-10
 */
@Component
public class FunctionCacheProvider implements IBatchCacheProvider<String, IFunction>{
	
	@Resource(name = "functionDao")
	private IFunctionDao functionDao;
	
	
	@Override
	public Date getLastModifyTime() {
		return functionDao.getLastModifyTime();
	}

	@Override
	public Map<String, IFunction> get() {
		Map<String, IFunction> map = new HashMap<String, IFunction>();
		Collection<Function> funcs = functionDao.getAllChildFunctionByURI("/"+AppContext.getAppContext().getAppName());
		for (Function func : funcs) {
			map.put(func.getUri(), func);
		}
		return map;
	}


}