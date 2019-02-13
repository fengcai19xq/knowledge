package com.sky.knowledge.module.framework.shared.exception.finalexception;

import com.sky.knowledge.module.framework.shared.exception.GeneralException;


/**
 * 
 * 
*******************************************
* <b style="font-family:微软雅黑"><small>Description:数据约束错误异常</small></b>   </br>
* <b style="font-family:微软雅黑"><small>HISTORY</small></b></br>
* <b style="font-family:微软雅黑"><small> ID      DATE    PERSON     REASON</small></b><br>
********************************************
* <div style="font-family:微软雅黑,font-size:70%"> 
* 1 2011-4-9 steven.cheng 新增
* </div>  
********************************************
 */
final public class DataIntegrityViolationException extends GeneralException {
    
    private static final long serialVersionUID = 6591089800166168075L;
    
    private final static String ERROR_CODE = "errror.common.DataIntegrityViolationException";
    
    public DataIntegrityViolationException(Throwable t) {
        super(t);
        this.errCode = ERROR_CODE;
        
    }
    
}