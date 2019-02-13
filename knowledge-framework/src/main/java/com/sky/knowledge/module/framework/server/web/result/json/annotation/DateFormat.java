package com.sky.knowledge.module.framework.server.web.result.json.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
*******************************************
* Copyright deppon.com.
* All rights reserved.
* Description:   定义日期格式的Annotation
* HISTORY
*  ID      DATE                PERSON            REASON
********************************************
*  1       2011-3-8             周丹枫      新增
********************************************
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface DateFormat {
	String formate() default "yyyy-MM-dd HH:mm:ss";
}
