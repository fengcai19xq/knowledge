/**
 * Project Name:FIMS-Interfaces
 * File Name:RSETServiceClient.java
 * Package Name:com.deppon.fims.inter.demo.client
 * Date:2014-10-10下午5:07:50
 * Copyright (c) 2014 All Rights Reserved.
 */

package org.sky.knowledge.module.restful.server.client;
import java.io.IOException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PutMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

/**
 * @ClassName:RSETServiceClient <br/>
 * @Title: TODO 用一句话说明. <br/>
 * @author: 215209
 * @Date: 2014-10-10 下午5:07:50
 */
public class RSETServiceClient {
	
	public static void main(String[] args) {
		String url = "http://localhost:8081/knowledge/rest/bean/1";
		HttpClient httpClient = new HttpClient();
		httpClient.getParams().setContentCharset("UTF-8"); // 设置编码
		GetMethod method = new GetMethod(url);
		PutMethod p = new PutMethod();
		HttpMethodParams hp = new HttpMethodParams();
		try {
			int statucode = httpClient.executeMethod(method);
			if(statucode != HttpStatus.SC_OK){
				System.out.print("aa");
			}
			byte[] res = method.getResponseBody();
			System.out.println(new String(res));
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}
