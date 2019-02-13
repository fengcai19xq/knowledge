package com.sky.knowledge.module.framework.server.dao.impl;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.sky.knowledge.module.framework.server.components.ibatis.IBatis3DaoImpl;
import com.sky.knowledge.module.framework.server.dao.IRoleDao;
import com.sky.knowledge.module.framework.shared.domain.Role;


@SuppressWarnings("unchecked")
@Repository
public class RoleDao extends IBatis3DaoImpl implements IRoleDao {

	public Date getLastModifyTime() {
		Date lastModyfyTime = (Date) getSqlSession()
				.selectOne(
						"com.sky.knowledge.module.framework.shared.domain.Role.getLastModifyTime");
		return lastModyfyTime;
	}
	
	public List<Role> getAllRole() {
		List<Role> list = null;
		list = getSqlSession()
				.selectList(
						"com.sky.knowledge.module.framework.shared.domain.Role.getAllRole");
		return list;
	}
	
	

}