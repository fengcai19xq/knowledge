package com.sky.knowledge.module.framework.server.aop;

import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import com.sky.knowledge.module.framework.server.components.logger.LogBuffer;
import com.sky.knowledge.module.framework.server.components.security.SecurityAccessor;
import com.sky.knowledge.module.framework.server.components.security.SecurityNonCheckRequired;
import com.sky.knowledge.module.framework.server.context.RequestContext;
import com.sky.knowledge.module.framework.shared.exception.components.IExceptionConvert;
import com.sky.knowledge.module.framework.shared.service.IWebService;


/**
 * 
 * 
 ******************************************* 
 * <b
 * style="font-family:微软雅黑"><small>Description:业务拦截器，拦截非struts分发的请求</small></b>
 * </br> <b style="font-family:微软雅黑"><small>HISTORY</small></b></br> <b
 * style="font-family:微软雅黑"><small> ID DATE PERSON REASON</small></b><br>
 ******************************************** 
 * <div style="font-family:微软雅黑,font-size:70%"> 1 2011-6-13 徐涛 新增 </div>
 ******************************************** 
 */
@Aspect
public class WebServiceInterceptor {

	private static final Log logger = LogFactory
			.getLog(WebServiceInterceptor.class);

	@Resource
	private IExceptionConvert exceptionConvert;

	@Resource(name = "performanceLog")
	private LogBuffer performanceLog;
	
	@Resource(name = "exceptionLog")
	private LogBuffer exceptionLog;

	@Pointcut("execution(public * com.sky..*server.ws..*Ws.*(..))")
	public void webServicePointCut() {
	}

	/*
	 * When using Spring AOP, spring injects a proxy to the bean into CXF
	 * instead of the actual bean. The Proxy does not have the annotations on it
	 * (like the @WebService annotation) so we cannot query the information
	 * directly from the object like we can in the non-AOP case. The "fix" is to
	 * also specify the actual serviceClass of the object in the spring config:
	 * 
	 * <jaxws:endpoint id="myService"
	 * implementorClass="my.package.MyServiceImpl" implementor="#myServiceImpl"
	 * address="/MyService" />
	 */
	@Around("webServicePointCut()")
	public Object webServiceAdvice(ProceedingJoinPoint pjp) throws Throwable {

		Object object = null;
		// 性能日志记录
		// 从上下文中获取 bean：logBuffer
		String uid = null;
		Object target = null;
		Class<?> callerCls = null;
		try {
			if (performanceLog.isEnable()) {
				uid = UUID.randomUUID().toString();
				target = pjp.getTarget();
				long beginTime = System.currentTimeMillis();
				performanceLog.write(getPerfomanceLogInfo(uid, "begin", target, pjp
						.getSignature().toLongString(), beginTime));
			}
			// 权限检查
			callerCls = pjp.getTarget().getClass();
			// 忽略使用SecurityNonCheckRequired注解的类
			if (!callerCls.isAnnotationPresent(SecurityNonCheckRequired.class)) {
				// TODO 暂时不进行权限检查
				final String reqUrl = RequestContext.getCurrentContext()
						.getRemoteRequestURL();
				String methodName = pjp.getSignature().getName();
				String realUrl = reqUrl.substring(1);
				realUrl = realUrl.substring(realUrl.indexOf("/"));
				
				//建议 url: {prefix}/module/interface/method
				//{prefix} = http://${website}/${application}/${webservice}
				SecurityAccessor.checkURLAccessSecurity(realUrl + "/"
						+ methodName);
			}

			object = pjp.proceed();
		} catch (Throwable t) {
			if( exceptionLog.isEnable()){
				exceptionLog.write(t.getMessage());
			}
			// TODO performance log exception
			if (IWebService.class.isAssignableFrom(callerCls)) {
				throw exceptionConvert.nativeConvert(t);
			}
			logger.error(t.getMessage(), t);
			throw t;
		} finally {
			if (performanceLog.isEnable()) {
				long endTime = System.currentTimeMillis();
				performanceLog.write(getPerfomanceLogInfo(uid, "end", target, pjp
						.getSignature().toLongString(), endTime));
			}

		}
		return object;
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
		return new StringBuffer().append(uid).append("|").append(tag)
				.append("|").append(target.getClass().getName()).append("|")
				.append(method).append("|").append(time).toString();
	}

}