package com.sky.knowledge.module.framework.cache.provider.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.sky.knowledge.module.framework.cache.provider.ILazyCacheProvider;
import com.sky.knowledge.module.framework.server.dao.IUserDao;
import com.sky.knowledge.module.framework.shared.domain.User;
import com.sky.knowledge.module.framework.shared.entity.IUser;


/**
 * 用户对象数据缓存提供者
 * @author Administrator
 *
 */
@Component
public class UserCacheProvider implements ILazyCacheProvider<String, IUser> {

	@Resource(name = "userDao")
	private IUserDao userDao;

	public Date getLastModifyTime() {
		return userDao.getLastModifyTime();
	}

	public IUser get(String key) {
//		System.out.println("Find User:" + key);
		User user = userDao.getUserWithRoleIdAndFunctionCodeById(key);
		if (user == null)
			return null;
		//User对象中不保存Role对象   modified by zdf
		/*
		Set<String> roleIds = user.getRoleids();
		@SuppressWarnings("unchecked")
		ICache<String, Role> roleCache = (ICache<String, Role>) CacheManager
				.getInstance().getCache(IRole.class.getName());
		
			List<IRole> roles = new ArrayList<IRole>();
		if (roleIds != null) {
			for (String roleId : roleIds) {
				Role role = roleCache.get(roleId);
				roles.add(role);
			}
		}
		user.setRoles(roles);
		*/
		return user;
	}

	public Map<String, IUser> getUpdateObjectMaps(Date time) {
		Map<String, IUser> map = new HashMap<String, IUser>();
		List<User> users = userDao.getByLastModifyDate(time);
		for (User user : users) {
			map.put(user.getId(), user);
		}
		return map;
	}

}