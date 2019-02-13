package com.sky.knowledge.module.framework.server.adapter.jms.adapter;

import java.util.Map;
import java.util.Set;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Queue;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessagePostProcessor;

import com.sky.knowledge.module.framework.server.adapter.jms.ISGAdapter;
import com.sky.knowledge.module.framework.server.adapter.jms.SGException;
import com.sky.knowledge.module.framework.server.adapter.jms.convert.IConverter;


/**
* Copyright deppon.com.
* All rights reserved.
* Description:   消息异步发送Adapter
* HISTORY
*  ID      DATE                PERSON            REASON
********************************************
*  1       2011-3-8             vincent.chen      新增
********************************************
 */
public class AsyncJMSAdapter implements ISGAdapter {

	// 由Spring注入
	private JmsTemplate jmsTemplate;
	// 发送队列，由Spring注入
	private Queue sendQueue;
	// 数据转换器，由Spring注入
	
	@SuppressWarnings("rawtypes")
	private IConverter converter;

	/**
	 * 服务处理 发送消息到sendQueue队列
	 */
	@SuppressWarnings("unchecked")
	public Object send(Object to) throws SGException {
		String xml = converter.fromObject(to);
		jmsTemplate.convertAndSend(sendQueue, xml);
		return null;
	}

	/**
	 * 服务处理 设置关联ID，发送消息到sendQueue队列
	 */
	public Object send(Object to, final String correlationID)
			throws SGException {
		return this.send(to, correlationID, null);
	}

	/**
	 * 服务处理 设置消息头属性，发送消息到sendQueue队列
	 */
	public Object send(Object to, final Map<String, ?> header)
			throws SGException {
		return this.send(to, null, header);
	}

	/**
	 * 服务处理 设置消息的关联ID，消息头属性，发送消息到sendQueue
	 */
	@SuppressWarnings("unchecked")
	public Object send(Object to, final String correlationID, final Map<String, ?> header) throws SGException {
		String xml = converter.fromObject(to);
		jmsTemplate.convertAndSend(sendQueue, xml, new MessagePostProcessor() {
			public Message postProcessMessage(Message message)
					throws JMSException {
				if (header != null && !header.isEmpty()) {
					Set<String> keys = header.keySet();
					for (String key : keys) {
						message.setObjectProperty(key, header.get(key));
					}
				}
				message.setJMSCorrelationID(correlationID);
				return message;
			}
		});
		return null;
	}

	public void setJmsTemplate(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}

	public void setSendQueue(Queue sendQueue) {
		this.sendQueue = sendQueue;
	}

	public void setConverter(@SuppressWarnings("rawtypes") IConverter converter) {
		this.converter = converter;
	}

	@SuppressWarnings("rawtypes")
	public IConverter getConverter() {
		return this.converter;
	}
}
