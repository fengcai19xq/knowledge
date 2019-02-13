package com.sky.knowledge.module.framework.server.deploy;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.sky.knowledge.module.framework.server.context.AppContext;

public class AppContextListener implements ServletContextListener{

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		ServletContext sc = sce.getServletContext();
		String servletContextName = sc.getServletContextName();
		String staticServerAddress = sc.getInitParameter("staticServerAddress");
		AppContext.initAppContext(servletContextName, staticServerAddress);
	}

}
