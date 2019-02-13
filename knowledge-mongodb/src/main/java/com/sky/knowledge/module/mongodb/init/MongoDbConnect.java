package com.sky.knowledge.module.mongodb.init;

import java.net.UnknownHostException;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;
import com.mongodb.MongoException;
/**
 * 初始化mongodb链接
 * @description
 * @create xq
 * @date 2014-11-17
 */
public class MongoDbConnect implements InitializingBean,DisposableBean{

	private String host ;
	
	private int port ;
	
	private String dbname ;
	
	 private Mongo mg = null;
	 private DB db;
	 
	 private DBCollection logTab;
	 
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
		if(mg == null){
			  try {
		            mg = new Mongo(host,port);
		        } catch (UnknownHostException e) {
		            e.printStackTrace();
		        } catch (MongoException e) {
		            e.printStackTrace();
		        }
		}
		if(db==null){
			initDB();
		}
		if(logTab ==null){
			initCollection();
		}
		
	}
	/**
	 * 初始化db库
	 * @description
	 * @create xq
	 * @date 2014-11-17
	 */
	private void initDB(){
		if(mg!=null){
			db = mg.getDB(dbname);
		}
	}
	/**
	 * 初始化logTab表
	 * @description
	 * @create xq
	 * @date 2014-11-17
	 */
	private void initCollection(){
		if(db!=null){
			logTab = db.getCollection("logTab");
		}
	}
	
	public DBCollection getCollection(String params){
		if(db!=null){
			return db.getCollection(params);
		}
		return null ;
	}

	public void destroy() throws Exception {
		// TODO Auto-generated method stub
		  if (mg != null)
	            mg.close();
	        mg = null;
	        db = null;
	        logTab = null;
	        System.gc();
	}
	
	
	
	public Mongo getMg() {
		return mg;
	}
	public void setMg(Mongo mg) {
		this.mg = mg;
	}
	public DB getDb() {
		return db;
	}

	public void setDb(DB db) {
		this.db = db;
	}

	public DBCollection getLogTab() {
		return logTab;
	}

	public void setLogTab(DBCollection logTab) {
		this.logTab = logTab;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public String getDbname() {
		return dbname;
	}
	public void setDbname(String dbname) {
		this.dbname = dbname;
	}
	
	
}
