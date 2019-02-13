package com.sky.knowledge.module.cache.storage.exception;

import com.sky.knowledge.module.cache.exception.GeneralException;

public class RedisCacheStorageException extends GeneralException{

	 private static final long serialVersionUID = 4664623827741256267L;
	    
	    public RedisCacheStorageException(String message) {
	        super(message);
	    }

	    public RedisCacheStorageException(Throwable e) {
	        super(e);
	    }

	    public RedisCacheStorageException(String message, Throwable cause) {
	        super(message, cause);
	    }
}
