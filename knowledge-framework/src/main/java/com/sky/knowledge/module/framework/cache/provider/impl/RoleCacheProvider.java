package com.sky.knowledge.module.framework.cache.provider.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.sky.knowledge.module.framework.cache.provider.IBatchCacheProvider;
import com.sky.knowledge.module.framework.server.dao.IRoleDao;
import com.sky.knowledge.module.framework.shared.domain.Role;
import com.sky.knowledge.module.framework.shared.entity.IRole;


/**
 * 角色缓存数据提供者
 * @description
 * @create xq
 * @date 2014-6-10
 */
@Component
public class RoleCacheProvider implements IBatchCacheProvider<String, IRole>{

	@Resource(name = "roleDao")
	private IRoleDao roleDao;

	
	@Override
	public Date getLastModifyTime() {
		return roleDao.getLastModifyTime();
	}

	@Override
	public Map<String, IRole> get() {
		List<Role> roles = roleDao.getAllRole();
		Map<String, IRole> map = new HashMap<String, IRole>();
		for (Role role : roles) {
			map.put(role.getId(), role);
		}
		return map;
	}

	
	

}