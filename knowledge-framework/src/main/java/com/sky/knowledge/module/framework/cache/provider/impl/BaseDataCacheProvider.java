package com.sky.knowledge.module.framework.cache.provider.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.sky.knowledge.module.framework.cache.provider.IRedisCacheProvider;
import com.sky.knowledge.module.framework.server.dao.IBaseDataDao;
import com.sky.knowledge.module.framework.shared.domain.BaseData;
import com.sky.knowledge.module.framework.shared.entity.IBaseData;

/**
 * 系统基础资料缓存数据提供者
 * @description
 * @create xq
 * @date 2014-6-10
 */
@Component
public class BaseDataCacheProvider implements IRedisCacheProvider<String, IBaseData>{

	@Resource
	private IBaseDataDao dao ;
	
	@Override
	public Date getLastModifyTime() {
		// TODO Auto-generated method stub
		return dao.getLastModifyTime();
	}

	@Override
	public Map<String, IBaseData> get() {
		// TODO Auto-generated method stub
		List<BaseData> list = (List<BaseData>) dao.getAll();
		Map<String,IBaseData> map = new HashMap<String,IBaseData>();
		for(BaseData data:list){
			map.put(data.getFid(),data);
		}
		return map;
	}

}
