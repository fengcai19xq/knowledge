/**
 * Project Name:FIMS-Interfaces
 * File Name:IDemoRestClientService.java
 * Package Name:com.deppon.fims.inter.demo.client
 * Date:2014-10-11下午2:14:14
 * Copyright (c) 2014 All Rights Reserved.
 */

package org.sky.knowledge.module.restful.server.client;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.sky.knowledge.module.restful.shared.UserEntityReq;
import org.sky.knowledge.module.restful.shared.UserEntityRes;

/**
 * @ClassName:IDemoRestClientService <br/>
 * @Title: TODO 用一句话说明. <br/>
 * @author: 215209
 * @Date: 2014-10-11 下午2:14:14
 */

@Path(value = "/sample")
public interface IDemoRestClientService {
	
	@GET
	@Path("/bean/{id}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public UserEntityRes getBean(int id);
	

	@PUT
	@Path("/putData/{id}")
	@Consumes(MediaType.APPLICATION_XML)
	public UserEntityRes putData(int id,
			UserEntityReq userEntityReq);
	
	
}
