/**
 * Project Name:FIMS-Interfaces
 * File Name:Server.java
 * Package Name:com.deppon.fims.inter.demo.service
 * Date:2014-10-11上午10:50:41
 * Copyright (c) 2014 All Rights Reserved.
 */

package org.sky.knowledge.module.restful.server;

import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.sky.knowledge.module.restful.server.service.impl.DemoRestServiceImpl;

/**
 * @ClassName:Server <br/>
 * @Title: TODO 用一句话说明. <br/>
 * @author:  215209
 * @Date: 2014-10-11 上午10:50:41
 */
public class Server {
	public static void main(String[] args) {
		//基于restful
		 JAXRSServerFactoryBean bean = // 声明JAXRS服务对象
		 new JAXRSServerFactoryBean();
		 bean.setServiceBean(new DemoRestServiceImpl());// 加载服务类
		 bean.setAddress("http://localhost:9004/");// 声明地址，注意只声明地址和端口即可
		 bean.getInInterceptors().add(new LoggingInInterceptor());
		 bean.getOutInterceptors().add(new LoggingOutInterceptor());
		 bean.create();// 启动
		 System.err.println("JAX-RS 启动成功....");
		/*
		//基于wsdl
		JaxWsServerFactoryBean bean = new JaxWsServerFactoryBean();
        //服务的地址
        bean.setAddress("http://127.0.0.1:7788/rest");
        //提供服务的类的类型
        bean.setServiceClass(DemoRestServiceImpl.class);
        //提供服务的实例
        bean.setServiceBean(new DemoRestServiceImpl());
        //发布服务  publish()...
        bean.create();
        System.out.println("server ready...");*/
    }  
}
