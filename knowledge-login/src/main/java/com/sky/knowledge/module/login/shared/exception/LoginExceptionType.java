package com.sky.knowledge.module.login.shared.exception;

/**
 * 密码异常类型枚举
 */
public enum LoginExceptionType {

	/**
	 * 登录密码不能为空
	 */
	LoginPasswordIsNull,
	
	/**
	 * 登录密码错误
	 */
	LoginPasswordIsError,
	
	/**
	 * 该用户不存在
	 */
	UserIsNull,
	
	/**
	 * 当前用户已经被禁用
	 */
	UserIsDisable,
	
	/**
	 * 用户名不能为空
	 */
	UserNameIsNull
}
