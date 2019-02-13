package com.sky.knowledge.module.framework.server.web.interceptor;

import java.io.BufferedReader;
import java.lang.reflect.Method;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.util.ValueStack;
import com.sky.knowledge.module.framework.server.web.result.json.JSONPopulator;
import com.sky.knowledge.module.framework.server.web.result.json.annotation.JSON;
import com.sky.knowledge.module.framework.shared.define.Protocol;


/**
*******************************************
* <b style="font-family:微软雅黑"><small>Description:JSON反序列化拦截器对JSON格式提交的数据反序列化并绑定到Action</small></b>   </br>
* <b style="font-family:微软雅黑"><small>HISTORY</small></b></br>
* <b style="font-family:微软雅黑"><small> ID      DATE    PERSON     REASON</small></b><br>
********************************************
* <div style="font-family:微软雅黑,font-size:70%"> 
* 1 2011-3-8 周丹枫 新增
* </div>  
********************************************
 */
@Component
public class JSONInterceptor extends AbstractInterceptor {
	
	private static final long serialVersionUID = -3423290391156261612L;
	
	private final Log log = LogFactory.getLog(getClass());
	
	/**
	 * 序列化及缓存类
	 */
	protected static ObjectMapper mapper = new ObjectMapper();
	
	/**
	 * 指定反序列化对象名
	 */
	private String root;

	/**
	 * JSON格式反序列化并绑定到Action中的属性上
	 * @see com.opensymphony.xwork2.interceptor.AbstractInterceptor#intercept(com.opensymphony.xwork2.ActionInvocation)
	 * intercept
	 * @param invocation
	 * @return
	 * @throws Exception
	 * @since:
	 */
	public String intercept(ActionInvocation invocation) throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		String contentType = request.getHeader("content-type");
		if (contentType != null) {
			int iSemicolonIdx;
			if ((iSemicolonIdx = contentType.indexOf(";")) != -1)
				contentType = contentType.substring(0, iSemicolonIdx);
		}

		Object rootObject;
		if (this.root != null) {
			ValueStack stack = invocation.getStack();
			rootObject = stack.findValue(this.root);

			if (rootObject == null) {
				throw new RuntimeException("Invalid root expression: '"
						+ this.root + "'.");
			}
		} else {
			rootObject = invocation.getAction();
		}

		Class<?> clazz = invocation.getAction().getClass();
		String methodName = invocation.getProxy().getMethod();
		Method method = ReflectionUtils.findMethod(clazz, methodName);
		//如果在Action方法上加入JSON的注解，那么就可以在应用这个方法的时候，把错误的结果信息转换为json
	    if (Protocol.JSON_CONTENT_TYPE.equalsIgnoreCase(contentType) || AnnotationUtils.isAnnotationDeclaredLocally(JSON.class, clazz)
	        	|| (method != null && method.isAnnotationPresent(JSON.class))) {
			BufferedReader reader = request.getReader();
			String line = null;
			StringBuilder stringBuilder = new StringBuilder();
			while ((line = reader.readLine()) != null) {
				stringBuilder.append(line);
			}
			if (stringBuilder.toString().length() > 0) {
				@SuppressWarnings("rawtypes")
				Map json = mapper.readValue(stringBuilder.toString(), Map.class);
				JSONPopulator.populateObject(rootObject, json);
			}
		} else {
			if (log.isDebugEnabled()) {
				log.debug("Content type must be 'application/json' or 'application/json-rpc'. Ignoring request with content type "
						+ contentType);
			}
		}
		return invocation.invoke();
	}

}