/**
 * Project Name:FIMS-Interfaces
 * File Name:UserInfo.java
 * Package Name:com.deppon.fims.inter.demo.domian
 * Date:2014-10-10下午2:38:52
 * Copyright (c) 2014 All Rights Reserved.
 */

package org.sky.knowledge.module.restful.shared;
import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @ClassName:UserInfo <br/>
 * @Title: TODO 用一句话说明. <br/>
 * @author: 215209
 * @Date: 2014-10-10 下午2:38:52
 */
@XmlRootElement(name = "UserInfo")
public class UserInfo implements Serializable {
	/**
	 * serialVersionUID:(用一句话描述这个变量表示什么).
	 */
	private static final long serialVersionUID = -6106492093697801090L;

	private Integer uid;

	private String userName;

	private String pwd;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	@Override
	public String toString() {
		return this.uid + "#" + this.userName + "#" + this.pwd + "#";
	}
}
