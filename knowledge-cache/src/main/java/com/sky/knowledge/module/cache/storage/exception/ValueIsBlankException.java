package com.sky.knowledge.module.cache.storage.exception;
/**
 *  key存在，value为空
 * @description
 * @create xq
 * @date 2014-9-23
 */
public class ValueIsBlankException extends RedisCacheStorageException{

    private static final long serialVersionUID = 5536157410092139146L;
    
    public ValueIsBlankException(String message) {
        super(message);
    }

    public ValueIsBlankException(Throwable e) {
        super(e);
    }

    public ValueIsBlankException(String message, Throwable cause) {
        super(message, cause);
    }
}
