package com.sky.knowledge.module.framework.server.web.action;


import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.struts2.dispatcher.DefaultActionSupport;

/**
 * 
 * 
*******************************************
* <b style="font-family:微软雅黑"><small>Description:Action基类</small></b>   </br>
* <b style="font-family:微软雅黑"><small>HISTORY</small></b></br>
* <b style="font-family:微软雅黑"><small> ID      DATE    PERSON     REASON</small></b><br>
********************************************
* <div style="font-family:微软雅黑,font-size:70%"> 
* 1 2011-4-9 steven.cheng 新增
* </div>  
********************************************
 */
public class AbstractAction extends DefaultActionSupport {
    
    private static final long serialVersionUID = -187229619606945346L;
    
    /**
     * jsp错误result
     */
    public final static String JSP_ERROR_RESULT = "jspError";
    
    /**
     * json错误result
     */
    public final static String JSON_ERROR_RESULT = "jsonError";
    
    /**
     * jsp登录错误result
     */
    public final static String JSP_LOGIN_RESULT = "jspLogin";
    
    /**
     * json登录错误result
     */
    public final static String JSON_LOGIN_RESULT = "jsonLogin";
    
    public Logger log = LogManager.getLogger(getClass());
    
    /**
     * ext调用错误标识
     */
    private boolean success = true;
    
    /**
     * ext调用异常标识
     */
    private boolean isException = false;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public boolean isException() {
		return isException;
	}

	public void setException(boolean isException) {
		this.isException = isException;
	}
    
}
