package com.sky.knowledge.module.framework.server.web.validate;

import com.sky.knowledge.module.framework.shared.exception.GeneralException;


/**
*******************************************
* <b style="font-family:微软雅黑"><small>Description:数据校验异常类</small></b>   </br>
* <b style="font-family:微软雅黑"><small>HISTORY</small></b></br>
* <b style="font-family:微软雅黑"><small> ID      DATE    PERSON     REASON</small></b><br>
********************************************
* <div style="font-family:微软雅黑,font-size:70%"> 
* 
* </div>  
********************************************
 */
public class ValidationException extends GeneralException {
    
    private static final long serialVersionUID = 4660102447165921074L;
    
    /**
     * 
      * 创建一个新的实例 ValidationException.
      * @since
     */
    public ValidationException() {
        super("");
    }
    
    /**
     * 
      * 创建一个新的实例 ValidationException.
      * @param error
      * @since
     */
    public ValidationException(String error) {
        super(error);
    }
    
    /**
     * 
      * 创建一个新的实例 ValidationException.
      * @param error
      * @param e
      * @since
     */
    public ValidationException(String error, Exception e) {
        super(error, e);
    }
}
