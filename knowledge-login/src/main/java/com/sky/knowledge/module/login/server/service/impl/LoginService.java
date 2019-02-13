package com.sky.knowledge.module.login.server.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sky.knowledge.module.framework.server.context.SessionContext;
import com.sky.knowledge.module.framework.server.dao.IUserDao;
import com.sky.knowledge.module.framework.server.util.CryptoUtil;
import com.sky.knowledge.module.framework.shared.domain.User;
import com.sky.knowledge.module.login.server.service.ILoginService;
import com.sky.knowledge.module.login.shared.exception.LoginException;
import com.sky.knowledge.module.login.shared.exception.LoginExceptionType;
@Service
@Transactional(readOnly=true)
public class LoginService implements ILoginService{

	@Resource(name = "userDao")
	private IUserDao userDao;
	
	public User userLogin(String username, String pwd) {
		if (username == null) {
			throw new LoginException(LoginExceptionType.UserNameIsNull);
		}
		if (pwd == null) {
			throw new LoginException(LoginExceptionType.LoginPasswordIsNull);
		}
		//应用OA的加密方式   modify by ztjie 2011-11-21
		pwd = CryptoUtil.digestByMD5(pwd);
		User user = this.userDao.getByLoginName(username);
		if (user == null) {
			throw new LoginException(LoginExceptionType.UserIsNull);
		}
		//如果用户已经被禁用，则不能登录
		if(user.getStatus()==0){
			throw new LoginException(LoginExceptionType.UserIsDisable);
		}
		if (!pwd.equals(user.getPassword())) {
			throw new LoginException(LoginExceptionType.LoginPasswordIsError);
		}
		userDao.updateLastLoginDate(user);
		return user;
	}

	public void userLogout() {
		// TODO Auto-generated method stub
		SessionContext.invalidateSession();
	}

}
