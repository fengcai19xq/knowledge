package com.sky.knowledge.module.framework.server.web.exttag;

import java.io.IOException;

import com.sky.knowledge.module.framework.server.web.message.MessageBundle;


/**
 * @Classname:MessageTag
 * @Description:处理国际化信息的tag
 * @author sifer chuanguo.xie@achievo.com
 * @date 2011-3-28  下午04:57:11
 * 
 */
public class I18nTagMsg extends AbstractTagSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6786608763089330004L;
	//国际化信息key
	private String key;
	//国际化信息bean，为messageBundle
	private String messageBean = "messageBundle";
	
	public String getKey() {
		return key;
	}


	public void setKey(String key) {
		this.key = key;
	}


	public int doStartTag(){
		
		try {
			pageContext.getOut().write(((MessageBundle)getBean(messageBean)).getMessage(key));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SKIP_BODY;
	}
}