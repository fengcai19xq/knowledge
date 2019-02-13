package com.sky.knowledge.module.framework.server.web.exttag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.sky.knowledge.module.framework.server.web.message.MessageBundle;


/**
 * 
 * 
*******************************************
* <b style="font-family:微软雅黑"><small>Description:获得指定键的国际化信息，并返回封装好的javascript对象</small></b>   </br>
* <b style="font-family:微软雅黑"><small>HISTORY</small></b></br>
* <b style="font-family:微软雅黑"><small> ID      DATE    PERSON     REASON</small></b><br>
********************************************
* <div style="font-family:微软雅黑,font-size:70%"> 
* 1 2011-5-10 陈兴波 新增
* </div>  
********************************************
 */
public class I18nTag extends SimpleTagSupport {
	/**
	 * 国际化的键组成的字符串
	 */
	private String keys;
	
	/**
	 * 国际化资源接口实现类 对象
	 */
	private MessageBundle messageBundle;

	/**
	 * SimpleTagSupport标签执行，调用的主方法
	 * 调用国际化资源接口，遍历传入的国际化键的字符串，查找出每一个键对应的国际化信息，将键和国际化信息封装成一个javascript对象，放入到script 标签中
	 * @see javax.servlet.jsp.tagext.SimpleTagSupport#doTag()
	 * doTag
	 * @throws JspException
	 * @throws IOException
	 * @since JDK1.6
	 */
	@Override
	public void doTag() throws JspException, IOException {
		if (this.getKeys() == null || "".equals(this.getKeys())) {
			return;
		}
		messageBundle = new MessageBundle();
		StringBuilder msgObject = new StringBuilder("");
		msgObject.append("<script type='text/javascript'> \n");
		// 声明一个function，用于取message
		msgObject.append("	function i18n(key, args) { \n");
		// 声明一个对象，存放message信息
		msgObject.append("msg = {");
		String[] keyArray = parseKeys(keys);
		for (String key : keyArray) {
			String message = messageBundle.getMessage(key);
			if (message != null && !"".equals(message)) {
				msgObject.append("'"+key +"'"+ ":'" + message + "',");
			}
		}
		msgObject.deleteCharAt(msgObject.lastIndexOf(","));
		msgObject.append("};");

		msgObject.append("\n");
		msgObject.append("var message = msg[ key] ; \n");
		msgObject.append("if(args != null){  \n");
		msgObject.append("for ( var i = 0; i < args.length; i++) { \n");
		msgObject.append("var reg ='{'+i+'}'; \n");
		msgObject
				.append("message = message.toString().replace(reg, args[i]); \n");
		msgObject.append("} \n");
		msgObject.append("} \n");
		msgObject.append("return message; \n");
		msgObject.append("} \n");
		msgObject.append("</script>");
		getJspContext().getOut().write(msgObject.toString());
	}

	/**
	 * 获得 国家化键组成的字符串
	 * getKeys
	 * @return
	 * @return String
	 * @since JDK1.6
	 */
	public String getKeys() {
		return keys;
	}

	/**
	 * 设置国际化键组成的字符串
	 * setKeys
	 * @param keys
	 * @return void
	 * @since JDK1.6
	 */
	public void setKeys(String keys) {
		this.keys = keys;
	}
	
	/**
	 * 解析字符串，获得key组成的数组
	 * parseKeys
	 * @param keys
	 * @return
	 * @return String[]
	 * @since JDK1.6
	 */
	private String[] parseKeys(String keys){
		keys = keys.replaceAll("\n", "");
		keys = keys.replaceAll("\r", "");
		String []keyArray = keys.split(",");
		for(int i=0;i<keyArray.length;i++)
		{
			keyArray[i] = keyArray[i].trim();
		}
		return keyArray;
	}
}