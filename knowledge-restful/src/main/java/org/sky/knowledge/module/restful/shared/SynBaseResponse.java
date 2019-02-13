/**
 * Project Name:FIMS-Interfaces
 * File Name:SynBaseResponse.java
 * Package Name:com.deppon.fims.inter.demo.domian
 * Date:2014-10-10下午3:01:26
 * Copyright (c) 2014 All Rights Reserved.
 */

package org.sky.knowledge.module.restful.shared;

import java.io.Serializable;

/**
 * @ClassName:SynBaseResponse <br/>
 * @Title: TODO 反馈基础类. <br/>
 * @author: 215209
 * @Date: 2014-10-10 下午3:01:26
 */
public class SynBaseResponse implements Serializable {

	/**
	 * serialVersionUID:(用一句话描述这个变量表示什么).
	 */
	private static final long serialVersionUID = -5784468242729619916L;
	/**
	 * 是否成功1/0
	 */
	private String isSuccess;
	/**
	 * 失败原因
	 */
	private String faileReason;
	
	public String getIsSuccess() {
		return isSuccess;
	}
	public void setIsSuccess(String isSuccess) {
		this.isSuccess = isSuccess;
	}
	public String getFaileReason() {
		return faileReason;
	}
	public void setFaileReason(String faileReason) {
		this.faileReason = faileReason;
	}

}
