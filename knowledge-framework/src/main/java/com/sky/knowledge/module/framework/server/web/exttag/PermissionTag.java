package com.sky.knowledge.module.framework.server.web.exttag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.sky.knowledge.module.framework.server.components.security.SecurityAccessor;

/**
 * 
 * 
*******************************************
* <b style="font-family:微软雅黑"><small>Description:判断url是否有权限，将相关信息封装到javascript对象中，并返回</small></b>   </br>
* <b style="font-family:微软雅黑"><small>HISTORY</small></b></br>
* <b style="font-family:微软雅黑"><small> ID      DATE    PERSON     REASON</small></b><br>
********************************************
* <div style="font-family:微软雅黑,font-size:70%"> 
* 1 2011-5-10 陈兴波 新增
* </div>  
********************************************
 */
public class PermissionTag extends SimpleTagSupport {
	/**
	 * url
	 */
	private String urls;
	
	/**
	 * SimpleTagSupport标签执行的主方法
	 * 调用权限访问控制器，遍历传入的urls的字符串，查找出每一个url对应的权限信息，将键和权限信息封装成一个javascript对象，放入到script 标签中，并返回
	 * @see javax.servlet.jsp.tagext.SimpleTagSupport#doTag()
	 * doTag
	 * @throws JspException
	 * @throws IOException
	 * @since JDK1.6
	 */
	@Override
	public void doTag() throws JspException, IOException {
		if(this.urls== null||"".equals(this.urls))
		{
			return;
		}
		String []urlArray = parseUrls(urls);
		StringBuilder  msgObject=new StringBuilder("");
		msgObject.append("<script type='text/javascript'> \n");
		msgObject.append("function isPermission(url){ \n");
		msgObject.append("permissions = {");
		for(String url :urlArray)
		{
			boolean message = SecurityAccessor.hasAccessSecurity(url);
			msgObject.append("'"+url+"'" +":"+message+",");
		}
		msgObject.deleteCharAt(msgObject.lastIndexOf(","));
		msgObject.append("}; \n");
		msgObject.append("return permissions[url]; \n");
		msgObject.append("}; \n");
		msgObject.append("</script>");
		getJspContext().getOut().write(msgObject.toString());
	}

	/**
	 * 设置url字符串
	 * setUrls
	 * @param urls
	 * @return void
	 * @since JDK1.6
	 */
	public void setUrls(String urls) {
		this.urls = urls;
	}

	/**
	 * 解析字符串，获得url组成的数组
	 * parseUrls
	 * @param urls
	 * @return
	 * @return String[]
	 * @since JDK1.6
	 */
	private String[] parseUrls(String urls){
		urls = urls.replaceAll("\n", "");
		urls = urls.replaceAll("\r", "");
		String []urlArray = urls.split(",");
		for(int i=0;i<urlArray.length;i++)
		{
			urlArray[i] = urlArray[i].trim();
		}
		return urlArray;
	}
}
