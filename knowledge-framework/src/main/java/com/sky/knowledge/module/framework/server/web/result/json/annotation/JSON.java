package com.sky.knowledge.module.framework.server.web.result.json.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
*******************************************
* Copyright deppon.com.
* All rights reserved.
* Description:   JSON格式的Annotation
* HISTORY
*  ID      DATE                PERSON            REASON
********************************************
*  1       2011-3-8             周丹枫      新增
********************************************
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface JSON {
	
}