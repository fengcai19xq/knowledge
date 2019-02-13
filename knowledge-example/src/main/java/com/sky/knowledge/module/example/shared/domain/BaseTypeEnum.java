package com.sky.knowledge.module.example.shared.domain;
/**
 * 基本类型枚举类
 * @description
 * @create 徐倩
 * @date 2014-7-28
 */
public enum BaseTypeEnum {

	operate_save("add"),
	operate_modify("modify");
	
	private String name ;
	
	BaseTypeEnum(){
		
	}
     BaseTypeEnum(String name){
    	 this.name = name ;
	}
	public void set(String name){
		this.name = name ;
	}
	
	public String getName(){
		return name ;
	}
}
