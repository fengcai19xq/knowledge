package com.sky.knowledge.module.cache.exception;
/**
 * 异常接口
 * @description
 * @create xq
 * @date 2014-9-23
 */
public interface IException {

	    String getErrorCode();
	    
	    String getNativeMessage();
	    
	    void setErrorArguments(Object... objects);
	    
	    Object[] getErrorArguments();
}
