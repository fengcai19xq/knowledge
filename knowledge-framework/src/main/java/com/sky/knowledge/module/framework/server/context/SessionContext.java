package com.sky.knowledge.module.framework.server.context;

import java.util.Locale;

import com.sky.knowledge.module.framework.server.session.ISession;
import com.sky.knowledge.module.framework.server.session.Session;
import com.sky.knowledge.module.framework.shared.define.Definitions;

public class SessionContext {
	 /**
     * 初始化session上下文
     */
    private final static ThreadLocal<ISession<Object>> safeSession = new ThreadLocal<ISession<Object>>() {
        @Override
        protected ISession<Object> initialValue() {
            return new Session<Object>();
        }
        
    };
    
    private SessionContext() {
        
    }
    
    public static ISession<Object> getSession() {
        return safeSession.get();
    }
    
    /**
     * 设置真是session
     * setSession
     * @param session
     * @return void
     * @since:0.7
     */
    public static void setSession(javax.servlet.http.HttpSession session) {
        ISession<Object> sessionHold = safeSession.get();
        sessionHold.init(session);   
    }
    
    /**
     * 清除ThreadLocal
     * remove
     * @return void
     * @since:0.7
     */
    public static void remove() {
    	safeSession.get().init(null);
    }
    
    /**
     * 设置User
     * setCurrentUser
     * @param user
     * @return void
     * @since:0.7
     */
    public static void setCurrentUser(String user){
    	safeSession.get().setObject(Definitions.KEY_USER, user);
    }
    
    /**
     * 设置Locale
     * setUserLocale
     * @param userLocale
     * @return void
     * @since:0.7
     */
    public static void setUserLocale(Locale userLocale){
    	safeSession.get().setObject(Definitions.KEY_LOCALE, userLocale);
    }
    
    /**
     * 使session失效
     * invalidateSession
     * @return void
     * @since:0.7
     */
    public static void invalidateSession() {
    	safeSession.get().invalidate();
    }
    
}
