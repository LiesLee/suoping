package com.example.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

public class Http_ReqForm {

	public static  void dopost(Map<String, String> parmas,String Url){	    
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
	          //show.setText(returnConnection);
	   } catch (IllegalStateException e) {
	    e.printStackTrace();
	   } catch (IOException e) {
	    e.printStackTrace();
	   }
	    
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
