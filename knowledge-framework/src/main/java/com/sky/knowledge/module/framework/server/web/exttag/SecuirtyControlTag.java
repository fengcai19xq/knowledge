package com.sky.knowledge.module.framework.server.web.exttag;

import javax.servlet.jsp.JspException;

import com.sky.knowledge.module.framework.server.components.security.SecurityAccessor;
import com.sky.knowledge.module.framework.shared.exception.security.AccessNotAllowException;



/**
 * 页面权限控制Tag
 * 
 * @author 周丹枫
 * 
 */
public class SecuirtyControlTag extends AbstractTagSupport {
    
    /**
	 * 
	 */
    private static final long serialVersionUID = 531462821435846273L;
    /** 按钮对应action */
    private String action;
    
    public String getAction() {
        return action;
    }
    
    public void setAction(String action) {
        this.action = action;
    }
    
    @Override
    public int doEndTag() throws JspException {
        return super.doEndTag();
    }
    
    @SuppressWarnings("static-access")
	@Override
    public int doStartTag() throws JspException {
        
        // 通过spring beanfactory获得权限验证器
        SecurityAccessor securityAccessor = getBean(SecurityAccessor.class);
        String contextPath = getContextPath();
        try {
            securityAccessor.checkURLAccessSecurity(contextPath + "/" + action);
        } catch (AccessNotAllowException e) {
            // 验证不通过隐藏按钮（或菜单）
            return SKIP_BODY;
        }
        return super.doStartTag();
    }
    
}