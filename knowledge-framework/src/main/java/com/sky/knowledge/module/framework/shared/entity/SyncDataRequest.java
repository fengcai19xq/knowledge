package com.sky.knowledge.module.framework.shared.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 
*******************************************
* <b style="font-family:微软雅黑"><small>Description:数据同步请求</small></b>   </br>
* <b style="font-family:微软雅黑"><small>HISTORY</small></b></br>
* <b style="font-family:微软雅黑"><small> ID      DATE    PERSON     REASON</small></b><br>
********************************************
* <div style="font-family:微软雅黑,font-size:70%"> 
* 1 2011-4-9 steven.cheng 新增
* </div>  
********************************************
 */
public class SyncDataRequest implements Serializable{
    
    /**
	 * 
	 */
	private static final long serialVersionUID = -7441693678617034943L;
	private Class<?> syncKey;
    private Date     fromDate;
    private Date 	 maxDate;
	private int      fromPage;
    
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
    
    public int getFromPage() {
        return fromPage;
    }
    
    public Date getMaxDate() {
		return maxDate;
	}

	public void setMaxDate(Date maxDate) {
		this.maxDate = maxDate;
	}
    public void setFromPage(int fromPage) {
        this.fromPage = fromPage;
    }
    
}