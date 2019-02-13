package com.sky.knowledge.module.framework.shared.exception;

public class PropertiesLoadException extends RuntimeException{
	private static final long serialVersionUID = -3983003239735523708L;

	public PropertiesLoadException(){
		super();
	}
	
	public PropertiesLoadException(String error){
		super(error);
	}
	
	public PropertiesLoadException(String error,Exception e){
		super(error,e);
	}
}

