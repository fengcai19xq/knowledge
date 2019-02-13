package com.sky.knowledge.module.framework.server.web.result.json;


import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.io.OutputStream;
import java.io.StringWriter;
import java.lang.reflect.Method;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.StrutsConstants;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.Result;
import com.opensymphony.xwork2.inject.Inject;
import com.sky.knowledge.module.framework.server.web.action.AbstractAction;
@SuppressWarnings("serial")
public class JSONResult implements Result {
	/** 继承ActionSupport中的属性 */
	protected static final Set<String> FIELDS = new HashSet<String>();
	/** 共用一个静态变量，缓存解析过的类型 */
	protected static ObjectMapper mapper = new ObjectMapper();

	private String defaultEncoding = "UTF-8";
	private Log log = LogFactory.getLog(getClass());

	static {
		FIELDS.add("actionErrors");
		FIELDS.add("actionMessages");
		FIELDS.add("class");
		FIELDS.add("errorMessages");
		FIELDS.add("errors");
		FIELDS.add("locale");
		FIELDS.add("fieldErrors");
		FIELDS.add("texts");
		FIELDS.add("success");
		FIELDS.add("isException");
	}

	@Inject(StrutsConstants.STRUTS_I18N_ENCODING)
	public void setDefaultEncoding(String val) {
		this.defaultEncoding = val;
	}

	protected String getEncoding() {
		String encoding = this.defaultEncoding;

		if (encoding == null) {
			encoding = System.getProperty("file.encoding");
		}

		if (encoding == null) {
			encoding = "UTF-8";
		}

		return encoding;
	}

	private String includeProperties;
	private Set<String> includePropertiesSet;

	public String getIncludeProperties() {
		return includeProperties;
	}

	public void setIncludeProperties(String includeProperties) {
		if (includeProperties == null)
			return;
		this.includeProperties = includeProperties;
		String[] properties = this.includeProperties.split(",");
		includePropertiesSet = new HashSet<String>();
		for (String property : properties) {
			includePropertiesSet.add(property);
		}
		this.setIncludePropertiesList(includePropertiesSet);
	}

	public Set<String> getIncludePropertiesList() {
		return includePropertiesSet;
	}

	public void setIncludePropertiesList(Set<String> includePropertiesList) {
		this.includePropertiesSet = includePropertiesList;
	}

	/*
	 * <action name="findAll" class="orderBillAction" method="findAll">
	 * 		<result name='success' type='json'>
	 *  		<param name="contentType">text/html</param>
	 * 		</result>
	 * </action>
	 */
	private String contentType;

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	/**
	 * 序列化对象成JSON
	 * 
	 * @param val
	 * @param sb
	 * @throws JsonGenerationException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	private void serialize(Object val, StringBuilder sb)
			throws JsonGenerationException, JsonMappingException, IOException {
		if (val == null) {
			sb.append("null").append(",");
		} else if (val instanceof String || val instanceof StringBuffer
				|| val instanceof StringBuilder) {
			sb.append("\"").append(val.toString()).append("\"").append(",");
		} else if (val instanceof Number || val instanceof Boolean) {
			sb.append(val.toString()).append(",");
		} else if (val instanceof Date) {
			sb.append((Long) ((Date) val).getTime()).append(",");
		} else if (val instanceof Calendar) {
			sb.append((Long) ((Calendar) val).getTime().getTime()).append(",");
		} else {
			StringWriter writer = new StringWriter();
			mapper.writeValue(writer, val);
			sb.append(writer.getBuffer().toString()).append(",");
		}
	}

	public void execute(ActionInvocation invocation) throws Exception {
		if (log.isDebugEnabled())
			log.debug("begin JSONResult");
		HttpServletResponse response = ServletActionContext.getResponse();

		if (getContentType() != null) {
			response.setContentType(this.getContentType());
		} else {
			response.setContentType("application/json");
		}
		response.setCharacterEncoding(defaultEncoding);
		// PrintWriter out = response.getWriter();
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		Object obj = invocation.getAction();
		BeanInfo ip = Introspector.getBeanInfo(obj.getClass());
		PropertyDescriptor[] pros = ip.getPropertyDescriptors();
		// 对success、isException属性特别处理
		if (obj instanceof AbstractAction) {
			PropertyDescriptor spd = new PropertyDescriptor("success",
					obj.getClass());
			Method smd = spd.getReadMethod();
			Object success = smd.invoke(obj);
			//ztjie修改：返回的success属性名的前后都加上双引号
			sb.append("\"success\"").append(":");
			serialize(success, sb);

			PropertyDescriptor epd = new PropertyDescriptor("exception",
					obj.getClass());
			Method emd = epd.getReadMethod();
			Object isException = emd.invoke(obj);
			//ztjie修改：返回的isException属性名的前后都加上双引号
			sb.append("\"isException\"").append(":");
			serialize(isException, sb);
		}
		for (PropertyDescriptor pro : pros) {
			if (includePropertiesSet != null) {
				if (!includePropertiesSet.contains(pro.getDisplayName()))
					continue;
			}
			Method method = pro.getReadMethod();
			if (method != null) {
				Object[] objs = null;
				Object val = method.invoke(obj, objs);
				String proName = pro.getDisplayName();
				if (FIELDS.contains(proName))
					continue;
				//ztjie修改：返回的请求类的所有属性名的前后都加上双引号
				sb.append("\"");
				sb.append(pro.getDisplayName());
				sb.append("\"");
				sb.append(":");
				serialize(val, sb);
			}
		}
		String result = "";
		if (sb.length() > 1)
			result = sb.substring(0, sb.length() - 1);
		result = result.concat("}");
		OutputStream out = response.getOutputStream();
		out.write(result.getBytes(defaultEncoding));
		// out.print(result + "}");
		out.flush();
		out.close();
	}
}
