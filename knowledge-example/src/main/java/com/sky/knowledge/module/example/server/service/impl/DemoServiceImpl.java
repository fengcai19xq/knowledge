package com.sky.knowledge.module.example.server.service.impl;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Service;

import com.sky.knowledge.module.example.server.dao.IDemoDao;
import com.sky.knowledge.module.example.server.service.IDemoService;
import com.sky.knowledge.module.example.shared.domain.DemoEntity;
/**
 * 业务实现层
 * @description
 * @create 徐倩
 * @date 2014-7-26
 */
@Service
public class DemoServiceImpl implements IDemoService{

	/**
	 * 数据层交互接口
	 */
	@Resource
	private IDemoDao demoDao ;
	
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
		
		return demoDao.queryFun(entity, start, limit);
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
	public int queryFunCount(DemoEntity entity) throws Exception {
		// TODO Auto-generated method stub
		return demoDao.queryFunCount(entity);
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
	public double queryTotalMoney(DemoEntity entity) throws Exception {
		// TODO Auto-generated method stub
		return demoDao.queryTotalMoney(entity);
	}
	/**
	 * 批量保存
	 * @description
	 * @create 徐倩
	 * @date 2014-7-26  
	 * @param list
	 * @return
	 */
	public int save(List<Object> list){
		int count = 0;
		for (Object obj : list) {
			DemoEntity entity = (DemoEntity) obj;
			entity.setName(decode2(entity.getName()));
			entity
					.setRemitbank(decode2(entity.getRemitbank()));
			entity.setHandoverstatus(decode2(entity
					.getHandoverstatus()));
			entity.setSurnname(entity.getName().substring(0, 1));
			entity.setLastname(entity.getName().substring(1,entity.getName().length()));
			count += demoDao.save(entity);
		}
		return count ;
	}
	
	public static String decode2(String str) {
		if (StringUtils.isBlank(str)) {
			return "";
		}
		String temp = "";
		try {
			temp = URLDecoder.decode(URLDecoder.decode(str, "utf-8"), "utf-8");
		} catch (Exception e) {
		}
		return temp;
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
		return demoDao.deleteData(arrayParams);
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
	public int updateGridData(List<Object> list)throws Exception {
		int count = 0;
		for (Object obj : list) {
			DemoEntity entity = (DemoEntity) obj;
			entity.setName(decode2(entity.getName()));
			entity
					.setRemitbank(decode2(entity.getRemitbank()));
			entity.setHandoverstatus(decode2(entity
					.getHandoverstatus()));
			entity.setSurnname(entity.getName().substring(0, 1));
			entity.setLastname(entity.getName().substring(1,entity.getName().length()));
			count +=demoDao.updateGridData(entity);
		}
		return count ;
	}
	/**
	 * 单条更新
	 * @description
	 * @create 徐倩
	 * @date 2014-7-28  
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	public int updateSingleEntity(DemoEntity entity)throws Exception {
		entity.setSurnname(entity.getName().substring(0, 1));
		entity.setLastname(entity.getName().substring(1,entity.getName().length()));
		return demoDao.updateGridData(entity);
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
	public boolean markHandOverStatus(Map paramMap) throws Exception{
		return demoDao.markHandOverStatus(paramMap);
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
	public List<DemoEntity> exportAllData(DemoEntity entity) throws Exception{
		
		return demoDao.queryExportData(entity);
	}
	
	/**
	 * 封装导出数据InputStream
	 * @description
	 * @create 徐倩
	 * @date 2014-7-26  
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	public InputStream getInputStream(DemoEntity entity)throws Exception{
		List<DemoEntity> queryList = demoDao.queryExportData(entity);
		
		int count = queryList.size();

		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet hs = wb.createSheet("导出明细表");
		HSSFRow row01 = null;
		HSSFCell cell01 = null;
		// 对明细表excel的写入
		if (count > 0) {
			HSSFCellStyle setBorder = null;
			row01 = hs.createRow(0);

			row01.setHeight((short) 350);
			cell01 = row01.createCell(0);

			hs.setColumnWidth(0, 6000);
			setBorder = wb.createCellStyle();
			setBorder.setFillForegroundColor((short) 13);// 设置背景色
			cell01.setCellStyle(setBorder);
			cell01.setCellValue("编号");
			HSSFCell cell02 = row01.createCell(1);

			hs.setColumnWidth(1, 6000);
			setBorder = wb.createCellStyle();
			setBorder.setFillForegroundColor((short) 13);// 设置背景色
			cell02.setCellStyle(setBorder);
			cell02.setCellValue("姓名");
			HSSFCell cell03 = row01.createCell(2);

			hs.setColumnWidth(2, 6000);
			setBorder = wb.createCellStyle();
			setBorder.setFillForegroundColor((short) 13);// 设置背景色
			cell03.setCellStyle(setBorder);
			cell03.setCellValue("收款银行");
			HSSFCell cell04 = row01.createCell(3);

			hs.setColumnWidth(3, 6000);
			setBorder = wb.createCellStyle();
			setBorder.setFillForegroundColor((short) 13);// 设置背景色
			cell04.setCellStyle(setBorder);
			cell04.setCellValue("收款日期");
			HSSFCell cell05 = row01.createCell(4);

			hs.setColumnWidth(4, 6000);
			setBorder = wb.createCellStyle();
			setBorder.setFillForegroundColor((short) 13);// 设置背景色
			cell05.setCellStyle(setBorder);
			cell05.setCellValue("交付状态");
			HSSFCell cell06 = row01.createCell(5);

			hs.setColumnWidth(5, 4000);
			setBorder = wb.createCellStyle();
			setBorder.setFillForegroundColor((short) 13);// 设置背景色
			cell06.setCellStyle(setBorder);
			cell06.setCellValue("收款金额");

			int i = 0;
			int j = 1;
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
			for (DemoEntity d : queryList) {
				row01 = hs.createRow(i + 1);
				cell01 = row01.createCell(0);
				cell01.setCellValue(d.getBillnum());
				cell01 = row01.createCell(1);
				cell01.setCellValue(d.getName());
				cell01 = row01.createCell(2);
				cell01.setCellValue(d.getRemitbank());
				cell01 = row01.createCell(3);
				cell01.setCellValue(sf.format(d.getRemitdate()));
				cell01 = row01.createCell(4);
				cell01.setCellValue(d.getHandoverstatus());
				cell01 = row01.createCell(5);
				cell01.setCellValue(d.getMoney());
				i++;
				j++;
			}
		}

		File file = new File("export.xls");
		OutputStream os = new FileOutputStream(file);
		try {
			wb.write(os);// 将excel写入到输流
		} catch (Exception e) {
			e.printStackTrace();
		}
		InputStream is = null;
		try {
			is = new FileInputStream(file);// 将excel读入到输入流中
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			os.close();
			return is;
		}
	}
	
	/**
	 * 保存单条数据
	 * @description
	 * @create 徐倩
	 * @date 2014-7-28  
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	public int saveSingleData(DemoEntity entity)throws Exception {
		entity.setSurnname(entity.getName().substring(0, 1));
		entity.setLastname(entity.getName().substring(1,entity.getName().length()));
		return demoDao.save(entity);
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
		return demoDao.querySingleEntity(entity);
	}
	/**
	 * 批量导入数据
	 * @description
	 * @create xq
	 * @date 2014-10-18
	 */
	public String importExcel(List<DemoEntity> demoEntityList)throws Exception {
		if(null ==demoEntityList ||demoEntityList.size()<=0){
			return "导入excel的数据为空!";
		}else{
			List<String> billnumList = new ArrayList<String>();//用于去除excel中重复的编号
			List<DemoEntity> insertList = new ArrayList<DemoEntity>(); //插入数据库的list
			for(DemoEntity de: demoEntityList){
				if(billnumList.contains(de.getBillnum())){
					continue ;
				}else{
					billnumList.add(de.getBillnum());//excel重复的编号，默认取第一条
					boolean isFlag = demoDao.validateBillnum(de.getBillnum());
					if(isFlag){//判断是否在数据库中已经存在
						return "编号："+de.getBillnum()+"已在数据库中存在，请重新核对数据！";
					}else{
						de.setSurnname(de.getName().substring(0, 1));
						de.setLastname(de.getName().substring(1,de.getName().length()));
						insertList.add(de);
					}
				}
			}
			
			  int ii = insertList.size()%1000;
			  int m = insertList.size()/1000;
			  int length = 0;
			  int num = 0;
			  List<DemoEntity> spiltList = new ArrayList<DemoEntity>(); //每次分割的list
			  for(int j = 0;j<=m;j++){
				  spiltList = insertList.subList(length,j*1000+ii);
			   		if(spiltList.size()<=0){
			   			continue ;
			   		}
			   	    // 插入数据库
			   		num += demoDao.importExcel(spiltList);
			   		length = j*1000+ii ;
			  } 
			 
		}
		return "";
	}
}
