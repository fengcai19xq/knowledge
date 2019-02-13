package com.sky.knowledge.module.framework.shared.exception.finalexception;

import com.sky.knowledge.module.framework.shared.exception.GeneralException;


/**
 * 
 * 
*******************************************
* <b style="font-family:微软雅黑"><small>Description:唯一键重复异常</small></b>   </br>
* <b style="font-family:微软雅黑"><small>HISTORY</small></b></br>
* <b style="font-family:微软雅黑"><small> ID      DATE    PERSON     REASON</small></b><br>
********************************************
* <div style="font-family:微软雅黑,font-size:70%"> 
* 1 2011-4-9 steven.cheng 新增
* </div>  
********************************************
 */
final public class DuplicateKeyException extends GeneralException {
    
    private static final long serialVersionUID = 1L;
    public final static String ERROR_CODE = "errror.common.duplicatekey";
    
    public DuplicateKeyException(Throwable t) {
        super(t);
        this.errCode = ERROR_CODE;
    }
    
}
