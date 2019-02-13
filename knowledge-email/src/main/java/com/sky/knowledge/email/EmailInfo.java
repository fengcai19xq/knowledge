package com.sky.knowledge.email;
import java.io.File;
import java.util.List;

/***
 * 
 * 邮件发送信息
 * <p style="display:none">modifyRecord</p>
 * <p style="display:none">version:V1.0,author:afei,date:2013-8-21 下午7:23:53,content:TODO </p>
 * @author afei
 * @date 2013-8-21 下午7:23:53
 * @since
 * @version
 */
public class EmailInfo {
	//邮件接受者
	private List<String> receiverList;
	//邮件发送者
	private String sender;
	//邮件内容
	private String message;
	//附件
	private List<File> fileList;
	//主题
	private String subject;
	
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public List<String> getReceiverList() {
		return receiverList;
	}
	public void setReceiverList(List<String> receiverList) {
		this.receiverList = receiverList;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public List<File> getFileList() {
		return fileList;
	}
	public void setFileList(List<File> fileList) {
		this.fileList = fileList;
	}
}
