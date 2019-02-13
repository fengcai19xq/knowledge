package com.sky.knowledge.module.framework.shared.exception.security;

import com.sky.knowledge.module.framework.shared.exception.GeneralException;

public class EncodeMd5Exception extends GeneralException{

	private static final long serialVersionUID = 6829001589862824918L;
	
	public static final String ERROR_CODE = "errror.common.passwordEncodeMd5Code.failure";

	public EncodeMd5Exception(Throwable t) {
		super(t);
		this.errCode = "errror.common.passwordEncodeMd5Code.failure";
	}

	public EncodeMd5Exception() {
	}
}
