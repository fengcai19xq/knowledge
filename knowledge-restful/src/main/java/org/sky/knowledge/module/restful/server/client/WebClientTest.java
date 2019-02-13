package org.sky.knowledge.module.restful.server.client;

import org.apache.cxf.jaxrs.client.WebClient;
import org.sky.knowledge.module.restful.shared.UserEntityRes;
import org.sky.knowledge.module.restful.shared.UserInfo;

public class WebClientTest {

	private static final String CATEGORY_URL = "http://localhost:8081/RestFulWeb/rest/";
    private static final String CATEGORY_ID = "005";
    private static final String TYPE_XML = "application/xml";
    private static final String TYPE_JSON = "application/json";
    
	/**
	 * @description
	 * @create xq
	 * @date 2014-10-16  
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		 WebClient client = WebClient.create(CATEGORY_URL);
	        client.path("/putObjData").accept(
	        		TYPE_JSON).type(TYPE_JSON);
	        UserInfo user = new UserInfo();
			user.setUid(1);
			user.setUserName("xq");
			user.setPwd("2222");
			UserEntityRes uResponse = client.post(user, UserEntityRes.class);
			System.out.println("客户端："+uResponse.getUsermap().get("user").toString());
	}

}
