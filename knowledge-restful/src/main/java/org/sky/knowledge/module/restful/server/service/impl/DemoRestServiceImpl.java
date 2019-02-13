/**
 * Project Name:FIMS-Interfaces
 * File Name:DemoRestServiceImpl.java
 * Package Name:com.deppon.fims.inter.demo.service
 * Date:2014-10-10下午3:25:33
 * Copyright (c) 2014 All Rights Reserved.
 */

package org.sky.knowledge.module.restful.server.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import org.sky.knowledge.module.restful.server.service.IDemoRestService;
import org.sky.knowledge.module.restful.shared.UserEntityRes;
import org.sky.knowledge.module.restful.shared.UserInfo;


/*
 注释（Annotation）：在 javax.ws.rs.* 中定义，是 JAX-RS (JSR 311) 规范的一部分。 
 @Path：定义资源基 URI。由上下文根和主机名组成，资源标识符类似于 http://localhost:8080/RESTful/rest/hello。 
 @GET：这意味着以下方法可以响应 HTTP GET 方法。 
 @Context： 使用该注释注入上下文对象，比如 Request、Response、UriInfo、ServletContext 等。 
 @Path("{contact}")：这是 @Path 注释，与根路径 “/contacts” 结合形成子资源的 URI。 
 @PathParam("contact")：该注释将参数注入方法参数的路径，在本例中就是联系人 id。其他可用的注释有 @FormParam、@QueryParam 等。 
 @Produces：标注返回的MIME媒体类型，定义了服务类和方法生产内容的类型，响应支持多个 MIME 类型。在本例和上一个示例中，APPLICATION/XML 将是默认的 MIME 类型。
 @Consumes：标注可接受请求的MIME媒体类型。
 @FormParam：注入该方法的 HTML 属性确定的表单输入。
 @Response.created(uri).build()： 构建新的 URI
 用于新创建的联系人（/contacts/{id}）并设置响应代码（201/created）。
 您可以使用 http://localhost:8080/Jersey/rest/contacts/<id> 访问新联系人
 @PathParam，@QueryParam，@HeaderParam，@CookieParam，@MatrixParam，@FormParam,分别标注方法的参数来自于HTTP请求的不同位置，例如@PathParam来自于URL的路径，@QueryParam来自于URL的查询参数，@HeaderParam来自于HTTP请求的头信息，@CookieParam来自于HTTP请求的Cookie
 */
/**
 * restful 服务端
 * @description
 * @create xq
 * @date 2014-10-16
 */
public class DemoRestServiceImpl implements IDemoRestService {

	@Context
	private UriInfo uriInfo;

	@Context
	private Request request;

	public String doGet() {
		UserEntityRes res = new UserEntityRes();
		res.setIsSuccess("1");
		System.out.print(request.getMethod());
		return "success";
	}

	public String doRequest(@PathParam("param") String param,
			@Context HttpServletRequest servletRequest,
			@Context HttpServletResponse servletResponse) {
		System.out.println(servletRequest);
		System.out.println(servletResponse);
		System.out.println(servletRequest.getParameter("param"));
		System.out.println(servletRequest.getContentType());
		System.out.println(servletResponse.getCharacterEncoding());
		System.out.println(servletResponse.getContentType());
		return "success";
	}

	@GET
	@Path("/bean/{id}")
	@Produces({"application/json" })
	public UserEntityRes getBean(@PathParam("id") int id) {
		System.out.println("####getBean#####");
		UserEntityRes res = new UserEntityRes();
		res.setIsSuccess("1");
		System.out.print(request.getMethod());
		res.setFaileReason("成功");
		return res;
	}

	@GET
	@Path("/list")
	@Produces({ "application/json", "application/xml" })
	public UserEntityRes getList() {
		UserEntityRes res = new UserEntityRes();
		res.setIsSuccess("1");
		System.out.print(request.getMethod());
		return res;
	}

	@GET
	@Path("/map")
	@Produces({ "application/xml", "application/json" })
	public UserEntityRes getMap() {
		UserEntityRes res = new UserEntityRes();
		res.setIsSuccess("1");
		System.out.print(request.getMethod());
		return res;
	}
/*
	
	@POST
	@Path("/postData")
	public UserEntityRes postData(UserEntityRes userEntityRes)
			throws IOException {
		UserEntityRes res = new UserEntityRes();
		res.setIsSuccess("1");
		System.out.print(request.getMethod());
		return res;
	}*/

	@PUT
	@Path("/putData/{id}")
	@Consumes("application/xml")
	public UserEntityRes putData(@PathParam("id") int id,
			UserEntityRes userEntityRes) {
		UserEntityRes res = new UserEntityRes();
		res.setIsSuccess("1");
		System.out.print(request.getMethod());
		return res;
	}
	
	
	public UserEntityRes putObjData(UserInfo u) {
		if(null == u){
			ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
			builder.type("application/xml");
			builder.entity("<error>Category Not Found</error>");
			throw new WebApplicationException(builder.build());
		}
		System.out.println("服务端："+u.toString());
		UserEntityRes res = new UserEntityRes();
		res.setIsSuccess("1");
		Map map = new HashMap();
		map.put("user",u);
		res.setUsermap(map);
		System.out.print(request.getMethod());
		return res;
	}
	
/*
	
	@DELETE
	@Path("/removeData/{id}")
	public UserEntityRes deleteData(@PathParam("id") int id) {
		UserEntityRes res = new UserEntityRes();
		res.setIsSuccess("1");
		System.out.print(request.getMethod());
		return res;
	}*/

	
	@DELETE
	@Path("/removeData")
	public UserEntityRes deleteData(UserEntityRes userEntityRes) {
		UserEntityRes res = new UserEntityRes();
		res.setIsSuccess("1");
		System.out.print(request.getMethod());
		return res;
	}
}
