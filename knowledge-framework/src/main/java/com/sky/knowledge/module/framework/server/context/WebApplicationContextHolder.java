package com.sky.knowledge.module.framework.server.context;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

/**
 * 加载Spring配置文件时，如果Spring配置文件中所定义的Bean类实现了ApplicationContextAware 接口，那么在加载Spring配置文件时，会自动调用ApplicationContextAware 接口中的
 * @description
 * @create xq
 * @date 2014-6-9
 */
@Component
public class WebApplicationContextHolder implements ApplicationContextAware {
	
	private static ApplicationContext context;
	
	/**
	 * 注入WebApplicationContext
	 * @see org.springframework.context.ApplicationContextAware#setApplicationContext(org.springframework.context.ApplicationContext)
	 * setApplicationContext
	 * @param applicationContext
	 * @throws BeansException
	 * @since:0.9
	 */
	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		context = applicationContext;
	}
	
	public static WebApplicationContext getWebApplicationContext() {
		return (WebApplicationContext)context;
	}

}
