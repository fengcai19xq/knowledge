package com.sky.knowledge.module.framework.server.adapter.jms.convert;

import com.sky.knowledge.module.framework.shared.exception.GeneralException;


/**
 * 
 * 
*******************************************
* <b style="font-family:微软雅黑"><small>Description:jms消息转换异常</small></b>   </br>
* <b style="font-family:微软雅黑"><small>HISTORY</small></b></br>
* <b style="font-family:微软雅黑"><small> ID      DATE    PERSON     REASON</small></b><br>
********************************************
* <div style="font-family:微软雅黑,font-size:70%"> 
*  1 2011-3-8 vincent.chen 新增
* </div>  
********************************************
 */
public class ConvertException extends GeneralException {
    
    /**
     * 
     */
    private static final long serialVersionUID = -9038516873786772247L;

    public ConvertException() {
        super();
        // TODO Auto-generated constructor stub
    }

    public ConvertException(String message, Throwable cause) {
        super(message, cause);
        // TODO Auto-generated constructor stub
    }

    public ConvertException(String msg) {
        super(msg);
        // TODO Auto-generated constructor stub
    }

    public ConvertException(Throwable cause) {
        super(cause);
        // TODO Auto-generated constructor stub
    }
    
    
    
}
