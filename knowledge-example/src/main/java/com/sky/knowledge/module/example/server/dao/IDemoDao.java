package com.sky.knowledge.module.example.server.dao;
import java.util.List;
import java.util.Map;

import com.sky.knowledge.module.example.shared.domain.DemoEntity;

public interface IDemoDao {

	public List<DemoEntity> queryFun(DemoEntity entity,int start ,int limit) throws Exception ;
	public int queryFunCount(DemoEntity entity) throws Exception;
	public double queryTotalMoney(DemoEntity entity) throws Exception;
	public int save(DemoEntity demoEntity);
	public int deleteData(String[] arrayParams)throws Exception;
	public int updateGridData(DemoEntity entity)throws Exception ;
	public boolean markHandOverStatus(Map paramMap) throws Exception;
	public List<DemoEntity> queryExportData(DemoEntity entity) throws Exception ;

	public DemoEntity querySingleEntity(DemoEntity entity)throws Exception ;
	
	public int importExcel(List<DemoEntity> demoEntityList)throws Exception ;
	
	public boolean validateBillnum(String billnum)throws Exception;

}
