package com.sky.knowledge.email;
import java.util.List;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

public class SpringMail {

	// 日志记录
	private static Logger logger =LoggerFactory.getLogger(SpringMail.class);

	/**
	 * 功能说明：仅发送简单的文本邮件（不含附件以及html格式）;
	 * @param sender
	 *            邮件发送器
	 * @throws Exception
	 */
	public void sendTextMail(JavaMailSender sender, EmailInfo email) throws Exception {
		SimpleMailMessage mail = new SimpleMailMessage();
		
		//获取接收者信息
		List<String> list = email.getReceiverList();
		
		//接收者如果为空，则不发送
		if(list == null || list.size() == 0) {
			logger.error("邮件接收者为空！");
		} 
		//发送者如果为空，则不发送
		else if(email.getSender() == null || email.getSender().equals("")) {
			logger.error("邮件发送者为空！");
		}
		else {
			//收件人数量
			final int size = email.getReceiverList().size();
			//同时发送多个收件人
			mail.setTo((String[])email.getReceiverList().toArray(new String[size]));
			//设置发件人
			mail.setFrom(email.getSender());
			//设置邮件主题
			mail.setSubject(email.getSubject());
			//设置邮件内容，仅限文本格式
			mail.setText(email.getMessage());
			
			logger.info("开始发送邮件！");
			
			//发送邮件
			sender.send(mail);
			
			logger.info("成功发送文本文件！");
		}
	}

	/**
	 * 功能说明：发送带附件的邮件以及Html格式邮件
	 * author:afei
	 * @param sender
	 *            邮件发送器
	 * @throws Exception
	 */
	public void sendMimeMessage(final JavaMailSender sender, EmailInfo email) throws Exception {
		// 获取JavaMailSender bean
		JavaMailSenderImpl senderImpl = new JavaMailSenderImpl();
		MimeMessage mailMessage = senderImpl.createMimeMessage();
		// 设置utf-8或GBK编码，否则邮件会有乱码
		MimeMessageHelper messageHelper = new MimeMessageHelper(mailMessage,
				true, "utf-8");
		try {
			//收件人数量
			int size = email.getReceiverList().size();
			//设置收件人地址
			messageHelper.setTo((String[])email.getReceiverList().toArray(new String[size]));
			//设置发件人地址
			messageHelper.setFrom(email.getSender());
			//设置邮件主题
			messageHelper.setSubject(email.getSubject());// 主题
			// 邮件内容，注意加参数true
			messageHelper.setText(email.getMessage(),	true);
			
//			List<File> fileList = email.getFileList();
//			
//			//设置邮件附件的格式
//			for(File file: fileList) {
//				// 这里的方法调用和插入图片是不同的，使用MimeUtility.encodeWord()来解决附件名称的中文问题
//				messageHelper.addAttachment(MimeUtility.encodeWord(file.getName()),	file);
//			}

			logger.info("开始发送邮件！");
			
			sender.send(mailMessage);
			
			logger.info("成功发送文本文件！");
		} catch (Exception e) {
			logger.error("邮件发送出现异常{}", e);
			e.printStackTrace();
			throw e ;
		}
	}
	
}
