package com.sky.knowledge.module.cache.storage.exception;
/**
 * Redis连接异常
 * @description
 * @create xq
 * @date 2014-9-23
 */
public class RedisConnectionException extends RedisCacheStorageException{

	 private static final long serialVersionUID = -4269004402633873780L;
	    
	    public RedisConnectionException(String message) {
	        super(message);
	    }

	    public RedisConnectionException(Throwable e) {
	        super(e);
	    }

	    public RedisConnectionException(String message, Throwable cause) {
	        super(message, cause);
	    }
}
