package com.sky.knowledge.module.framework.shared.exception.components;

import com.sky.knowledge.module.framework.shared.exception.GeneralException;


public interface IExceptionConvert {
    /**
     * 转化成GeneralException
     * convert
     * @param target
     * @return
     * @return GeneralException
     * @since:0.6
     */
    GeneralException convert(Throwable target);
    
    /**
     * 转化成Exception
     * nativeConvert
     * @param target
     * @return
     * @return Exception
     * @since:0.6
     */
    Exception nativeConvert(Throwable target);
}