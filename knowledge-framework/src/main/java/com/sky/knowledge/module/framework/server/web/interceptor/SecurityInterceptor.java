package com.sky.knowledge.module.framework.server.web.interceptor;

import java.lang.reflect.Method;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import com.opensymphony.xwork2.ActionInvocation;
import com.sky.knowledge.module.framework.server.components.logger.LogBuffer;
import com.sky.knowledge.module.framework.server.components.security.SecurityAccessor;
import com.sky.knowledge.module.framework.server.components.security.SecurityNonCheckRequired;
import com.sky.knowledge.module.framework.server.context.RequestContext;


/**
*******************************************
* <b style="font-family:微软雅黑"><small>Description:性能监控与权限控制拦截器</small></b>   </br>
* <b style="font-family:微软雅黑"><small>HISTORY</small></b></br>
* <b style="font-family:微软雅黑"><small> ID      DATE    PERSON     REASON</small></b><br>
********************************************
* <div style="font-family:微软雅黑,font-size:70%"> 
* 1 2011-3-8 周丹枫 新增
* </div>  
********************************************
 */
@Component
public class SecurityInterceptor extends AbstractInterceptor {

	private static final long serialVersionUID = -5665511978967345874L;

	/**
	 * 日志缓冲
	 */
	@Resource(name = "performanceLog")
	private LogBuffer performanceLog;

	/**
	 * 性能监控与权限控件拦截器初始化方法
	 * @see com.deppon.foss.framework.server.web.interceptor.AbstractInterceptor#init()
	 * init
	 * @since:
	 */
	@Override
	public void init() {
		// securityAccessor = new SecurityAccessor();
	}

	/**
	 * 性能监控与权限控制拦截器主方法，实现性能监控与权限控制
	 * @see com.opensymphony.xwork2.interceptor.AbstractInterceptor#intercept(com.opensymphony.xwork2.ActionInvocation)
	 * intercept
	 * @param invocation
	 * @return
	 * @throws Exception
	 * @since:
	 */
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		String uid = null;
		Object target = invocation.getAction();
		String methodName = invocation.getProxy().getMethod();;
		if (performanceLog.isEnable()) {
			uid = UUID.randomUUID().toString();
			long beginTime = System.currentTimeMillis();
			performanceLog.write(getPerfomanceLogInfo(uid, "begin", target, methodName,
					beginTime));
		}
		final String url = RequestContext.getCurrentContext()
				.getRemoteRequestURL();
		Method method = ReflectionUtils.findMethod(target.getClass(), methodName);
		if (!method.isAnnotationPresent(SecurityNonCheckRequired.class))
			SecurityAccessor.checkURLAccessSecurity(url);
		try {
			String result = invocation.invoke();
			return result;
		} finally {
			if (performanceLog.isEnable()) {
				long endTime = System.currentTimeMillis();
				performanceLog.write(getPerfomanceLogInfo(uid, "end", target,
						methodName, endTime));
			}
		}
		
	}

	/**
	 * 
	 * @Title:getPerfomanceLogInfo
	 * @Description:性能日志格式:
	 * 
	 *                      [日志id]|begin or end|class|method
	 * 
	 * @param @param uid
	 * @param @param tag 开始结束标记
	 * @param @param target
	 * @param @param method
	 * @param @return
	 * @return Object
	 * @throws
	 */
	private Object getPerfomanceLogInfo(String uid, String tag, Object target,
			String method, long time) {
		return new StringBuffer().append(uid).append("|")
				.append(tag).append("|").append(target.getClass().getName())
				.append("|").append(method).append("|").append(time).toString();
	}

}