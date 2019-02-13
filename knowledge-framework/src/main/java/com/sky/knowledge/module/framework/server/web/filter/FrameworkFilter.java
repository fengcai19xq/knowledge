package com.sky.knowledge.module.framework.server.web.filter;
import java.io.IOException;
import java.util.Locale;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.sky.knowledge.module.framework.cache.CacheManager;
import com.sky.knowledge.module.framework.cache.ICache;
import com.sky.knowledge.module.framework.server.context.RequestContext;
import com.sky.knowledge.module.framework.server.context.SessionContext;
import com.sky.knowledge.module.framework.server.context.UserContext;
import com.sky.knowledge.module.framework.server.deploy.struts.ModuleManager;
import com.sky.knowledge.module.framework.server.session.ISession;
import com.sky.knowledge.module.framework.shared.define.Definitions;
import com.sky.knowledge.module.framework.shared.define.Protocol;
import com.sky.knowledge.module.framework.shared.entity.IUser;
public class FrameworkFilter extends DefaultFilter{

    private static ServletContext servletContext;
    
    public static ServletContext getServletContext() {
        return servletContext;
    }
    /**
     * 初始化Filter，导出模块资源
     * @description
     * @create xq
     * @date 2014-6-11
     */
    public void init(FilterConfig config) throws ServletException {
        servletContext = config.getServletContext();
        ModuleManager.export(servletContext);
    }
    /**
     * 设置应用信息
     * doFilter
     * @param request
     * @param response
     * @param filterChain
     * @throws IOException
     * @throws ServletException
     * @since:0.6
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException,
            ServletException {
        /** 取得HttpServletRequest,这里可以拿到HttpSession **/
        HttpServletRequest sreq = (HttpServletRequest) request;
        /** 寻找客户端是否采用Hessian协议,HTTP头部有此定义 **/
        String remoteReqMethod = sreq.getHeader(Protocol.SECURITY_HEADER);
        String remoteReqURL = sreq.getRequestURI();
        String contextPath = sreq.getContextPath();
        /** 去掉应用名称，具体部署的应用名称是可变的 **/
        if (contextPath != null && !"/".equals(contextPath) && remoteReqURL.startsWith(contextPath)) {
            remoteReqURL = remoteReqURL.substring(contextPath.length());
        }
        /** 将当前访问的路径和远程头信息放入权限上下文 **/
        RequestContext.setCurrentContext(remoteReqMethod, remoteReqURL);
        
        /** 会话保留到SessionContext，以便各层使用 **/
        SessionContext.setSession(sreq.getSession(true));
        
        ISession session=SessionContext.getSession();
        //set locale to usercontext
        Locale locale=(Locale)session.getObject(Definitions.KEY_LOCALE);
        //get request locale
        if(locale==null){
        	locale=sreq.getLocale();
        	session.setObject(Definitions.KEY_LOCALE, locale);
        }
        UserContext.setUserLocale(locale);
        //set user to usercontext
        String userId =(String)session.getObject(Definitions.KEY_USER);
        if(userId!=null){
        	ICache<String,IUser> userCache=CacheManager.getInstance().getCache(IUser.class.getName());
        	UserContext.setCurrentUser(userCache.get(userId));
        }
//        
        try{
        	super.doFilter(request, response, filterChain);
        }finally{
        	//清除ThreadLocal中持有的信息
        	SessionContext.remove();
        	RequestContext.remove();
        	UserContext.remove();
        }
        
    }
}
