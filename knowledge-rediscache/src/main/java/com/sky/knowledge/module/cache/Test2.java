package com.sky.knowledge.module.cache;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sky.knowledge.module.cache.server.util.RedisClientTemplate;

public class Test2 {

	public static void main(String args[]){
		 ApplicationContext ac =  new ClassPathXmlApplicationContext("com/sky/knowledge/module/cache/server/META-INF/data-source.xml");
	     RedisClientTemplate redisClient = (RedisClientTemplate)ac.getBean("redisClientTemplate");
	     redisClient.set("a","2");
	     
	     System.out.println(redisClient.get("a"));
	}
}
