package com.sky.knowledge.module.framework.shared.exception.security;

import com.sky.knowledge.module.framework.shared.exception.GeneralException;


/**
 * 
 * 
*******************************************
* <b style="font-family:微软雅黑"><small>Description:访问拒绝异常</small></b>   </br>
* <b style="font-family:微软雅黑"><small>HISTORY</small></b></br>
* <b style="font-family:微软雅黑"><small> ID      DATE    PERSON     REASON</small></b><br>
********************************************
* <div style="font-family:微软雅黑,font-size:70%"> 
* 1 2011-4-19 樊斌 新增
* </div>  
********************************************
 */
public class AccessNotAllowException extends GeneralException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5710513168282003818L;

	public AccessNotAllowException() {
		this("Method not allow access");
	}

	private AccessNotAllowException(String msg) {
		super(msg);
	}

	@Override
	public String getErrorCode() {
		return "ERROR.SECURITY.NOTALLOW";
	}

}