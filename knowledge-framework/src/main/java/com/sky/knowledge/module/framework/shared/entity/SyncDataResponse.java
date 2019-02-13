package com.sky.knowledge.module.framework.shared.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * 
 * 
*******************************************
* <b style="font-family:微软雅黑"><small>Description:数据同步响应</small></b>   </br>
* <b style="font-family:微软雅黑"><small>HISTORY</small></b></br>
* <b style="font-family:微软雅黑"><small> ID      DATE    PERSON     REASON</small></b><br>
********************************************
* <div style="font-family:微软雅黑,font-size:70%"> 
* 1 2011-4-9 steven.cheng 新增
* </div>  
********************************************
 */
public class SyncDataResponse implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2024887334067311878L;
	private List<?> fromData;
	private Date fromDate;
	private Date maxDate;
	private int pageSize;
	private int fromPage;
	private Class<?> syncKey;
	


	public List<?> getFromData() {
		return fromData;
	}

	public void setFromData(List<?> fromData) {
		this.fromData = fromData;
	}

	public int getPageSize() {
    	return pageSize;
    }

	public void setPageSize(int pageSize) {
    	this.pageSize = pageSize;
    }
	public int getFromPage() {
		return fromPage;
	}

	public void setFromPage(int fromPage) {
		this.fromPage = fromPage;
	}


	public Date getMaxDate() {
		return maxDate;
	}

	public void setMaxDate(Date maxDate) {
		this.maxDate = maxDate;
	}


	
	public Class<?> getSyncKey() {
		return syncKey;
	}
	
	public void setSyncKey(Class<?> syncKey) {
		this.syncKey = syncKey;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	
}