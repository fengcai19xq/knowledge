package com.sky.knowledge.module.login.server.service;

import com.sky.knowledge.module.framework.shared.domain.User;
import com.sky.knowledge.module.framework.shared.service.IService;


/**
 * 用户登录服务层
 * @author Administrator
 *
 */
public interface ILoginService extends IService {

	/**
	 * 用户登录
	 * @param username
	 * @param pwd
	 * @return
	 */
	User userLogin(String username,String pwd);
	
	/**
	 * 用户退出
	 */
	void userLogout();
	
}
