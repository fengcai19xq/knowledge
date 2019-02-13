package com.sky.knowledge.module.example.server.action;

import java.io.File;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.ezmorph.object.DateMorpher;
import net.sf.json.util.JSONUtils;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.StrutsStatics;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.sky.knowledge.module.common.server.util.DlpUtil;
import com.sky.knowledge.module.common.server.util.ExcelUtil;
import com.sky.knowledge.module.common.server.util.JsonUtil;
import com.sky.knowledge.module.common.server.util.PrintWriteUtil;
import com.sky.knowledge.module.common.shared.domain.excel.ExcelColumn;
import com.sky.knowledge.module.common.shared.domain.excel.ExcelHead;
import com.sky.knowledge.module.example.server.service.IDemoService;
import com.sky.knowledge.module.example.shared.domain.BaseTypeEnum;
import com.sky.knowledge.module.example.shared.domain.DemoEntity;
import com.sky.knowledge.module.framework.cache.CacheManager;
import com.sky.knowledge.module.framework.cache.ICache;
import com.sky.knowledge.module.framework.server.context.UserContext;
import com.sky.knowledge.module.framework.server.web.action.AbstractAction;
import com.sky.knowledge.module.framework.shared.domain.BaseData;
import com.sky.knowledge.module.framework.shared.domain.User;
import com.sky.knowledge.module.framework.shared.entity.IBaseData;
import com.sky.knowledge.module.mongodb.server.util.LogMongo;
import com.sky.knowledge.module.mongodb.server.util.MongoHelper;

/**
 * demo例子
 * @description
 * @create 徐倩
 * @date 2014-7-26
 */
@Controller
@Scope("prototype")
public class DemoAction extends AbstractAction{

	
	/**
	 * 类标识
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 每页显示条数
	 */
	private int limit = 10;// 值来源于store中的pageSize设置的大小

	/**
	 * 起始条数
	 */
	private int start;
	/**
	 * 查询总条数
	 */
	private int totalCount;

	// 存放编码字符串
	private String codeNums;
	// 导出文件名
	private String fileName;
	// 导出excel
	private InputStream excelStream;

	// 实体类
	private DemoEntity demoEntity;
	
    private List<DemoEntity> demoEntityList;
	/**
	 * 总金额
	 */
	private double totalMoney;
	
	/**
	 * nonBLS json封装的保存的数据
	 */
	private String nonBLS;

	private boolean mark_flag;// 用于判断标记是否成功
	
	@Resource
	private IDemoService demoSerivce ;
	
	/**
	 * 用于判断操作类型
	 */
	private String operateType ; 
	/**
	 * 记录提示信息
	 */
	private String msg = "";
	/**
	 * 导入文件
	 */
	private File excelFile ;
	/**
	 * 数据查询
	 * @description
	 * @create 徐倩
	 * @date 2014-7-26  
	 * @return
	 * @throws Exception
	 */
	public String queryFun()throws Exception{
		// 得到当前用户
		User user = (User) UserContext.getCurrentUser();
		if (user == null) {
			throw new Exception("当前登陆用户为空");
		}
		
		ICache<String,IBaseData> basedataCache = CacheManager.getInstance().getCache(IBaseData.class.getName());
		Map<String, IBaseData> map = basedataCache.get();
		for(String str : map.keySet()){
			System.out.println(str+","+((BaseData)map.get(str)).getTypename());
		}
		demoEntityList = demoSerivce.queryFun(demoEntity, start, limit);
		
		totalCount = demoSerivce.queryFunCount(demoEntity);
		
		totalMoney = demoSerivce.queryTotalMoney(demoEntity);
		
		getSession().setAttribute("demoEntity",demoEntity);
		
		return SUCCESS;
	}
	/**
	 * 查询单一实体类
	 * @description
	 * @create 徐倩
	 * @date 2014-7-28  
	 * @return
	 */
	public String querySingleEntity(){
		try{
			demoEntity = demoSerivce.querySingleEntity(demoEntity);
		     throw new Exception();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			msg = "根据编号查询信息失败！";
			LogMongo.debug(DemoAction.class, "querySingleEntity", demoEntity.getBillnum(), "根据编号查询信息失败！", e);
		}
		
		return SUCCESS ;
	}
	/**
	 * 批量保存数据
	 * @description
	 * @create 徐倩
	 * @date 2014-7-26  
	 * @return
	 * @throws Exception
	 */
	public String save() throws Exception {
		String[] dateFormats = new String[] { "yyyy-MM-dd" };
		JSONUtils.getMorpherRegistry().registerMorpher(
				new DateMorpher(dateFormats));

		List<Object> listObj = JsonUtil.jsonStrToList(nonBLS,
				DemoEntity.class);
		try {
			demoSerivce.save(listObj);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			LogMongo.debug(DemoAction.class, "save", nonBLS, "批量保存数据失败！", e);

		}

		return SUCCESS;
	}
	/**
	 * 批量删除
	 * @description
	 * @create 徐倩
	 * @date 2014-7-26  
	 * @return
	 */
	public String deleteData(){
		if(demoEntity!=null &&demoEntity.getArrayParams().length>0){
			try {
				demoSerivce.deleteData(demoEntity.getArrayParams());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				LogMongo.debug(DemoAction.class, "deleteData", demoEntity.getBillnum(), "批量删除数据失败！", e);

			}	
		}
		
		return SUCCESS ;
	}

