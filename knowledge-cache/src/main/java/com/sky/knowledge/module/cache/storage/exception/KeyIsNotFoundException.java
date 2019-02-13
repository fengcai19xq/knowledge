package com.sky.knowledge.module.cache.storage.exception;
/**
 *  key不存在
 * @description
 * @create xq
 * @date 2014-9-23
 */
public class KeyIsNotFoundException extends RedisCacheStorageException{

	 private static final long serialVersionUID = 5165307445946057734L;
	    
	    public KeyIsNotFoundException(String message) {
	        super(message);
	    }

	    public KeyIsNotFoundException(Throwable e) {
	        super(e);
	    }

	    public KeyIsNotFoundException(String message, Throwable cause) {
	        super(message, cause);
	    }
}
