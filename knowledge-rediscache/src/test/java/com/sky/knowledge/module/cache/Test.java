package com.sky.knowledge.module.cache;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sky.knowledge.module.cache.server.dao.UserDAO;
import com.sky.knowledge.module.cache.shared.domain.User;

public class Test {

	public static void main(String args[]){
		
		 ApplicationContext ac =  new ClassPathXmlApplicationContext("com/sky/knowledge/module/cache/server/META-INF/spring.xml");
	        UserDAO userDAO = (UserDAO)ac.getBean("userDAO");
	        User user1 = new User();
	        user1.setId(1);
	        user1.setName("obama");
	        userDAO.saveUser(user1);
	        User user2 = userDAO.getUser(1);
	        System.out.println(user2.getName());
	}
}
