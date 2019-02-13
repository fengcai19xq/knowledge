package com.sky.knowledge.module.example.server.dao.impl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.sky.knowledge.module.example.server.dao.IDemoDao;
import com.sky.knowledge.module.example.shared.domain.DemoEntity;
import com.sky.knowledge.module.framework.server.components.ibatis.IBatis3DaoImpl;
/**
 * 数据交互层
 * @description
 * @create 徐倩
 * @date 2014-7-26
 */
@Repository
@SuppressWarnings("unchecked")
public class DemoDaoImpl extends IBatis3DaoImpl implements IDemoDao{

	private final String sqlMapper = "com.sky.knowledge.module.example.shared.domain.DemoEntity.";
	/**
	 * 数据查询
	 * @description
	 * @create 徐倩
	 * @date 2014-7-26  
	 * @param entity
	 * @param start
	 * @param limit
	 * @return
	 * @throws Exception
	 */
	public List<DemoEntity> queryFun(DemoEntity entity,int start ,int limit) throws Exception {
		RowBounds rowBounds = new RowBounds(start, limit);
		return getSqlSession().selectList(sqlMapper+"queryFun", entity,rowBounds);
	}
	/**
	 * 查询数据个数
	 * @description
	 * @create 徐倩
	 * @date 2014-7-26  
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public int queryFunCount(DemoEntity entity) throws Exception {

		return (Integer) getSqlSession().selectOne(sqlMapper+"queryFunCount", entity);

	}
	/**
	 * 查询总金额
	 * @description
	 * @create 徐倩
	 * @date 2014-7-26  
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public double queryTotalMoney(DemoEntity entity) throws Exception {
		Object obj = getSqlSession().selectOne(sqlMapper+"queryTotalMoney",
				entity);
		return obj==null?0:(Double)obj;
	}
	/**
	 * 保存
	 * @description
	 * @create 徐倩
	 * @date 2014-7-26  
	 * @param list
	 * @return
	 */
	public int save(DemoEntity demoEntity){
		return getSqlSession().insert(sqlMapper+"save", demoEntity);
	}
	/**
	 * 批量删除
	 * @description
	 * @create 徐倩
	 * @date 2014-7-26  
	 * @param arrayParams
	 * @return
	 * @throws Exception
	 */
	public int deleteData(String[] arrayParams)throws Exception{
		Map<String,String[]> parMap = new HashMap<String,String[]>();
		parMap.put("arrayParams",arrayParams);
		return getSqlSession().delete(sqlMapper+"deleteData", parMap);
	}
	/**
	 * 批量更新
	 * @description
	 * @create 徐倩
	 * @date 2014-7-26  
	 * @param list
	 * @return
	 * @throws Exception
	 */
	public int updateGridData(DemoEntity entity)throws Exception {
		
		return getSqlSession().update(sqlMapper+"updateGridData", entity);
	}
	/**
	 * 标记
	 * @description
	 * @create 徐倩
	 * @date 2014-7-26  
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	public boolean markHandOverStatus(Map paramMap) throws Exception {
		// TODO Auto-generated method stub
		boolean isFlag = false;
		int count = getSqlSession().update(
				sqlMapper+"markHandOverStatus",
				paramMap);
		if (count >= 1) {
			isFlag = true;
		}

		return isFlag;
	}
	/**
	 * 查询要导出数据
	 * @description
	 * @create 徐倩
	 * @date 2014-7-26  
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	public List<DemoEntity> queryExportData(DemoEntity entity) throws Exception {
		return  getSqlSession().selectList(sqlMapper+"queryFun", entity);
	}
	/**
	 * 查询单一实体类
	 * @description
	 * @create 徐倩
	 * @date 2014-7-28  
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	public DemoEntity querySingleEntity(DemoEntity entity)throws Exception {
	
		return (DemoEntity)getSqlSession().selectOne(sqlMapper+"queryFun", entity);
	}
	/**
	 * 校验billnum是否在数据库中存在
	 * @description
	 * @create xq
	 * @date 2014-10-18
	 */
	public boolean validateBillnum(String _parameter)throws Exception{
		return ((Integer)getSqlSession().selectOne(sqlMapper+"validateBillnum", _parameter))>0?true:false;
	}
	/**
	 * 批量导入数据
	 * @description
	 * @create xq
	 * @date 2014-10-18
	 */
    public int importExcel(List<DemoEntity> demoEntityList)throws Exception {
    	Map paramMap = new HashMap();
		paramMap.put("demoEntityList", demoEntityList);
		return getSqlSession().insert(sqlMapper+"importExcel",paramMap);
	}


}
