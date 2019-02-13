package com.sky.knowledge.module.framework.server.dao.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.springframework.stereotype.Repository;

import com.sky.knowledge.module.framework.server.components.ibatis.IBatis3DaoImpl;
import com.sky.knowledge.module.framework.server.dao.IFunctionDao;
import com.sky.knowledge.module.framework.shared.domain.Function;

@Repository
public class FunctionDao extends IBatis3DaoImpl implements IFunctionDao {
	
	/**
	 * 查询最后更新时间
	 * @return
	 */
	
	public Date getLastModifyTime() {
		Date lastModyfyTime = (Date) getSqlSession()
				.selectOne(
						"com.sky.knowledge.module.framework.shared.domain.Function.getLastModifyTime");
		return lastModyfyTime;
	}
	
	/**
	 * 通过URI得到当前URL定位的功能树下的所有功能
	 * @param URI
	 * @return
	 */
	@SuppressWarnings("unchecked")
	
	public Collection<Function> getAllChildFunctionByURI(String URI) {
		Collection<Function> collection = new ArrayList<Function>();
		collection = getSqlSession()
				.selectList(
						"com.sky.knowledge.module.framework.shared.domain.Function.getAllChildFunctionByURI",
						URI);
		return collection;
	}
	
}