package com.sky.knowledge.module.login.shared.exception;

import com.sky.knowledge.module.framework.shared.exception.security.UserNotLoginException;

public class LoginException extends UserNotLoginException{

private static final long serialVersionUID = -4375232641764945199L;
	
	private static final String USER_NAME_IS_ERROR_CODE = "error.module.login.UserNameIsNullException";
	
	private static final String LOGIN_PASSWORD_IS_ERROR_CODE = "error.module.login.LoginPasswordIsErrorException";
	
	private static final String LOGIN_PASSWORD_IS_NULL_ERROR_CODE="error.module.login.LoginPasswordIsNullException";
	
	private static final String USER_IS_NULL_ERROR_CODE="error.module.login.UserIsNullException";
	
	private static final String USER_IS_DISABLE_ERROR_CODE="error.module.login.UserIsDisableException";
	
	public LoginException(LoginExceptionType errorType){
		super();
		if(errorType.equals(LoginExceptionType.UserNameIsNull)){
			this.errCode = USER_NAME_IS_ERROR_CODE;
		}
		if(errorType.equals(LoginExceptionType.LoginPasswordIsError)){
			this.errCode = LOGIN_PASSWORD_IS_ERROR_CODE;
		}
		if(errorType.equals(LoginExceptionType.LoginPasswordIsNull)){
			this.errCode = LOGIN_PASSWORD_IS_NULL_ERROR_CODE;
		}
		if(errorType.equals(LoginExceptionType.UserIsNull)){
			this.errCode = USER_IS_NULL_ERROR_CODE;
		}
		if(errorType.equals(LoginExceptionType.UserIsDisable)){
			this.errCode = USER_IS_DISABLE_ERROR_CODE;
		}
	}
}
