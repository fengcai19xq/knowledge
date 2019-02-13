package com.sky.knowledge.module.framework.server.adapter.jms;

import com.sky.knowledge.module.framework.shared.exception.GeneralException;

/**
* Copyright deppon.com.
* All rights reserved.
* Description:   服务网关异常基础类
* HISTORY
*  ID      DATE                PERSON            REASON
********************************************
*  1       2011-3-8             vincent.chen      新增
********************************************
 */
public class SGException extends GeneralException {

	private static final long serialVersionUID = -8184025670038174183L;
	
	public SGException(String message) {
		super(message);
	}

	public SGException(String message, Throwable cause) {
		super(message, cause);
	}

	public SGException(Throwable cause) {
		super(cause);
	}

}