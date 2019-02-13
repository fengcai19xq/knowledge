package com.sky.knowledge.module.framework.server.dao.impl;

import java.util.Collection;
import java.util.Date;

import org.springframework.stereotype.Repository;

import com.sky.knowledge.module.framework.server.components.ibatis.IBatis3DaoImpl;
import com.sky.knowledge.module.framework.server.dao.IBaseDataDao;
import com.sky.knowledge.module.framework.shared.domain.BaseData;
/**
 * 查询数据库 基准资料数据
 * @description
 * @create xq
 * @date 2014-11-11
 */
@Repository
public class BaseDataDao extends IBatis3DaoImpl  implements IBaseDataDao{

	private String sqlMapper = "com.sky.knowledge.module.framework.shared.domain.BaseData.";
	/**
	 * 查询最后更新时间
	 * @description
	 * @create xq
	 * @date 2014-11-11
	 */
	@Override
	public Date getLastModifyTime() {
		// TODO Auto-generated method stub
		return (Date)getSqlSession().selectOne(sqlMapper+"getLastModifyTime");
	}

	/**
	 * 获取系统所有基础资料
	 * @description
	 * @create xq
	 * @date 2014-11-11
	 */
	@Override
	public Collection<BaseData> getAll() {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(sqlMapper+"getAll");
	}

}
