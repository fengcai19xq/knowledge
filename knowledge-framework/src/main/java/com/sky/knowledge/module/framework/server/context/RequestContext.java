package com.sky.knowledge.module.framework.server.context;

public class RequestContext {

	private static ThreadLocal<RequestContext> context = new ThreadLocal<RequestContext>(){
		@Override
		protected RequestContext initialValue(){
			return new RequestContext();
		}
	};
	//远程调用请求的方法名称和url
	private String remoteReqMethod;
	private String remoteReqURL;
	//请求的模块名称
	private String moduleName;
	
	public String getModuleName() {
		return moduleName;
	}

	private RequestContext(){
		
	}
	
	public String getRemoteRequestMethod() {
		return this.remoteReqMethod;
	}

	public String getRemoteRequestURL() {
		return this.remoteReqURL;
	}
	
	/**
	 * 清除请求信息
	 * clear
	 * @return void
	 * @since:
	 */
	public void clear(){
		this.remoteReqMethod=null;
		this.remoteReqURL=null;
		this.moduleName=null;
	}

	/**
	 * 
	 * @return
	 */
	public static RequestContext getCurrentContext() {
		return context.get();

	}

	/**
	 * 
	 * @param remoteReqMethod
	 * @param remoteReqURL
	 */
	public static void setCurrentContext(String remoteReqMethod,
			String remoteReqURL) {
		RequestContext requestContext = getCurrentContext();
		requestContext.remoteReqMethod = remoteReqMethod;
		requestContext.remoteReqURL = remoteReqURL;
	}
	/**
	 * 
	 * @param moduleName
	 */
	public static void setCurrentContext(String moduleName) {
		RequestContext requestContext = getCurrentContext();
		requestContext.moduleName = moduleName;
	}
	
	/**
	 * 清楚ThreadLocal
	 * remove
	 * @return void
	 * @since:
	 */
	public static void remove(){	
		RequestContext requestContext = getCurrentContext();
		requestContext.clear();
	}
}
