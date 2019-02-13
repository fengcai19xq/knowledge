package com.sky.knowledge.module.login.server.action;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sky.knowledge.module.framework.server.components.security.SecurityNonCheckRequired;
import com.sky.knowledge.module.framework.server.context.UserContext;
import com.sky.knowledge.module.framework.server.web.action.AbstractAction;
import com.sky.knowledge.module.framework.shared.domain.User;
import com.sky.knowledge.module.framework.shared.entity.IUser;


/**
 * 主页面
 * @author Administrator
 *
 */
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
@Controller
public class IndexAction extends AbstractAction {

	private static final long serialVersionUID = 4545162877027512021L;

	// 当前用户
	private String currentUserName;

	// 当前用户登录名
	private String currentUserLoginName;

	// 当前用户所属部门
	private String currentUserDeptName;

	/**
	 * 主页面
	 */
	@SecurityNonCheckRequired
	public String execute()  throws Exception{
		IUser user = UserContext.getCurrentUser();
		if (user == null){
			return "login";
		}
		return super.execute();
	}

	/**
	 * 主页面用户信息栏
	 * @return
	 */
	public String bottom() {
		IUser user = UserContext.getCurrentUser();
		if (user == null){
			return "login";
		}
		currentUserLoginName = ((User)user).getLoginName();
		currentUserDeptName = ((User)user).getEmpCode().getDeptId().getDeptName();
		currentUserName = ((User)user).getEmpCode().getEmpName();
		return SUCCESS;
	}
	
	public String getCurrentUserName() {
		return currentUserName;
	}

	public String getCurrentUserLoginName() {
		return currentUserLoginName;
	}

	public String getCurrentUserDeptName() {
		return currentUserDeptName;
	}
	
}
