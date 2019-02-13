package com.sky.knowledge.module.mongodb.server.util;

import java.util.Date;

import com.sky.knowledge.module.mongodb.shared.domain.LogEntity;

public class LogMongo {
	
	public static boolean debug(Class class1, String request_void,
			String request_data, String func_description, Exception e) {
		// TODO Auto-generated method stub
		if (class1 == null) {
			return false;
		}
		String class_name = class1.getName();
		String module_name = class_name.substring(class_name.indexOf("module.")+7,class_name.indexOf(".", class_name.indexOf("module.")+7));
		LogEntity entity = new LogEntity();
		entity.setClass_name(class_name);
		entity.setModule_name(module_name);
		entity.setRequest_void(request_void);
		entity.setRequest_data(request_data);
		entity.setFunc_description(func_description);
		entity.setException_info(exceptionToString(e));
		entity.setLog_level("DEBUG");
		entity.setCreatetime(new Date());
		MongoHelper.getInstance().saveOne(entity, LogEntity.class);
		
		return false;
	}
	
	private static String exceptionToString(Exception e){
		String str = "";
		if ( e.getMessage() != null) {
			str+= e.getMessage()+"\n";
		}
		for (int i = 0; i < e.getStackTrace().length && i<500; i++) {
			str += e.getStackTrace()[i].toString()+"\n";
		}
		return str;
	}
}
