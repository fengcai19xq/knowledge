package com.sky.knowledge.module.mongodb.server.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.sky.knowledge.module.mongodb.init.MongoDbConnect;
/**
 * mongodb帮助类
 * @description
 * @create xq
 * @date 2014-11-18
 */
public class MongoHelper {

	private  MongoDbConnect  con ;
	/**
	 * 单一保存
	 * @description
	 * @create xq
	 * @date 2014-11-18
	 */
	public  void saveOne(Object obj,Class<?> c){
		DBObject dbobj = new BasicDBObject();
		Method[] ms = c.getMethods();
		Class<?> cl ;
		for(Method m:ms){
				String str = m.getName();
				if(str.startsWith("get")&&!str.equals("getClass")){
					String name = str.substring(3, str.length()).toLowerCase();
					try {
						Object value = m.invoke(obj);
						cl = m.getReturnType();
//						if(cl == null){
//							dbobj.put(name, "");
//						}else if(cl.toString().endsWith("Integer")){
//							dbobj.put(name, (Integer)value);
//						}else if(cl.toString().endsWith("Double")){
//							dbobj.put(name, (Double)value);
//						}else if(cl.toString().endsWith("String")){
//							dbobj.put(name, (String)value);
//						}else if(cl.toString().endsWith("Date")){
//							try {
//								dbobj.put(name,value);
//							} catch (Exception e) {
//								// TODO Auto-generated catch block
//								e.printStackTrace();
//							}
//						}else if(cl.isArray()){
//							dbobj.put(name, value==null?"":value.toString());
//						}else{
							dbobj.put(name, value);
//						}
					} catch (IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalArgumentException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} 
				}
				
	 }
		getDBcollection().save(dbobj);
	}
	
	public void queryAll(){
		DBCursor cur = getDBcollection().find();
	        while (cur.hasNext()) {
	            System.out.println(cur.next());
	        }
	}
	/**
	 * 一个where条件查询
	 * @description
	 * @create xq
	 * @date 2014-11-17
	 */
	public Object queryByOneParam(String fieldParam,String value,Class<?> c){
		List<Object> retList = new ArrayList<Object>();//返回值
		List<DBObject> list = getDBcollection().find(new BasicDBObject(fieldParam,value)).toArray();//mongodb查询出值
		Object obj = null;
		Method[] ms = c.getMethods();
		for(DBObject dbo : list){
			try {
				obj = c.newInstance();
				for(Method m : ms){
					String str = m.getName();
					if(str.startsWith("set")){
						String field = str.substring(3, str.length()).toLowerCase();
					 	try {
					 		if(dbo.get(field)!=null&&!dbo.get(field).equals("")){
					 			m.invoke(obj, dbo.get(field));
					 		}
						} catch (IllegalArgumentException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (InvocationTargetException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
				retList.add(obj);
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return retList;
	}
	
	private  DBCollection getDBcollection() {
		return con.getLogTab();
	}

	public static MongoHelper getInstance(){
		return (MongoHelper)SpringContextUtil.getBean("mongoHelper");
	}

	public MongoDbConnect getCon() {
		return con;
	}

	public void setCon(MongoDbConnect con) {
		this.con = con;
	}
	
	/**
	 * 日期转化
	 * @description
	 * @create 徐倩
	 * @date 2014-6-30  
	 * @param datestr
	 * @return
	 * @throws Exception
	 */
	private Date transferDate(Object datestr)throws Exception{
		return new SimpleDateFormat("yyyy-MM-dd").parse((String)datestr);
	}
	
}
