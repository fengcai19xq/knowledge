package com.sky.knowledge.email;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import freemarker.template.Template;

/**
 * 发送邮件工具类
 * @author xq
 *
 */
public class SendMailUtil {

	private static Log logger = LogFactory.getLog(SendMailUtil.class);

	private   JavaMailSender sender = null;
	// 使用FreeMarker模板
	private   FreeMarkerConfigurer freeMarkerConfigurer = null;
	
	private   SpringMail springMail = null  ;
	//应用自动加载
	 {
//		ApplicationContext ctx = new  ClassPathXmlApplicationContext(
//				"applicationContext-mail.xml");
		 ApplicationContext ctx = new FileSystemXmlApplicationContext(
					"classpath:config/applicationContext-mail.xml");
		//邮件发送获取
		sender = (JavaMailSender) ctx.getBean("mailSender");
		//模板配置
		freeMarkerConfigurer = (FreeMarkerConfigurer)ctx.getBean("freeMarker");
		
		springMail = new SpringMail(); 
	}
	/**
	 * 初始化实体对象
	 * @description
	 * @create xq
	 * @date 2013-10-14  
	 * @return
	 */
	public  static SendMailUtil initSendMailUtil(){
		return new SendMailUtil();
	}
	
	/**
	 * 使用模板发送动态邮件
	 * @author afei
	 * @date 2013-8-23 下午2:05:02
	 * @see
	 */
	public  void sendDynamicMail(List<Object> list ,String theme,List<String> receivePerson)throws Exception {
	
		String m = getMailText(list);
		//邮件信息
		EmailInfo email = new EmailInfo();
		email.setMessage(m);
		email.setSender("dpfindev@deppon.com");
		email.setSubject(theme);
		
		//邮件接收者
		email.setReceiverList(receivePerson);
		
		try {
			springMail.sendMimeMessage(sender, email);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("发送邮件失败!");
			throw e ;
		}
	}
	
	/**
	 * 输出动态的邮件内容
	 * @author afei
	 * @date 2013-8-23 下午12:39:59
	 * @param messageList
	 * @return
	 * @see
	 */
	private  String getMailText(List<Object> messageList)throws Exception  {
		String htmlText = "";
		try {
			// 通过指定模板名获取FreeMarker模板实例
			Template tpl = freeMarkerConfigurer.getConfiguration().getTemplate(
					"mailtemp.ftl");
			// FreeMarker通过Map传递动态数据
			Map<String, List<Object>> map = new HashMap<String, List<Object>>();

			// 注意动态数据的key和模板标签中指定的属性相匹配
			map.put("messageList", messageList);

			// 解析模板并替换动态数据，最终messageList将替换模板文件中的${messageList}标签。
			htmlText = FreeMarkerTemplateUtils.processTemplateIntoString(tpl,
					map);
		} catch (Exception e) {
			e.printStackTrace();
			throw e ;
		}
		return htmlText;
	}
}
