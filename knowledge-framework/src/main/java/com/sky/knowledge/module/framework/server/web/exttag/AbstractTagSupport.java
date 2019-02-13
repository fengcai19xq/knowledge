package com.sky.knowledge.module.framework.server.web.exttag;

import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.views.jsp.StrutsBodyTagSupport;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;


public abstract class AbstractTagSupport extends StrutsBodyTagSupport {
    
    private static final long serialVersionUID = -719412065578224234L;
    
    @SuppressWarnings("unchecked")
    protected <T> T getBean(String beanName) {
        ServletContext context = ServletActionContext.getServletContext();
        ApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(context);
        return (T) applicationContext.getBean(beanName);
    }
    
    @SuppressWarnings("unchecked")
    protected <T> T getBean(Class<?> claszz) {
        ServletContext context = ServletActionContext.getServletContext();
        ApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(context);
        return (T) applicationContext.getBean(claszz);
    }
    
    protected String getContextPath() {
        ServletContext context = ServletActionContext.getServletContext();
        return context.getContextPath();
    }
    
}