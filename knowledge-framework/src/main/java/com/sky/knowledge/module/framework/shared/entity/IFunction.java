package com.sky.knowledge.module.framework.shared.entity;


/**
 * 
 * 
*******************************************
* <b style="font-family:微软雅黑"><small>Description:功能接口定义</small></b>   </br>
* <b style="font-family:微软雅黑"><small>HISTORY</small></b></br>
* <b style="font-family:微软雅黑"><small> ID      DATE    PERSON     REASON</small></b><br>
********************************************
* <div style="font-family:微软雅黑,font-size:70%"> 
* 1 2011-4-9 stven.cheng 新增
* </div>  
********************************************
 */
public interface IFunction extends IEntity{
	/**
	 * 
	 * @Title:getModule
	 * @Description: FOSS I的设计方案，在用户功能下有一个功能模块
	 * 在后来的完善中，过滤掉了此设计方案
	 * @param @return
	 * @return IModule
	 * @throws
	 */
	@Deprecated
	IModule getModule();
	
	/**
	 * 
	 * @Title:getUri
	 * @Description:用户功能菜单的href
	 * @param @return
	 * @return String
	 * @throws
	 */
	String getUri();
	/**
	 * 
	 * @Title:getKey
	 * @Description:功能菜单的id
	 * @param @return
	 * @return String
	 * @throws
	 */
	String getKey();
	/**
	 * 
	 * @Title:getFunctionCode
	 * @Description:功能菜单的的代码号：code
	 * @param @return
	 * @return String
	 * @throws
	 */
	String getFunctionCode();
	
	/**
	 * 功能是否被启用
	 * getValidFlag
	 * @return
	 * @return Boolean
	 * @since:0.6
	 */
	Boolean getValidFlag();

}