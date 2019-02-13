package org.sky.knowledge.module.restful.server.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.sky.knowledge.module.restful.shared.UserEntityRes;
import org.sky.knowledge.module.restful.shared.UserInfo;

/*
 注释（Annotation）：在 javax.ws.rs.* 中定义，是 JAX-RS (JSR 311) 规范的一部分。 
 @Path：定义资源基 URI。由上下文根和主机名组成，资源标识符类似于 http://localhost:8080/RESTful/rest/hello。 
 @GET：这意味着以下方法可以响应 HTTP GET 方法。 
 @Produces：以纯文本方式定义响应内容 MIME 类型。
 @Context： 使用该注释注入上下文对象，比如 Request、Response、UriInfo、ServletContext 等。 
 @Path("{contact}")：这是 @Path 注释，与根路径 “/contacts” 结合形成子资源的 URI。 
 @PathParam("contact")：该注释将参数注入方法参数的路径，在本例中就是联系人 id。其他可用的注释有 @FormParam、@QueryParam 等。 
 @Produces：响应支持多个 MIME 类型。在本例和上一个示例中，APPLICATION/XML 将是默认的 MIME 类型。
 @Consumes：声明该方法使用 HTML FORM。
 @FormParam：注入该方法的 HTML 属性确定的表单输入。
 @Response.created(uri).build()： 构建新的 URI
 用于新创建的联系人（/contacts/{id}）并设置响应代码（201/created）。
 您可以使用 http://localhost:8080/Jersey/rest/contacts/<id> 访问新联系人
 */
/**
 * 
 * ClassName: IDemoRestService <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * 
 * @author 215209
 * @version
 * @date: 2014-10-10 上午9:17:33
 */

public interface IDemoRestService {

	/**
	 * 
	 * doGet:返回所有信息. <br/>
	 * 
	 * @author 215209
	 * @Date:2014-10-10下午3:24:31
	 */
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String doGet();

	/**
	 * 
	 * doRequest:返回指定条件数据信息. <br/>
	 * 
	 * @author 215209
	 * @Date:2014-10-10下午3:22:10
	 */
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/request/{param}")
	public String doRequest(@PathParam("param") String param,
			@Context HttpServletRequest servletRequest,
			@Context HttpServletResponse servletResponse);

	/**
	 * 
	 * getMap:返回指定id数据信息. <br/>
	 * 
	 * @author 215209
	 * @Date:2014-10-10下午3:19:47
	 */
	@GET
	@Path("/bean/{id}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public UserEntityRes getBean(@PathParam("id") int id);

	/**
	 * 
	 * getMap:返回list集合数据. <br/>
	 * 
	 * @author 215209
	 * @Date:2014-10-10下午3:19:47
	 */
	@GET
	@Path("/list")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public UserEntityRes getList();

	/**
	 * 
	 * getMap:返回map集合数据. <br/>
	 * 
	 * @author 215209
	 * @Date:2014-10-10下午3:19:47
	 */
	@GET
	@Path("/map")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public UserEntityRes getMap();

	/**
	 * 
	 * postData:批量更新. <br/>
	 * 
	 * @author 215209
	 * @Date:2014-10-10下午3:19:10
	 
	@POST
	@Path("/postData")
	public UserEntityRes postData(UserEntityRes userEntityRes)
			throws IOException;
*/
	/**
	 * 
	 * putData:按指定id更新. <br/>
	 * 
	 * @author 215209
	 * @Date:2014-10-10下午3:18:37
	 */
	@PUT
	@Path("/putData/{id}")
	@Consumes(MediaType.APPLICATION_XML)
	public UserEntityRes putData(@PathParam("id") int id,
			UserEntityRes userEntityRes);
	/**
	 * 传递实体类
	 * @description
	 * @create xq
	 * @date 2014-10-16
	 */
	@POST
	@Path("/putObjData")
	@Consumes("application/json")
	public UserEntityRes putObjData(UserInfo u);
	

	/**
	 * 
	 * deleteData:按指定id删除. <br/>
	 * 
	 * @author 215209
	 * @Date:2014-10-10下午3:15:33

	@DELETE
	@Path("/removeData/{id}")
	public UserEntityRes deleteData(@PathParam("id") int id);
	*/
	/**
	 * 
	 * deleteData:批量删除. <br/>
	 * 
	 * @author 215209
	 * @Date:2014-10-10下午3:17:01
	 */
	@DELETE
	@Path("/removeData")
	public UserEntityRes deleteData(UserEntityRes userEntityRes);
}
