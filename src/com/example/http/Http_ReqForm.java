package com.example.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import com.example.parameter.config;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class Http_ReqForm {

	public static  void dopost(final Map<String, String> parmas,final String Url,final Context ctx){	  
		
	Thread newThread;         //声明一个子线程
		new Thread() {
			   @Override
		public void run() {	
				   
		      DefaultHttpClient client = new DefaultHttpClient();//http客户端
		      HttpPost httpPost = new HttpPost(Url);	    
		      ArrayList<BasicNameValuePair> pairs = new ArrayList<BasicNameValuePair>();
		      if(parmas != null){
		          Set<String> keys = parmas.keySet();
		          for(Iterator<String> i = keys.iterator(); i.hasNext();) {
		               String key = (String)i.next();
		               pairs.add(new BasicNameValuePair(key, parmas.get(key)));
		          }
		      }
			   try {
				   	  UrlEncodedFormEntity p_entity = new UrlEncodedFormEntity(pairs, "utf-8");
			         
			          httpPost.setEntity(p_entity);
			         
			          HttpResponse response = client.execute(httpPost);
			          HttpEntity entity = response.getEntity();
			          InputStream content = entity.getContent();
			          String returnConnection = convertStreamToString(content);
			          //String  stringUTF8 = URLEncoder.encode(returnConnection, "gb2312");  
			          String stringGBK = new String(returnConnection.getBytes(),"gb2312"); 
			          Log.d("HTTP", stringGBK);
			          
			          //json(returnConnection);
		              //Intent mIntent = new Intent(config.HTTP_POST);  
		              //mIntent.putExtra("data", json(returnConnection));  
		                //发送广播  
		              //ctx.sendBroadcast(mIntent); 
			          
				   } catch (IllegalStateException e) {
				    e.printStackTrace();
				   } catch (IOException e) {
				    e.printStackTrace();
				   }
			}
		}.start();	    
	 }
	 
	public static Map<String, String> json(String json) {
		//List<Map<String, String>> list = new ArrayList<Map<String, String>>();  
        HashMap<String, String> map = new HashMap<String, String>(); // 存放到MAP里面  
		try { 
			JSONObject demoJson = new JSONObject(json);  
			String code = demoJson.getString("code");  
			String msg = demoJson.getString("msg");  
	        map.put("code", code);  
	        map.put("msg", msg);  
	        //list.add(map);  
	        Log.d("HTTP", msg);
	            	        
		} catch (JSONException ex) {  
		    // 异常处理代码  
		}  
		return map;
	}
	
	 private static String convertStreamToString(InputStream is) {
	    BufferedReader reader = new BufferedReader(new InputStreamReader(is));
	          StringBuilder sb = new StringBuilder();
	          String line = null;
	          try {
	               while ((line = reader.readLine()) != null) {
	                    sb.append(line);
	               }
	          } catch (IOException e) {
	               e.printStackTrace();
	          } finally {
	               try {
	                    is.close();
	               } catch (IOException e) {
	                    e.printStackTrace();
	               }
	          }
	          return sb.toString();
	 }
	 
	 
}
