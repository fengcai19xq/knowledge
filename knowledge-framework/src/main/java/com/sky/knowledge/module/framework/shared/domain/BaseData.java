package com.sky.knowledge.module.framework.shared.domain;

import java.util.Date;

import com.sky.knowledge.module.framework.shared.entity.BaseEntity;
import com.sky.knowledge.module.framework.shared.entity.IBaseData;

/**
 * 系统基础资料实体类
 * @description
 * @create xq
 * @date 2014-11-11
 */
public class BaseData extends BaseEntity implements IBaseData{

	/**
	 * @description
	 * @create xq
	 * @date 2014-11-11  
	 */
	private static final long serialVersionUID = 2779090984452282837L;

	/**
	 * fid
	 */
	private String  fid;
	/**
	 * 类型编码
	 */
	private String typecode;
	/**
	 * 类型名称
	 */
	private String typename;
	/**
	 * 类型键
	 */
	private String typekey;
	/**
	 * 类型值
	 */
	private String typevalue; 
	/**
	 * 启用状态（0、否；1、是）
	 */
	private String status ;
	/**
	 * 创建时间
	 */
	private Date createdate;
	/**
	 * 修改时间
	 */
	private Date modifydate ;
	/**
	 * 备注
	 */
	private String remark ;
	/**
	 * 创建人
	 */
	private String createuser ;
	/**
	 * 修改人
	 */
	private String modifyuser ;

	public String getFid() {
		return fid;
	}

	public void setFid(String fid) {
		this.fid = fid;
	}

	public String getTypecode() {
		return typecode;
	}

	public void setTypecode(String typecode) {
		this.typecode = typecode;
	}

	public String getTypename() {
		return typename;
	}

	public void setTypename(String typename) {
		this.typename = typename;
	}

	public String getTypekey() {
		return typekey;
	}

	public void setTypekey(String typekey) {
		this.typekey = typekey;
	}

	public String getTypevalue() {
		return typevalue;
	}

	public void setTypevalue(String typevalue) {
		this.typevalue = typevalue;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getCreatedate() {
		return createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	public Date getModifydate() {
		return modifydate;
	}

	public void setModifydate(Date modifydate) {
		this.modifydate = modifydate;
	}

	

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCreateuser() {
		return createuser;
	}

	public void setCreateuser(String createuser) {
		this.createuser = createuser;
	}

	public String getModifyuser() {
		return modifyuser;
	}

	public void setModifyuser(String modifyuser) {
		this.modifyuser = modifyuser;
	}
	
}
