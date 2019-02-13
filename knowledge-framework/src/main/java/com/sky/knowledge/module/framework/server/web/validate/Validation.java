package com.sky.knowledge.module.framework.server.web.validate;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;


/**
*******************************************
* <b style="font-family:微软雅黑"><small>Description:校验管理器</small></b>   </br>
* <b style="font-family:微软雅黑"><small>HISTORY</small></b></br>
* <b style="font-family:微软雅黑"><small> ID      DATE    PERSON     REASON</small></b><br>
********************************************
* <div style="font-family:微软雅黑,font-size:70%"> 
* 
* </div>  
********************************************
 */
public final class Validation {

	//用于存放已经实例化的校验器
	private static ConcurrentMap<String, Validator> validators;

	private static Validation validation = new Validation();

	private Validation() {
	}

	/**
	 * 得到校验管理器
	 * getValidation
	 * @return
	 * @return Validation
	 * @since:
	 */
	public static Validation getValidation() {
		validators = new ConcurrentHashMap<String, Validator>();
		return validation;
	}

	/**
	 * 存放校验器
	 * put
	 * @param validatorName
	 * @param validator
	 * @return void
	 * @since:
	 */
	@SuppressWarnings("static-access")
	public void put(String validatorName, Validator validator) {
		this.validators.put(validatorName, validator);
	}

	/**
	 * 得到校验器
	 * get
	 * @param validatorName
	 * @return
	 * @return Validator
	 * @since:
	 */
	@SuppressWarnings("static-access")
	public Validator get(String validatorName) {
		return this.validators.get(validatorName);
	}
}
