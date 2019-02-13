package com.sky.knowledge.module.framework.server.components.logger.track;

import java.io.Serializable;
import java.util.List;

import com.sky.knowledge.module.framework.server.components.logger.LogSender;


/**
*******************************************
* Copyright deppon.com.
* All rights reserved.
* Description:   日志发送任务
* HISTORY
*  ID      DATE                PERSON            REASON
********************************************
*  1       2011-4-8             周丹枫      新增
********************************************
 */
public class SendTask implements Runnable {
	@SuppressWarnings("rawtypes")
	private List list;

	private LogSender sender;
	
	/**
	 * 
	  * 创建一个新的实例 SendTask.
	  * @param list 需要发送的list
	  * @param sender 日志发送接口
	  * @since JDK1.6
	 */
	public SendTask(@SuppressWarnings("rawtypes") List list, LogSender sender) {
		this.list = list;
		this.sender = sender;
	}
	
	/**
	 * 调用发送日志接口，发送日志
	 * @see java.lang.Runnable#run()
	 * run
	 * @since JDK1.6
	 */
	@Override
	public void run() {
		try {
			sender.send((Serializable)list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}