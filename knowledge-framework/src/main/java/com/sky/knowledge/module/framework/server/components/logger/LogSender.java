package com.sky.knowledge.module.framework.server.components.logger;

import java.io.Serializable;

import javax.jms.JMSException;


public interface LogSender {
	
	/**
	 * 
	 *@author GaryHe
	 *@date 2011-1-18 下午06:44:54
	 *@description  消息发送接口
	 *@param msg 发送消息内容
	 */
	public void send(Serializable msg)throws JMSException;
	

}