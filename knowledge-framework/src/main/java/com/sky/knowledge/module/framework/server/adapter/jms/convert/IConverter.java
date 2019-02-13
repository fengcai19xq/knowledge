package com.sky.knowledge.module.framework.server.adapter.jms.convert;

/**
* Copyright deppon.com.
* All rights reserved.
* Description:   数据转换接口
* HISTORY
*  ID      DATE                PERSON            REASON
********************************************
*  1       2011-3-8             vincent.chen      新增
********************************************
 */
public interface IConverter {

	/**
	 * 传输对象 ---> 报文字符串
	 * 
	 * @param to
	 *            传输对象
	 * @return 报文
	 * @throws ConverterException
	 */
	public String fromObject(Object o) throws ConvertException;

	/**
	 * 报文字符串 ---> 传输对象
	 * 
	 * @param str
	 *            报文
	 * @return
	 * @throws ConverterException
	 */
	public <T> T toObject(String str , Class<T> clazz) throws ConvertException;


}