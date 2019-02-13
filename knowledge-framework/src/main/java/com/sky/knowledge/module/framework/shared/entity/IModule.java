package com.sky.knowledge.module.framework.shared.entity;

import java.util.List;


/**
 * 
 * 
*******************************************
* <b style="font-family:微软雅黑"><small>Description:模块接口定义</small></b>   </br>
* <b style="font-family:微软雅黑"><small>HISTORY</small></b></br>
* <b style="font-family:微软雅黑"><small> ID      DATE    PERSON     REASON</small></b><br>
********************************************
* <div style="font-family:微软雅黑,font-size:70%"> 
* 1 2011-4-9 stven.cheng 新增
* </div>  
********************************************
 */
public interface IModule extends IEntity {
	
	List<IFunction>	 getFunctions();

}
