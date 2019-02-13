package com.sky.knowledge.email;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import junit.framework.TestCase;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import freemarker.template.Template;

public class SpingMailTest extends TestCase {
	public SpingMailTest(){
		
	}
	private static JavaMailSender sender = null;
	private static SpringMail springMail = null;
	// 使用FreeMarker模板
	private static FreeMarkerConfigurer freeMarkerConfigurer = null;
	
	//应用自动加载
	static {
		ApplicationContext ctx = new FileSystemXmlApplicationContext(
				"classpath:config/applicationContext-mail.xml");
		//邮件发送获取
		SpingMailTest.sender = (JavaMailSender) ctx.getBean("mailSender");
		//模板配置
		SpingMailTest.freeMarkerConfigurer = (FreeMarkerConfigurer)ctx.getBean("freeMarker");
		
		SpingMailTest.springMail = new SpringMail(); 
	}
	
	/**
	 * 
	 *简单的文本发送 
	 * @author afei
	 * @date 2013-8-22 上午11:44:58
	 * @see
	 */
//	public void testSendTxtMail() {
//		//邮件信息
//		EmailInfo email = new EmailInfo();
//		email.setMessage("helloworld");
//		email.setSender("dpfindev@deppon.com");
//		email.setSubject("test");
//		
//		//邮件接收者
//		List<String> relist = new ArrayList<String>();
//		email.setReceiverList(relist);
//		relist.add("xuqian003@deppon.com");
////		relist.add("fengcai19xq@163.com");
//		
//		try {
//			springMail.sendTextMail(sender, email);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//	
	/**
	 * 
	 *发送带附件的邮件
	 * @author afei
	 * @date 2013-8-22 下午4:56:01
	 * @see
	 */
//	public void testMimeMail() { 
//		//邮件信息
//		EmailInfo email = new EmailInfo();
//		email.setMessage("<html><head></head><body><h1>hello!!带附件邮件</h1></body></html>");
//		email.setSender("dplf@deppon.com");
//		email.setSubject("test");
//		
//		//邮件接收者
//		List<String> relist = new ArrayList<String>();
//		email.setReceiverList(relist);
//		relist.add("xuqian003@deppon.com");
//		relist.add("fengcai19xq@163.com");
//		
//		List<File> fileList = new ArrayList<File>();
//		File file = new File("d:/新建文本文档.txt");
//		fileList.add(file);
//		
//		email.setFileList(fileList);
//		
//		try {
//			springMail.sendMimeMessage(sender, email);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//	
	/**
	 * 使用模板发送动态邮件
	 * @author afei
	 * @date 2013-8-23 下午2:05:02
	 * @see
	 */
	@Test
	public void testpm() {
		List<FinMessage> msgList = new ArrayList<FinMessage>();
		FinMessage message = new FinMessage();
		message.setDepType(1);
		message.setDept("徐泾营业部");
		message.setDeptLeader("张三");
		message.setDescription("部门存在一笔未认领的汇款！");
		message.setDirector("李四");
		message.setNumber("001");
		message.setQuestion("部门存在一笔未认领的汇款！");
		message.setReportDate(new Date());
		message.setSuggestion("及时认领");
		
		msgList.add(message);
		
		String m = this.getMailText(msgList);
		
		//邮件信息
		EmailInfo email = new EmailInfo();
		email.setMessage(m);
		email.setSender("dpfindev@deppon.com");
		email.setSubject("test");
		
		//邮件接收者
		List<String> relist = new ArrayList<String>();
		email.setReceiverList(relist);
		relist.add("xuqian003@deppon.com");
//		relist.add("fengcai19xq@163.com");
		
//		List<File> fileList = new ArrayList<File>();
//		File file = new File("d:/新建文本文档.txt");
//		fileList.add(file);
//		
//		email.setFileList(fileList);
		
		try {
			springMail.sendMimeMessage(sender, email);
		} catch (Exception e) {
			e.printStackTrace();
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
	private String getMailText(List<FinMessage> messageList) {
		String htmlText = "";
		try {
			// 通过指定模板名获取FreeMarker模板实例
			Template tpl = freeMarkerConfigurer.getConfiguration().getTemplate(
					"mailtemp.ftl");
			// FreeMarker通过Map传递动态数据
			Map<String, List<FinMessage>> map = new HashMap<String, List<FinMessage>>();

			// 注意动态数据的key和模板标签中指定的属性相匹配
			map.put("messageList", messageList);

			// 解析模板并替换动态数据，最终messageList将替换模板文件中的${messageList}标签。
			htmlText = FreeMarkerTemplateUtils.processTemplateIntoString(tpl,
					map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return htmlText;
	}
	
}
