package com.sky.knowledge.module.framework.server.dao;

import java.util.Date;
import java.util.List;

import com.sky.knowledge.module.framework.shared.domain.Role;


/**
 * 角色数据处理方法
 * @author Administrator
 *
 */
public interface IRoleDao {

	/**
	 * 查询最后更新时间
	 * @return
	 */
	Date getLastModifyTime();
	/**
	 * 提供缓存加载所有的角色对象与角色所对应的功能ID
	 * @return
	 */
	List<Role> getAllRole();
	

}