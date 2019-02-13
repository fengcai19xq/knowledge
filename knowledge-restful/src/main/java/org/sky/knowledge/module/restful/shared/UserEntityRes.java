/**
 * Project Name:FIMS-Interfaces
 * File Name:ResUserEntity.java
 * Package Name:com.deppon.fims.inter.demo.domian
 * Date:2014-10-10下午2:41:18
 * Copyright (c) 2014 All Rights Reserved.
 */

package org.sky.knowledge.module.restful.shared;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @ClassName:ResUserEntity <br/>
 * @Title: TODO 用一句话说明. <br/>
 * @author: 215209
 * @Date: 2014-10-10 下午2:41:18
 */
@XmlRootElement(name = "UserEntityRes")
public class UserEntityRes extends SynBaseResponse {

	/**
	 * serialVersionUID:(用一句话描述这个变量表示什么).
	 */
	private static final long serialVersionUID = -3255546391396672717L;
	/**
	 * list
	 */
	private List<UserInfo> userList;
	/**
	 * map
	 */
	private Map<String, UserInfo> usermap;

	public List<UserInfo> getUserList() {
		if (null == this.userList) {
			this.userList = new ArrayList<UserInfo>();
		}
		return userList;
	}

	public void setUserList(List<UserInfo> userList) {
		this.userList = userList;
	}

	public Map<String, UserInfo> getUsermap() {
		if (null == this.usermap) {
			this.usermap = new HashMap<String, UserInfo>();
		}
		return usermap;
	}

	public void setUsermap(Map<String, UserInfo> usermap) {
		this.usermap = usermap;
	}
}
