package org.sky.knowledge.module.restful.server.client;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class URLConnectionTest {

	public static void main(String args[]) throws Exception, IOException{
		
		URLConnection conn = new URL("http://localhost:8081/knowledge/rest/bean/1").openConnection();
        HttpURLConnection con = (HttpURLConnection) conn;
        con.setRequestMethod("GET");
        int code = con.getResponseCode();
        System.out.println(code);
        InputStream is = con.getInputStream();
        int len = 0;
        byte[] b = new byte[1024];
        StringBuffer sb = new StringBuffer();
        while((len = is.read(b)) != -1){
            String s = new String(b,0,len,"UTF-8");
            sb.append(s);
        }
        System.out.println(sb.toString());
        con.disconnect();
	}
}
