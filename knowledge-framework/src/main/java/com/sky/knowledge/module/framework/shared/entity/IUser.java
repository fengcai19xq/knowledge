package com.sky.knowledge.module.framework.shared.entity;

import java.util.List;
import java.util.Set;


public interface IUser  extends IEntity{
	
	@Deprecated
	List<IRole> getRoles();
	
	@Deprecated
	void setRoles(List<IRole> roles);
	
	/**
	 * 获取用户的所有角色id
	 * getRoleids
	 * @return
	 * @return Set<String>
	 * @since:0.9
	 */
	Set<String> getRoleids();
	
	void setRoleids(Set<String> roleids);
}
