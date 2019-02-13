package com.sky.knowledge.module.cache.storage.exception;
/**
 * key存在，value为null
 * @description
 * @create xq
 * @date 2014-9-23
 */
public class ValueIsNullException extends RedisCacheStorageException{

	 private static final long serialVersionUID = 932825584009506614L;

	    public ValueIsNullException(String message) {
	        super(message);
	    }

	    public ValueIsNullException(Throwable e) {
	        super(e);
	    }

	    public ValueIsNullException(String message, Throwable cause) {
	        super(message, cause);
	    }
}
