package com.sky.knowledge.module.framework.server.components.logger.track;

import java.io.Serializable;

import javax.jms.JMSException;

import com.sky.knowledge.module.framework.server.adapter.jms.adapter.AsyncJMSAdapter;
import com.sky.knowledge.module.framework.server.components.logger.LogSender;


public class LogSenderImpl implements LogSender {

	/**
	 * 异步jms发送接口
	 */
    private AsyncJMSAdapter asyncJMSAdapter;

    /**
     * 
     * setAsyncJMSAdapter
     * @param asyncJMSAdapter
     * @return void
     * @since JDK1.6
     */
    public void setAsyncJMSAdapter(AsyncJMSAdapter asyncJMSAdapter) {
        this.asyncJMSAdapter = asyncJMSAdapter;
    }

    /**
     * 使用异步jms消息发送日志
     * @see com.deppon.foss.framework.server.components.logger.track.LogSender#send(java.io.Serializable)
     * send
     * @param msg
     * @throws JMSException
     * @since:0.6
     */
    @Override
	public void send(final Serializable msg) throws JMSException {
        asyncJMSAdapter.send(msg);
	}


}