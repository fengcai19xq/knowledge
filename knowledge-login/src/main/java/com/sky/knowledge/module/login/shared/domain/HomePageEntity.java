package com.sky.knowledge.module.login.shared.domain;

import java.io.Serializable;
import java.util.Date;

import com.sky.knowledge.module.framework.shared.domain.Function;

public class HomePageEntity implements Serializable{

	 private static final long serialVersionUID = 1L;
	  private Integer homePageID;
	  private String title;
	  private String fileName;
	  private String readFileName;
	  private String imgSrc;
	  private String functionCode;
	  private Integer orderIndex;
	  private String dataType;
	  private String empCode;
	  private Date createTime;
	  private Function fun;

	  public Integer getHomePageID()
	  {
	    return this.homePageID;
	  }

	  public void setHomePageID(Integer homePageID)
	  {
	    this.homePageID = homePageID;
	  }

	  public String getTitle()
	  {
	    return this.title;
	  }

	  public void setTitle(String title)
	  {
	    this.title = title;
	  }

	  public String getFileName()
	  {
	    return this.fileName;
	  }

	  public void setFileName(String fileName)
	  {
	    this.fileName = fileName;
	  }

	  public String getImgSrc()
	  {
	    return this.imgSrc;
	  }

	  public void setImgSrc(String imgSrc)
	  {
	    this.imgSrc = imgSrc;
	  }

	  public String getFunctionCode()
	  {
	    return this.functionCode;
	  }

	  public void setFunctionCode(String functionCode)
	  {
	    this.functionCode = functionCode;
	  }

	  public Integer getOrderIndex()
	  {
	    return this.orderIndex;
	  }

	  public void setOrderIndex(Integer orderIndex)
	  {
	    this.orderIndex = orderIndex;
	  }

	  public String getDataType()
	  {
	    return this.dataType;
	  }

	  public void setDataType(String dataType)
	  {
	    this.dataType = dataType;
	  }

	  public String getEmpCode()
	  {
	    return this.empCode;
	  }

	  public void setEmpCode(String empCode)
	  {
	    this.empCode = empCode;
	  }

	  public Date getCreateTime()
	  {
	    return this.createTime;
	  }

	  public void setCreateTime(Date createTime)
	  {
	    this.createTime = createTime;
	  }

	  public Function getFun()
	  {
	    return this.fun;
	  }

	  public void setFun(Function fun)
	  {
	    this.fun = fun;
	  }

	  public String getReadFileName() {
	    return this.readFileName;
	  }

	  public void setReadFileName(String readFileName) {
	    this.readFileName = readFileName;
	  }
}
