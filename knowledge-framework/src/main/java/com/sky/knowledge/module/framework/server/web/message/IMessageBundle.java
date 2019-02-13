package com.sky.knowledge.module.framework.server.web.message;

import java.util.Locale;

/**
 * 
 * 
*******************************************
* <b style="font-family:微软雅黑"><small>Description:国际化资源接口</small></b>   </br>
* <b style="font-family:微软雅黑"><small>HISTORY</small></b></br>
* <b style="font-family:微软雅黑"><small> ID      DATE    PERSON     REASON</small></b><br>
********************************************
* <div style="font-family:微软雅黑,font-size:70%"> 
* 1 2011-3-27 liangfei 新增
* </div>  
********************************************
 */
public interface IMessageBundle {
	/**
	 * 根据键取得国际化资源，并格式化
	 * getMessage
	 * @param key 
	 * @param args 格式化参数 
	 * @return
	 * @return String
	 * @since:0.6
	 */
	String getMessage(String key, Object... args);
	
	/**
	 * 取得指定地区的国际化资源
	 * getMessage
	 * @param locale
	 * @param key
	 * @param args 格式化参数
	 * @return
	 * @return String
	 * @since:0.6
	 */
	String getMessage(Locale locale, String key, Object... args);
	
	/**
	 * 取得动态国际化资源并格式化
	 * getDynamicMessage
	 * @param key
	 * @param args 格式化参数
	 * @return
	 * @return String
	 * @since:0.6
	 */
	String getDynamicMessage(String key, Object... args);
	
	/**
	 * 取得指定地区的动态国际化资源
	 * getDynamicMessage
	 * @param locale
	 * @param key
	 * @param args 格式化参数
	 * @return
	 * @return String
	 * @since:0.6
	 */
	String getDynamicMessage(Locale locale ,String key ,Object ... args  );

}
