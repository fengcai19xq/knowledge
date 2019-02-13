package com.sky.knowledge.module.framework.server.web.interceptor;

import java.lang.reflect.Method;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.ReflectionUtils;

import com.opensymphony.xwork2.ActionInvocation;
import com.sky.knowledge.module.framework.server.web.action.AbstractAction;
import com.sky.knowledge.module.framework.server.web.message.IMessageBundle;
import com.sky.knowledge.module.framework.server.web.result.json.annotation.JSON;
import com.sky.knowledge.module.framework.shared.define.Protocol;
import com.sky.knowledge.module.framework.shared.exception.GeneralException;
import com.sky.knowledge.module.framework.shared.exception.components.IExceptionConvert;
import com.sky.knowledge.module.framework.shared.exception.security.AccessNotAllowException;
import com.sky.knowledge.module.framework.shared.exception.security.UserNotLoginException;


/**
 * 
 * 
*******************************************
* <b style="font-family:微软雅黑"><small>Description:异常拦截器，对所有异常进行拦截，并进行异常的处理</small></b>   </br>
* <b style="font-family:微软雅黑"><small>HISTORY</small></b></br>
* <b style="font-family:微软雅黑"><small> ID      DATE    PERSON     REASON</small></b><br>
********************************************
* <div style="font-family:微软雅黑,font-size:70%"> 
* 1 2011-5-13 周丹枫  新增
* </div>  
********************************************
 */
public class ExceptionInterceptor extends AbstractInterceptor {

    private static final long serialVersionUID = 2645380772641480245L;
    
    private final Log logger = LogFactory.getLog(getClass());
    
    /**
     * 异常转换
     */
    private IExceptionConvert exceptionConvert;
    

    /**
     * 设置异常转换器
     * setExceptionConvert
     * @param convert
     * @return void
     * @since:
     */
    public void setExceptionConvert(IExceptionConvert convert){
    	this.exceptionConvert = convert;
    }
    
    /**
     * 国际化信息绑定对象
     */
    @Resource
    private IMessageBundle messageBundle;
    
    public IMessageBundle getMessageBundle() {
		return messageBundle;
	}

	public void setMessageBundle(IMessageBundle messageBundle) {
		this.messageBundle = messageBundle;
	}

	/**
     * 异常拦截器初始化方法
     * @see com.deppon.foss.framework.server.web.interceptor.AbstractInterceptor#init()
     * init
     * @since:
     */
    @Override
	public void init() {
	}
    
    /**
     * 异常拦截器的主要方法
     * @see com.opensymphony.xwork2.interceptor.AbstractInterceptor#intercept(com.opensymphony.xwork2.ActionInvocation)
     * intercept
     * @param invocation
     * @return
     * @throws Exception
     * @since:
     */
	@Override
    public String intercept(ActionInvocation invocation) throws Exception {
        try {
            return invocation.invoke();
        } catch (UserNotLoginException e) {
            logger.error(e.getMessage(), e);
            return parseClientLoginResult(e, invocation);
        } catch (AccessNotAllowException e) { 
        	logger.error(e.getMessage(), e);
        	return parseClientErrorResult(e, invocation);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            GeneralException iex = exceptionConvert.convert(e);
            return parseClientErrorResult(iex, invocation);
        }
    }
    
	/*
	 * 转换错误信息为指定的result
	 */
    String parseClientErrorResult(GeneralException iex, ActionInvocation invocation) {
        final String errorCode;
        errorCode = iex.getErrorCode() != null?iex.getErrorCode():"framework.server.exception.general";
    
        final String localizationMsg = messageBundle.getMessage(invocation.getInvocationContext().getLocale(), errorCode, iex.getErrorArguments());
        final String clientContentType = getClientContentType();
        Class<?> clazz = invocation.getAction().getClass();
		String methodName = invocation.getProxy().getMethod();
		Method method = ReflectionUtils.findMethod(clazz, methodName);
		//如果在Action方法上加入JSON的注解，那么就可以在应用这个方法的时候，把错误的结果信息转换为json
        if (Protocol.JSON_CONTENT_TYPE.equalsIgnoreCase(clientContentType) || AnnotationUtils.isAnnotationDeclaredLocally(JSON.class, clazz)
        		|| (method != null && method.isAnnotationPresent(JSON.class))) {
            setJsonResultErrorMessage(localizationMsg);
            return AbstractAction.JSON_ERROR_RESULT;
        } else {
            setJspResultErrorMessage(localizationMsg);
            return AbstractAction.JSP_ERROR_RESULT;
        }
    }
    
    /*
     * 转换登录的错误信息为指定的result
     */
    String parseClientLoginResult(GeneralException iex, ActionInvocation invocation) {
    	final String errorCode;
        errorCode = iex.getErrorCode() != null?iex.getErrorCode():"framework.server.exception.general";
     
        final String localizationMsg = messageBundle.getMessage(invocation.getInvocationContext().getLocale(), errorCode);
        final String clientContentType = getClientContentType();
        Class<?> clazz = invocation.getAction().getClass();
		String methodName = invocation.getProxy().getMethod();
		Method method = ReflectionUtils.findMethod(clazz, methodName);
        if (Protocol.JSON_CONTENT_TYPE.equalsIgnoreCase(clientContentType) || AnnotationUtils.isAnnotationDeclaredLocally(JSON.class, clazz)
        		|| (method != null && method.isAnnotationPresent(JSON.class))) {
            setJsonResultErrorMessage(localizationMsg);
            return AbstractAction.JSON_LOGIN_RESULT;
        } else {
            setJspResultErrorMessage(localizationMsg);
            return AbstractAction.JSP_LOGIN_RESULT;
        }
    }
    
    /*
     * 获得客户端的ContentType
     */
    String getClientContentType() {
    	String clientContentType = ServletActionContext.getRequest().getHeader("content-type");
    	if (clientContentType != null) {
            int iSemicolonIdx;
            if ((iSemicolonIdx = clientContentType.indexOf(";")) != -1)
            	clientContentType = clientContentType.substring(0, iSemicolonIdx);
        }
    	return clientContentType;
    }
    
    /*
     * 设置错误信息，并以JSON格式把错误信息放入request中
     */
    void setJsonResultErrorMessage(String msg) {
        HttpServletRequest request = ServletActionContext.getRequest();
        request.setAttribute("ERROR", "{message:'" + msg + "', success:false,isException:true" + "}");
    }
    
    /*
     * 设置错误信息，以页面转向的形式，把页面返回到之前的页面，并把之前的页面路径（referer，ERROR）放到request中
     */
    void setJspResultErrorMessage(String msg) {
        HttpServletRequest request = ServletActionContext.getRequest();
        request.setAttribute("referer", request.getHeader("Referer"));
        request.setAttribute("ERROR", msg);
    }
    
}