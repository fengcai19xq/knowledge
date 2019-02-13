package com.sky.knowledge.module.framework.server.context;

public class AppContext {

	/**
	 * 应用名
	 */
	private final String appName;
	
	/**
	 * 静态资源地址
	 */
	private final String staticServerAddress;
	
	public String getAppName() {
		return appName;
	}
	
	public String getStaticServerAddress() {
		return staticServerAddress;
	}
	
	public AppContext(String appName, String staticServerAddress) {
		this.appName = appName;
		this.staticServerAddress = staticServerAddress;
	}
	
	private static AppContext context;
	
	public static void initAppContext(String appName, String staticServerAddress) {
		if (context == null)
			context = new AppContext(appName, staticServerAddress) {};
	}
	
	public static AppContext getAppContext() {
		return context;
	}
}
