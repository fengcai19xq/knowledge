package com.sky.knowledge.module.login.server.action;

import javax.annotation.Resource;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sky.knowledge.module.framework.server.web.action.AbstractAction;
import com.sky.knowledge.module.login.server.service.ILoginService;


@Scope(BeanDefinition.SCOPE_PROTOTYPE)
@Controller
public class LogoutAction extends AbstractAction{

	private static final long serialVersionUID = 4776710729717441838L;
	
	//注入loginService
	@Resource
	private ILoginService loginService;
	
	/**
	 * 退出系统
	 */
	@Override
	public String execute() throws Exception {
		loginService.userLogout();
		return super.execute();
	}
	
}