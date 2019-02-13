package com.sky.knowledge.module.framework.server.context;

import java.util.Locale;

import com.sky.knowledge.module.framework.shared.entity.IUser;


/**
 * 系统用户信息获得的上下文管理
 * 用户信息的ID存放于应用服务器的Session中
 * 通过Session的ID通过缓存获取用户
 * 缓存中没有指定用户信息时，会自动通过Provider去获取信息
 * 用户在缓存中存在的时候受DataReloader决定
 *
 */
public class UserContext {
    
	private final static ThreadLocal<IUser> userStore = new ThreadLocal<IUser>();
	
	private final static ThreadLocal<Locale> userLocale= new ThreadLocal<Locale>();
	
   
    
    /**
     * 获取当前会话的用户
     * 如果没有用户信息返回值为NULL
     * @return
     */
	public static IUser getCurrentUser() {
        return userStore.get();
    }
    
    /**
    */
    
    public static void setCurrentUser(IUser user){
    	userStore.set(user);
    }
    
    public static Locale getUserLocale(){
    	return userLocale.get();
    }
    
    public static void setUserLocale(Locale locale){
    	userLocale.set(locale);
    }
    
    /**
     * 清除ThreadLocal中的数据
     * remove
     * @return void
     * @since:0.6
     */
    public static void remove(){
    	userStore.set(null);
    	userLocale.set(null);
    }
}
