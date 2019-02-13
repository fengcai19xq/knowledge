package com.sky.knowledge.module.framework.server.web.interceptor;


/**
*******************************************
* <b style="font-family:微软雅黑"><small>Description:抽象拦截器类,平台中要定义的拦截器都必须继承它</small></b>   </br>
* <b style="font-family:微软雅黑"><small>HISTORY</small></b></br>
* <b style="font-family:微软雅黑"><small> ID      DATE    PERSON     REASON</small></b><br>
********************************************
* <div style="font-family:微软雅黑,font-size:70%"> 
* 1 2011-5-10 周丹枫  新增
* </div>  
********************************************
 */
public abstract class AbstractInterceptor extends com.opensymphony.xwork2.interceptor.AbstractInterceptor {
    
    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
    
    /**
     * 拦截器的销毁
     * @see com.opensymphony.xwork2.interceptor.AbstractInterceptor#destroy()
     * destroy
     * @since:
     */
    @Override
    public void destroy() {
        
    }
    
    /**
     * 拦截器的实例化
     * @see com.opensymphony.xwork2.interceptor.AbstractInterceptor#init()
     * init
     * @since:
     */
    @Override
    public void init() {
        
    }
    
}
