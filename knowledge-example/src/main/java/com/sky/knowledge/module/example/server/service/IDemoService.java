package com.sky.knowledge.module.example.server.service;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import com.sky.knowledge.module.example.shared.domain.DemoEntity;
/**
 * 业务层
 * @description
 * @create 徐倩
 * @date 2014-7-26
 */
public interface IDemoService {

	public List<DemoEntity> queryFun(DemoEntity entity,int start ,int limit) throws Exception ;
	public int queryFunCount(DemoEntity entity) throws Exception;
	public double queryTotalMoney(DemoEntity entity) throws Exception;
	public int save(List<Object> list);
	public int deleteData(String[] arrayParams)throws Exception;
	public int updateGridData(List<Object> list)throws Exception ;
	public boolean markHandOverStatus(Map paramMap) throws Exception;
	public InputStream getInputStream(DemoEntity entity)throws Exception;
	public List<DemoEntity> exportAllData(DemoEntity entity) throws Exception;
	public int saveSingleData(DemoEntity entity)throws Exception ;
	public DemoEntity querySingleEntity(DemoEntity entity)throws Exception ;
	public int updateSingleEntity(DemoEntity entity)throws Exception ;
	public String importExcel(List<DemoEntity> demoEntityList)throws Exception ;
}
