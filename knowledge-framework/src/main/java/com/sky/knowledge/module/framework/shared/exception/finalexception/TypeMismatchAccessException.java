package com.sky.knowledge.module.framework.shared.exception.finalexception;

import com.sky.knowledge.module.framework.shared.exception.GeneralException;

/**
 * 
 * 
*******************************************
* <b style="font-family:微软雅黑"><small>Description:类型不匹配异常</small></b>   </br>
* <b style="font-family:微软雅黑"><small>HISTORY</small></b></br>
* <b style="font-family:微软雅黑"><small> ID      DATE    PERSON     REASON</small></b><br>
********************************************
* <div style="font-family:微软雅黑,font-size:70%"> 
* 1 2011-4-9 steven.cheng 新增
* </div>  
********************************************
 */
final public class TypeMismatchAccessException extends GeneralException {
    
    private static final long serialVersionUID = -3526762703628318946L;
    public final static String ERROR_CODE = "errror.common.typemissmatch";
    
    public TypeMismatchAccessException(Throwable t) {
        super(t);
        this.errCode = ERROR_CODE;
    }
    
}
