package com.sky.knowledge.module.framework.server.dao;

import java.util.Collection;
import java.util.Date;

import com.sky.knowledge.module.framework.shared.domain.Function;



public interface IFunctionDao {
	
	/**
	 * 查询最后更新时间
	 * @return
	 */
	Date getLastModifyTime();

	/**
	 * 通过URI得到当前URL定位的功能树下的所有功能
	 * @param URI
	 * @return
	 */
	Collection<Function> getAllChildFunctionByURI(String URI);
	
}
