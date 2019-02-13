/**
 * Project Name:FIMS-Interfaces
 * File Name:ReqUserEntity.java
 * Package Name:com.deppon.fims.inter.demo.domian
 * Date:2014-10-10下午2:40:50
 * Copyright (c) 2014 All Rights Reserved.
 */

package org.sky.knowledge.module.restful.shared;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @ClassName:ReqUserEntity <br/>
 * @Title: TODO 用一句话说明. <br/>
 * @author: 215209
 * @Date: 2014-10-10 下午2:40:50
 */
@XmlRootElement(name = "userEntityReq")
public class UserEntityReq {
	/**
	 * 用户实体
	 */
	private UserInfo userInfo;

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

}
