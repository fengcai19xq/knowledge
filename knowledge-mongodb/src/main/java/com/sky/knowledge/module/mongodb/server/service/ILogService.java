package com.sky.knowledge.module.mongodb.server.service;

public interface ILogService {

	/**
	 * @功能描述 
	 * @create xq
	 * @date 2014-11-17
	 * @param class1 当前所在的class
	 * @param request_void 请求方法
	 * @param request_data 请求参数
	 * @param func_description 功能描述
	 * @param e 异常信息
	 * @return boolean
	 */
	public boolean debug(Class class1,String request_void, String request_data,
			String func_description, Exception e);
}
