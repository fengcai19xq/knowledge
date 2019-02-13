package com.sky.knowledge.module.login.server.action;

import javax.annotation.Resource;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sky.knowledge.module.framework.server.components.security.SecurityNonCheckRequired;
import com.sky.knowledge.module.framework.server.context.SessionContext;
import com.sky.knowledge.module.framework.server.context.UserContext;
import com.sky.knowledge.module.framework.server.web.action.AbstractAction;
import com.sky.knowledge.module.framework.shared.domain.User;
import com.sky.knowledge.module.login.server.service.ILoginService;
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
@Controller
public class LoginAction extends AbstractAction{

	private static final long serialVersionUID = -5182857592065055743L;
   
	@Resource
	private ILoginService loginService;
	//用户名
		private String loginName;
		
		//密码
		private String password;
		
		private User loginUser;
		  private User currentLoginUser;
		/**
		 * 
		 * @description
		 * @create xq
		 * @date 2014-6-9
		 */
		@Override
		@SecurityNonCheckRequired
		public String execute() throws Exception{
			User user = loginService.userLogin(loginName, password);
			SessionContext.setCurrentUser(user.getId());
			return super.execute();
		}

		  @SecurityNonCheckRequired
		  public String getUser()
		  {
		    this.loginUser = ((User)UserContext.getCurrentUser());
		    return "success";
		  }

		  public String queryCurrentLoginUser()
		  {
		    this.currentLoginUser = ((User)UserContext.getCurrentUser());
		    return "success";
		  }

		public String getLoginName() {
			return loginName;
		}

		public void setLoginName(String loginName) {
			this.loginName = loginName;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public User getLoginUser() {
			return loginUser;
		}

		public void setLoginUser(User loginUser) {
			this.loginUser = loginUser;
		}

		public User getCurrentLoginUser() {
			return currentLoginUser;
		}

		public void setCurrentLoginUser(User currentLoginUser) {
			this.currentLoginUser = currentLoginUser;
		}
		
}
