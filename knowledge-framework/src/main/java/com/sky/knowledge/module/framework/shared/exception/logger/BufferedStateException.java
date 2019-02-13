package com.sky.knowledge.module.framework.shared.exception.logger;

import com.sky.knowledge.module.framework.shared.exception.GeneralException;

/**
*******************************************
* Copyright deppon.com.
* All rights reserved.
* Description:   日志缓冲状态异常
* HISTORY
*  ID      DATE                PERSON            REASON
********************************************
*  1       2011-4-8             周丹枫      新增
********************************************
 */
public class BufferedStateException extends GeneralException{
	/**
     * 
     */
    private static final long serialVersionUID = 5002210385764063432L;

    public BufferedStateException(String str) {
		super(str);
	}
}