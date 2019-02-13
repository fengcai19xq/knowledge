package com.sky.knowledge.module.framework.server.components.security;

import java.util.Set;

import com.sky.knowledge.module.framework.cache.CacheManager;
import com.sky.knowledge.module.framework.cache.ICache;
import com.sky.knowledge.module.framework.server.context.RequestContext;
import com.sky.knowledge.module.framework.server.context.UserContext;
import com.sky.knowledge.module.framework.shared.entity.IFunction;
import com.sky.knowledge.module.framework.shared.entity.IRole;
import com.sky.knowledge.module.framework.shared.entity.IUser;
import com.sky.knowledge.module.framework.shared.exception.security.AccessNotAllowException;
import com.sky.knowledge.module.framework.shared.exception.security.FunctionNotValidException;
import com.sky.knowledge.module.framework.shared.exception.security.UserNotLoginException;


/**
 * 
 * 
*******************************************
* <b style="font-family:微软雅黑"><small>Description:权限访问控制器</small></b>   </br>
* <b style="font-family:微软雅黑"><small>HISTORY</small></b></br>
* <b style="font-family:微软雅黑"><small> ID      DATE    PERSON     REASON</small></b><br>
********************************************
* <div style="font-family:微软雅黑,font-size:70%"> 
* 1  2011-3-27  steven.cheng 新增
* </div>  
********************************************
 */
public class SecurityAccessor {
    
    private SecurityAccessor() {
    }
    
    /**
     * 读取request头里的method，并判断与实际调用method是否一致，不一致抛出异常
     * checkMethodAccessSecurity
     * @param methodName
     * @return void
     * @since:0.6
     */
    public static void checkMethodAccessSecurity(String methodName) {
        final String protocolHeader = RequestContext.getCurrentContext().getRemoteRequestMethod();
        if (null == protocolHeader)
            return;
        if (!protocolHeader.equalsIgnoreCase(methodName)) {
            // Client forge method invoke url
            throw new AccessNotAllowException();
        }
    }
    
    /**
     * 校验权限
     * checkURLAccessSecurity
     * @param accessURL
     * @return void
     * @since:0.6
     */
    public static void checkURLAccessSecurity(String accessURL) {
        @SuppressWarnings("unchecked")
        ICache<String, IFunction> functionContext = CacheManager.getInstance().getCache(IFunction.class.getName());
        @SuppressWarnings("unchecked")
        ICache<String, IRole> roleContext = CacheManager.getInstance().getCache(IRole.class.getName());
        
        IUser user = UserContext.getCurrentUser();
        //用户未登录
        if (user == null) {
            throw new UserNotLoginException();
        }
        
        IFunction function = functionContext.get(accessURL);
        //缓存中不存在该功能，则不需校验权限
        if (null == function) {
            return; // Not Require Checked
        }
        //功能已经被禁用
        if (!function.getValidFlag()) {
        	throw new FunctionNotValidException();
        }

        
        Set<String> roleIds = user.getRoleids();
        
        // 是否拥有访问权限
        boolean isAccess = false;
        for (String roleId : roleIds) {
            IRole realRole = roleContext.get(roleId);
            if (null == realRole) {
                continue;
            }
            if (realRole.getFunctionIds().contains(function.getId())) {
                // use not configurate this module
                isAccess = true;
                break;
            }
        }
        if (!isAccess)
            throw new AccessNotAllowException();
    }
    
    /**
     * 判断请求是否被允许
     * hasAccessSecurity
     * @param accessURL
     * @return
     * @return boolean
     * @since:0.7
     */
    public static boolean hasAccessSecurity(String accessURL) {
        try {
        	checkURLAccessSecurity(accessURL);
        	return true;
        } catch (Throwable t) {
        	return false;
        }
    }
    
}