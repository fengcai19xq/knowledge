package com.sky.knowledge.module.framework.shared.exception;

public class GeneralException extends RuntimeException implements IException{

	 private static final long serialVersionUID = 5374060474539004523L;
	    protected String errCode;
	    private String nativeMsg;
	    private Object[] arguments;
	    
	    public String getErrorCode() {
	        return errCode;
	    }
	    
	    @Override
	    public void setErrorArguments(Object... args) {
	        this.arguments = args;
	    }
	    
	    @Override
	    public String getNativeMessage() {
	        return this.nativeMsg;
	    }
	    
	    @Override
	    public Object[] getErrorArguments() {
	        return this.arguments;
	    }
	    
	    public GeneralException(String errCode, String message, Throwable cause, Object...arguments) {
	    	super(message, cause);
	    	this.errCode = errCode;
	    	this.arguments = arguments;
	    }
	    
	    public GeneralException(String msg) {
	        super(msg);
	    }
	    
	    public GeneralException() {
	        super();
	    }
	    
	    public GeneralException(String message, Throwable cause) {
	        super(message, cause);
	    }
	    
	    public GeneralException(Throwable cause) {
	        super(cause);
	    }
	    
}
