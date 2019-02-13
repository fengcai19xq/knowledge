package com.sky.knowledge.email;

import java.util.Date;

/**
 * 服务检查问题反馈信息
 * @author afei
 * @date 2013-8-23 上午10:38:44
 * @since
 * @version
 */
public class FinMessage {
	//问题编码
	private String number;
	//报告日期
	private Date reportDate;
	//部门
	private String dept;
	//部门类型
	private int depType;
	//部门经理
	private String deptLeader;
	//改善指定人
	private String director;
	//检查问题
	private String question;
	//指导意见
	private String suggestion;
	//备注描述
	private String description;
	
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public Date getReportDate() {
		return reportDate;
	}
	public void setReportDate(Date reportDate) {
		this.reportDate = reportDate;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public int getDepType() {
		return depType;
	}
	public void setDepType(int depType) {
		this.depType = depType;
	}
	public String getDeptLeader() {
		return deptLeader;
	}
	public void setDeptLeader(String deptLeader) {
		this.deptLeader = deptLeader;
	}
	public String getDirector() {
		return director;
	}
	public void setDirector(String director) {
		this.director = director;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getSuggestion() {
		return suggestion;
	}
	public void setSuggestion(String suggestion) {
		this.suggestion = suggestion;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
