package com.sky.knowledge.module.example.shared.domain;

import java.util.Date;

import com.sky.knowledge.module.framework.shared.entity.BaseEntity;

/**
 * demo实体
 * @description
 * @create xq
 * @date 2014-10-17
 */
public class DemoEntity extends BaseEntity {
	/**
	 * 编号
	 */
	private String billnum;
	/**
	 * 姓名
	 */
	private String name;
	/**
	 * 性
	 */
	private String surnname;
	/**
	 * 名
	 */
	private String lastname;
	/**
	 * 收款银行
	 */
	private String remitbank;
	/**
	 * 收款日期
	 */
	private Date remitdate;
	/**
	 * 交账状态
	 */
	private String handoverstatus;
	/**
	 * 收款金额
	 */
	private Double money;
	private Date dcStartDate ;
	private Date dcEndDate ;
	private String arrayParams [] ;
	
	
	public String[] getArrayParams() {
		return arrayParams;
	}

	public void setArrayParams(String[] arrayParams) {
		this.arrayParams = arrayParams;
	}

	public Date getDcStartDate() {
		return dcStartDate;
	}

	public void setDcStartDate(Date dcStartDate) {
		this.dcStartDate = dcStartDate;
	}

	public Date getDcEndDate() {
		return dcEndDate;
	}

	public void setDcEndDate(Date dcEndDate) {
		this.dcEndDate = dcEndDate;
	}

	public String getBillnum() {
		return this.billnum;
	}
		 	
	public void setBillnum(String billnum) {
		this.billnum = billnum;
	}
		
	public String getName() {
		return this.name;
	}
		 	
	public void setName(String name) {
		this.name = name;
	}
		
	public String getRemitbank() {
		return this.remitbank;
	}
		 	
	public void setRemitbank(String remitbank) {
		this.remitbank = remitbank;
	}
		
	public Date getRemitdate() {
		return this.remitdate;
	}
		 	
	public void setRemitdate(Date remitdate) {
		this.remitdate = remitdate;
	}
		
	public String getHandoverstatus() {
		return this.handoverstatus;
	}
		 	
	public void setHandoverstatus(String handoverstatus) {
		this.handoverstatus = handoverstatus;
	}

	public Double getMoney() {
		return money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

	public String getSurnname() {
		return surnname;
	}

	public void setSurnname(String surnname) {
		this.surnname = surnname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
		
	
}