	/**
	 * 批量更新数据
	 * @description
	 * @create 徐倩
	 * @date 2014-7-26  
	 * @return
	 */
	public String updateGridData(){
		List<Object> listObj = JsonUtil.jsonStrToList(nonBLS,
				DemoEntity.class);
		if(listObj.size()>0){
			try {
				demoSerivce.updateGridData(listObj);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return SUCCESS ;
	}
	/**
	 * 标记
	 * @description
	 * @create 徐倩
	 * @date 2014-7-26  
	 * @return
	 * @throws Exception
	 */
	public String markHandOverStatus() throws Exception {
		HttpServletRequest request = (HttpServletRequest) ActionContext
		.getContext().get(StrutsStatics.HTTP_REQUEST);
       String m_billnum = request.getParameter("m_billNum");
       String m_handOverStatus = request.getParameter("m_handOverStatus");
       Map<String,String> paramMap = new HashMap<String,String>();
       paramMap.put("billnum", m_billnum);
       paramMap.put("handOverStatus", m_handOverStatus);
       
       mark_flag = demoSerivce.markHandOverStatus(paramMap);
		
      return SUCCESS ;
	}
	/**
	 * 保存单条新增记录
	 * @description
	 * @create 徐倩
	 * @date 2014-7-28  
	 * @return
	 */
	public String saveSingleData(){
		if(demoEntity!=null){
			try{
				int i = 0 ;
				if(BaseTypeEnum.operate_save.getName().equals(operateType)){
					i = demoSerivce.saveSingleData(demoEntity);
				}else if(BaseTypeEnum.operate_modify.getName().equals(operateType)){
					i = demoSerivce.updateSingleEntity(demoEntity);
				}
				
				if(i<=0){
					msg="保存数据记录为0";
				}
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				msg = "保持数据失败:"+e.getMessage();
				LogMongo.debug(DemoAction.class, "saveSingleData", demoEntity.toString(), "保存单条新增记录失败！", e);

			}
		}else{
			msg="传入实体类为空！";
		}
		return SUCCESS ;
	}
	
	/**
	 * 导出excel数据
	 * @description
	 * @create 徐倩
	 * @date 2014-7-26  
	 * @return
	 */
	public String exportAllData(){
		try {
			// 从session获取当前用户的查询条件
			DemoEntity demoEntity = (DemoEntity)getSession().getAttribute("demoEntity");
			//
			fileName = "明细表.xls";
			fileName = new String(fileName.getBytes("UTF-8"), "UTF-8");
//			fileName = java.net.URLEncoder.encode(fileName, "UTF-8"); // 这句很重要，不然文件名为乱码

			excelStream = demoSerivce.getInputStream(
					demoEntity);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS ;
	}
	/**
	 * 导出excel数据
	 * @description
	 * @create 徐倩
	 * @date 2014-7-26  
	 * @return
	 */
	public String exportExcel(){
		
		try {
			// 从session获取当前用户的查询条件
			DemoEntity demoEntity = (DemoEntity)getSession().getAttribute("demoEntity");
			
			List<DemoEntity> list = demoSerivce.exportAllData(demoEntity);
			// 定义数据字段对应的Excel中的所在列和列名
			List<ExcelColumn> columns = new ArrayList<ExcelColumn>();
			columns.add(new ExcelColumn(0, "billnum", "编号"));
			columns.add(new ExcelColumn(1, "name", "姓名"));
			columns.add(new ExcelColumn(2, "remitbank", "收款银行"));
			columns.add(new ExcelColumn(3, "remitdate", "收款日期"));
			columns.add(new ExcelColumn(4, "handoverstatus", "交付状态"));
			columns.add(new ExcelColumn(5, "money", "收款金额"));
			
			Map<String,List<ExcelColumn>> megreCell = new HashMap<String,List<ExcelColumn>>();
			List<ExcelColumn> columns2 = new ArrayList<ExcelColumn>();
			columns2.add(new ExcelColumn(1, "surnname", "姓"));
			columns2.add(new ExcelColumn(2, "lastname", "名"));
			megreCell.put("name",columns2);
			
			ExcelHead head = new ExcelHead();
			head.setColumns(columns);
//			head.setColspan(columns2);
			try {
				fileName = URLEncoder.encode(
						"明细.xlsx", "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			// 生成Excel文件流
			excelStream = ExcelUtil.getInstanse().exportExcelInputStream(
					head, "明细表", list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS ;
	}
	/**
	 * 导入excel
	 * @description
	 * @create xq
	 * @date 2014-10-17
	 */
	public String importExcel()throws Exception{
    	List<ExcelColumn> excelColumns = new ArrayList<ExcelColumn>();
		excelColumns.add(new ExcelColumn(0, "billnum", String.class));
		excelColumns.add(new ExcelColumn(1, "name", String.class));
		excelColumns.add(new ExcelColumn(2, "remitbank", String.class));
		excelColumns.add(new ExcelColumn(3, "remitdate", Date.class));
		excelColumns.add(new ExcelColumn(4, "handoverstatus", String.class));
		excelColumns.add(new ExcelColumn(5, "money", Double.class));
		if (excelFile == null || !excelFile.exists()) {
			msg = "你选择的文件不存在,请重新选择！";
			PrintWriteUtil.writeResultToBrowser(false, msg);
			return null;
		}
		try {
			excelFile = DlpUtil.deCodeDlpFile(excelFile);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			msg = "解密excel失败!";
			PrintWriteUtil.writeResultToBrowser(false, msg);
		}
		ExcelHead head = new ExcelHead();
		head.setColumns(excelColumns);// 列的定义
		 List<DemoEntity>	demoEntityList = ExcelUtil.getInstanse()
					.importExcelToObjectList(head, excelFile, DemoEntity.class);
		 try {
			msg = demoSerivce.importExcel(demoEntityList);
			 if("".equals(msg)){
		    		PrintWriteUtil.writeResultToBrowser(true,"");
		        }else{
		        	PrintWriteUtil.writeResultToBrowser(false,msg);
		        }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			msg="出现异常，"+e.getMessage();
			PrintWriteUtil.writeResultToBrowser(false, msg);
		}
		return null ;
	}
	
	//---------------------------set/get----------------------------------
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public InputStream getExcelStream() {
		return excelStream;
	}

	public void setExcelStream(InputStream excelStream) {
		this.excelStream = excelStream;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	
	public int getTotalCount() {
		return totalCount;
	}


	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}


	public double getTotalMoney() {
		return totalMoney;
	}

	public void setTotalMoney(double totalMoney) {
		this.totalMoney = totalMoney;
	}

	public String getCodeNums() {
		return codeNums;
	}

	public void setCodeNums(String codeNums) {
		this.codeNums = codeNums;
	}

	public DemoEntity getDemoEntity() {
		return demoEntity;
	}

	public void setDemoEntity(DemoEntity demoEntity) {
		this.demoEntity = demoEntity;
	}

	public List<DemoEntity> getDemoEntityList() {
		return demoEntityList;
	}

	public void setDemoEntityList(List<DemoEntity> demoEntityList) {
		this.demoEntityList = demoEntityList;
	}

	public String getNonBLS() {
		return nonBLS;
	}

	public void setNonBLS(String nonBLS) {
		this.nonBLS = nonBLS;
	}

	public boolean isMark_flag() {
		return mark_flag;
	}

	public void setMark_flag(boolean mark_flag) {
		this.mark_flag = mark_flag;
	}
	
	private HttpSession getSession(){
		return ServletActionContext.getRequest().getSession(
				true);
	}
	public String printAc() {

		return SUCCESS;
	}
	
	public String printView() {

		return SUCCESS;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getOperateType() {
		return operateType;
	}
	public void setOperateType(String operateType) {
		this.operateType = operateType;
	}
	public File getExcelFile() {
		return excelFile;
	}
	public void setExcelFile(File excelFile) {
		this.excelFile = excelFile;
	}
	
}
