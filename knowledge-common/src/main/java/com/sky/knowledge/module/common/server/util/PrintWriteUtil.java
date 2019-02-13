package com.sky.knowledge.module.common.server.util;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;
/**
 * @功能描述：产生一个PrintWrite对象，用于向浏览器输出信息
 * @description
 * @create xq
 * @date 2014-10-17
 */
public class PrintWriteUtil
{
	/**
	 * 获取一个PrinterWriter对象,并返回
	 * */
	public static void writeResultToBrowser(boolean flag,String result){
		result = result == null ? "" : result;
		//从servletActionContext上下文中,获取response对象
		HttpServletResponse response = ServletActionContext.getResponse();
		//设置向浏览器返回信息的格式为text/html格式
		response.setContentType("text/html");
		//设置response编码为utf-8
		response.setCharacterEncoding("utf-8");
		
		PrintWriter ps = null;
		try {
			ps = response.getWriter();
			
			String r = "{success : "+flag+",'msg':'" + result + "'}";
			ps.write(r);
		} catch (IOException e) {
			
			e.printStackTrace();
		}finally{
			if(ps != null){
				ps.flush();
				ps.close();
			}
		}
	}
	
	/**
	 * 获取一个PrinterWriter对象,并返回
	 * */
	public static PrintWriter writeResultToBrowser(){
		//从servletActionContext上下文中,获取response对象
		HttpServletResponse response = ServletActionContext.getResponse();
		//设置向浏览器返回信息的格式为text/html格式
		response.setContentType("text/html");
		//设置response编码为utf-8
		response.setCharacterEncoding("utf-8");
		
		PrintWriter ps = null;
		try {
			ps = response.getWriter();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		return ps;
	}
}