package com.sky.knowledge.module.framework.server.web.result;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;


public class JSPErrorResult extends AbstractResult{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2811930130511957494L;

	/**
	 * 执行输出错误页面，并提示错误信息
	 * @see com.deppon.foss.framework.server.web.result.AbstractResult#execute(com.opensymphony.xwork2.ActionInvocation)
	 * execute
	 * @param invocation
	 * @throws Exception
	 * @since:
	 */
	@Override
	public void execute(ActionInvocation invocation) throws Exception {
		
		HttpServletRequest req = ServletActionContext.getRequest();
		HttpServletResponse resp = ServletActionContext.getResponse();
		resp.setContentType("text/html; charset=utf-8");
		resp.setCharacterEncoding("utf-8");
		PrintWriter out = resp.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'/> ");
		out.println("</head>");
		out.println("<body>");
		out.println("<h2 align='center'>出错啦！！！</h2>");
		out.println("<div align='center'>");
		out.println("<a href='#' onclick=\"javascript:document.getElementById('message').innerHTML='" + req.getAttribute("ERROR") + "'\">错误信息</a>");
		out.println("<div id='message'></div>");
		out.println("</div>");
		out.println("</body>");
		out.println("</html>");
//		out.flush();
//		out.close();
	}
	
	

}
