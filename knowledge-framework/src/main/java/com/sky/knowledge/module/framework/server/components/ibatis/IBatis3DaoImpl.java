package com.sky.knowledge.module.framework.server.components.ibatis;

import java.util.concurrent.atomic.AtomicBoolean;

import org.mybatis.spring.support.SqlSessionDaoSupport;

public class IBatis3DaoImpl extends SqlSessionDaoSupport implements IBatis3Dao{

	private static AtomicBoolean flag = new AtomicBoolean();


	 
//	@Override
//	protected void checkDaoConfig() {
//		super.checkDaoConfig();
//		/**
//		 * 由于mybatis对mapper文件使用了延迟加载
//		 * 在多线程情况下如果多个线程同时加载mapper文件会抛出异常
//		 */
//		if (!flag.get()) {
//			//加载所有mapper文件
//			getSqlSession().getConfiguration().buildAllStatements(); 
//			flag.set(true);
//		}
//		
//	}
}
