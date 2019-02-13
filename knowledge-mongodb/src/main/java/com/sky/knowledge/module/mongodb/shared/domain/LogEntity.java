package com.sky.knowledge.module.mongodb.shared.domain;

import java.util.Date;
/**
 * log实体
 * @description
 * @create xq
 * @date 2014-11-17
 */
public class LogEntity {

	private Date createtime;// 创建时间
	private String module_name;// 模块名称
	private String class_name;// 类名（含包名）
	private String request_void;// 请求方法
	private String interface_name;//接口名称（调用接口时用）
	private String request_user;// 请求者（即方法调用者）
	private String request_data;// 请求参数
	private String response_data;// 请求返回参数
	private String func_description;// 功能描述
	private String exception_info;// 异常信息
	private int is_again_call=0;// 是否重调（0否，1是）
	private int call_count=1;//调用次数
	private String log_level;// 级别（如DEBUG、WARN或者INFO）
	
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public String getModule_name() {
		return module_name;
	}
	public void setModule_name(String module_name) {
		this.module_name = module_name;
	}
	public String getClass_name() {
		return class_name;
	}
	public void setClass_name(String class_name) {
		this.class_name = class_name;
	}
	public String getRequest_void() {
		return request_void;
	}
	public void setRequest_void(String request_void) {
		this.request_void = request_void;
	}
	public String getInterface_name() {
		return interface_name;
	}
	public void setInterface_name(String interface_name) {
		this.interface_name = interface_name;
	}
	public String getRequest_user() {
		return request_user;
	}
	public void setRequest_user(String request_user) {
		this.request_user = request_user;
	}
	public String getRequest_data() {
		return request_data;
	}
	public void setRequest_data(String request_data) {
		this.request_data = request_data;
	}
	public String getResponse_data() {
		return response_data;
	}
	public void setResponse_data(String response_data) {
		this.response_data = response_data;
	}
	public String getFunc_description() {
		return func_description;
	}
	public void setFunc_description(String func_description) {
		this.func_description = func_description;
	}
	public String getException_info() {
		return exception_info;
	}
	public void setException_info(String exception_info) {
		this.exception_info = exception_info;
	}
	public int getIs_again_call() {
		return is_again_call;
	}
	public void setIs_again_call(int is_again_call) {
		this.is_again_call = is_again_call;
	}
	public int getCall_count() {
		return call_count;
	}
	public void setCall_count(int call_count) {
		this.call_count = call_count;
	}
	public String getLog_level() {
		return log_level;
	}
	public void setLog_level(String log_level) {
		this.log_level = log_level;
	}
	
	
}
