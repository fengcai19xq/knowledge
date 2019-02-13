package com.sky.knowledge.module.framework.server.dao.impl;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.sky.knowledge.module.framework.server.components.ibatis.IBatis3DaoImpl;
import com.sky.knowledge.module.framework.server.dao.IUserDao;
import com.sky.knowledge.module.framework.shared.domain.User;
@Repository
public class UserDao extends IBatis3DaoImpl implements IUserDao {

	public List<User> getAll(User user) {
		List<User> list = null;
		User model = new User();
		if (user != null) {
			if (user.getId() != null && !"".equals(user.getId())) {
				String id = "%" + user.getId() + "%";
				model.setId(id);
			}
			model.setEmpCode(user.getEmpCode());
			if (user.getLoginName() != null && !"".equals(user.getLoginName())) {
				String loginName = "%" + user.getLoginName() + "%";
				model.setLoginName(loginName);
			}
			if (user.getPassword() != null && !"".equals(user.getPassword())) {
				String password = "%" + user.getPassword() + "%";
				model.setPassword(password);
			}
			model.setLastLogin(user.getLastLogin());
			model.setStatus(user.getStatus());
			model.setInvalidDate(user.getInvalidDate());
			model.setValidDate(user.getValidDate());
		}
		list = getSqlSession()
				.selectList(
						"com.sky.knowledge.module.framework.shared.domain.User.getAll",
						model);
		return list;
	}

	
	public void update(User user) {
		getSqlSession()
				.update("com.sky.knowledge.module.framework.shared.domain.User.update",
						user);
	}

	
	public void insert(User user) {
		getSqlSession()
				.insert("com.sky.knowledge.module.framework.shared.domain.User.insert",
						user);
	}

	
	public void deleteById(String id) {
		getSqlSession()
				.delete("com.sky.knowledge.module.framework.shared.domain.User.deleteById",
						id);
	}

	
	public List<User> getAll(User user, int limit, int start) {
		RowBounds rowBounds = new RowBounds(start, limit);
		List<User> list = null;
		User model = new User();
		if (user != null) {
			if (user.getId() != null && !"".equals(user.getId())) {
				String id = "%" + user.getId() + "%";
				model.setId(id);
			}
			model.setEmpCode(user.getEmpCode());
			if (user.getLoginName() != null && !"".equals(user.getLoginName())) {
				String loginName = "%" + user.getLoginName() + "%";
				model.setLoginName(loginName);
			}
			if (user.getPassword() != null && !"".equals(user.getPassword())) {
				String password = "%" + user.getPassword() + "%";
				model.setPassword(password);
			}
			model.setLastLogin(user.getLastLogin());
			model.setStatus(user.getStatus());
			model.setInvalidDate(user.getInvalidDate());
			model.setValidDate(user.getValidDate());
		}
		list = getSqlSession()
				.selectList(
						"com.sky.knowledge.module.framework.shared.domain.User.getAll",
						model, rowBounds);
		return list;
	}

	
	public Long count(User user) {
		User model = new User();
		if (user != null) {
			if (user.getId() != null && !"".equals(user.getId())) {
				String id = "%" + user.getId() + "%";
				model.setId(id);
			}
			model.setEmpCode(user.getEmpCode());
			if (user.getLoginName() != null && !"".equals(user.getLoginName())) {
				String loginName = "%" + user.getLoginName() + "%";
				model.setLoginName(loginName);
			}
			if (user.getPassword() != null && !"".equals(user.getPassword())) {
				String password = "%" + user.getPassword() + "%";
				model.setPassword(password);
			}
			model.setLastLogin(user.getLastLogin());
			model.setStatus(user.getStatus());
			model.setInvalidDate(user.getInvalidDate());
			model.setValidDate(user.getValidDate());
		}
		Long count = (Long) getSqlSession()
				.selectOne(
						"com.sky.knowledge.module.framework.shared.domain.User.count",
						model);
		return count;
	}

	
	public Date getLastModifyTime() {
		Date lastModifyTime = (Date) getSqlSession()
				.selectOne(
						"com.sky.knowledge.module.framework.shared.domain.User.getLastModifyTime");
		return lastModifyTime;
	}

	
	public User getUserWithRoleIdAndFunctionCodeById(String userId) {
		User user = (User) getSqlSession()
		.selectOne(
				"com.sky.knowledge.module.framework.shared.domain.User.getUserWithRoleIdAndFunctionCodeById",userId);
		return user;
	}

	
	public List<User> getByLastModifyDate(Date lastModifyDate) {
		if (lastModifyDate == null)
			return null;
		List<User> userList = getSqlSession().selectList(
				"com.sky.knowledge.module.framework.shared.domain.User.getByLastModifyDate",
				lastModifyDate);
		return userList;
	}

	
	public User getByLoginName(String loginName) {
		User user = (User) getSqlSession().selectOne("com.sky.knowledge.module.framework.shared.domain.User.getByLoginName",loginName);
		return user;
	}

	/* (non-Javadoc)
	 * @see com.deppon.foss.module.authorization.server.dao.IUserDao#updateUserPassword(com.sky.knowledge.module.framework.shared.domain.User)
	 */
	
	public void updateUserPassword(User user) {
		getSqlSession().update("com.sky.knowledge.module.framework.shared.domain.User.updatePassword", user);
	}

	
	public void updateLastLoginDate(User user) {
		getSqlSession().update("com.sky.knowledge.module.framework.shared.domain.User.updateLastLoginDate",user.getId());
	}

	
	public User getById(String id) {
		// TODO Auto-generated method stub
		return (User)getSqlSession().selectOne("com.sky.knowledge.module.framework.shared.domain.User.getById",id);
	}

	
	public List<User> queryAllByDeptId(String deptId, int limit, int start) {
		// TODO Auto-generated method stub
		RowBounds rowBounds = new RowBounds(start, limit);
		return getSqlSession()
		.selectList(
				"com.sky.knowledge.module.framework.shared.domain.User.getAllBydeptId",
				deptId, rowBounds);
	}

	
	public Long count(String deptId) {
		Long count = (Long) getSqlSession()
		.selectOne(
				"com.sky.knowledge.module.framework.shared.domain.User.countBydeptId",
				deptId);
		return count;
	}

}
