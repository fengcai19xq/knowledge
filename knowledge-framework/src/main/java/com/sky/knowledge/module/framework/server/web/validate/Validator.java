package com.sky.knowledge.module.framework.server.web.validate;

import java.lang.annotation.Annotation;

import org.springframework.context.ApplicationContext;


/**
*******************************************
* <b style="font-family:微软雅黑"><small>Description:数据校验接口</small></b>   </br>
* <b style="font-family:微软雅黑"><small>HISTORY</small></b></br>
* <b style="font-family:微软雅黑"><small> ID      DATE    PERSON     REASON</small></b><br>
********************************************
* <div style="font-family:微软雅黑,font-size:70%"> 
* 
* </div>  
********************************************
 */
public interface Validator {
	
	/**
	 * 验证数据是否符合约束规则，
	 * validate
	 * @param an 校验的注解
	 * @param Obj 要进行校验的对象
	 * @return
	 * @throws 不符合约束将抛出ValidationException
	 * @return Validator
	 * @since:
	 */
	public Validator validate(Annotation an, Object Obj) throws ValidationException;
	
	/**
	 * 国际化信息初始化方法
	 * initMessageSource
	 * @param contex 通过ApplicationContext得到国际化信息的绑定对象
	 * @return void
	 * @since:
	 */
    public void initMessageSource(ApplicationContext contex);
}
