package com.example.activity.earnings;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import com.example.keyguard.LockActivity;
import com.example.keyguard.MainActivity;
import com.example.keyguard.R;
import com.slidingmenu.lib.SlidingMenu;

import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Activity_earnings extends Activity {
	private Button bt1;
	private Button bt2;
	private Button bt3;
	private Context context;
	private Button menubtn;
	private SlidingMenu menu;
	
	LinearLayout mProgressLayout;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tab_earnings);
		

		/*RelativeLayout mapButtonRL = new RelativeLayout(this);
		  RelativeLayout.LayoutParams lp1 = new RelativeLayout.LayoutParams(
		    ViewGroup.LayoutParams.WRAP_CONTENT,
		    ViewGroup.LayoutParams.WRAP_CONTENT);

		Button btn1 = new Button(this);
		btn1.setText("测试post");
		btn1.setId(1);
		
		btn1.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//loginQQ("1103513011");	
				Toast.makeText(Activity_earnings.this, "Activity_earnings!",
					     Toast.LENGTH_SHORT).show();
				
				//postest();
				
				mProgressLayout=new LinearLayout(Activity_earnings.this);
				  mProgressLayout.setMinimumHeight(30);
				  mProgressLayout.setGravity(Gravity.CENTER);
				  mProgressLayout.setOrientation(LinearLayout.HORIZONTAL);
				  android.widget.LinearLayout.LayoutParams mLayoutParams=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
				  ProgressBar mProgressBar=new ProgressBar(Activity_earnings.this);
				  mProgressBar.setPadding(100, 250, 5, 80);
				    mProgressLayout.addView(mProgressBar,mLayoutParams);
				  TextView mContent=new TextView(Activity_earnings.this);
				  mContent.setText("加载中...");
				  mContent.setTextSize(19);
				  mContent.setTextColor(Color.BLACK);
				  mContent.setPadding(0, 250, 0, 80);  
				  mProgressLayout.addView(mContent, mLayoutParams);
				  mProgressLayout.setVisibility(View.VISIBLE);
				  Activity_earnings.this.addContentView(mProgressLayout, mLayoutParams);
				  
				  dopost("hhz2262491", "2262491");
			}  
        }); 
		mapButtonRL.addView(btn1, lp1);
		setContentView(mapButtonRL);*/
		
		
	}
	public void postest() {
		/*
		  try {
			  final String SERVER_URL = "http://webservice.webxml.com.cn/WebServices/WeatherWS.asmx/getWeather"; // 定义需要获取的内容来源地址
			  HttpPost request = new HttpPost(SERVER_URL); // 根据内容来源地址创建一个Http请求
			  List params = new ArrayList();
			  params.add(new BasicNameValuePair("theCityCode", "长沙")); // 添加必须的参数
			  params.add(new BasicNameValuePair("theUserID", "")); // 添加必须的参数
			  request.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8)); // 设置参数的编码
			  HttpResponse httpResponse = new DefaultHttpClient().execute(request); // 发送请求并获取反馈
			  Log.i("test", httpResponse.toString());
			  // 解析返回的内容
			  if (httpResponse.getStatusLine().getStatusCode() != 404)
			  	{
			  			String result = EntityUtils.toString(httpResponse.getEntity());
			  			System.out.println(result);
			  	}
		  } catch (Exception e) {
		  }
		  */
		Thread newThread;         //声明一个子线程
		   new Thread() {
			   @Override
			   public void run() {
			        //这里写入子线程需要做的工作
					HttpClient httpClient = new DefaultHttpClient();
					HttpPost httpPost = new HttpPost("http://webservice.webxml.com.cn/WebServices/WeatherWS.asmx/getWeather");
					try {
						// 为httpPost设置HttpEntity对象
						List<NameValuePair> parameters = new ArrayList<NameValuePair>();
						parameters.add(new BasicNameValuePair("theCityCode", "长沙"));
						parameters.add(new BasicNameValuePair("theUserID", ""));
						HttpEntity entity = new UrlEncodedFormEntity(parameters);
						httpPost.setEntity(entity);
						// httpClient执行httpPost表单提交
						HttpResponse response = httpClient.execute(httpPost);
						// 得到服务器响应实体对象
						HttpEntity responseEntity = response.getEntity();
						if (responseEntity != null) {
							System.out.println(EntityUtils
									.toString(responseEntity, "utf-8"));
							System.out.println("表单上传成功！");
						} else {
							System.out.println("服务器无响应！");
						}
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						// 释放资源
						httpClient.getConnectionManager().shutdown();
					}
			           }
			      }.start();

	}
	
	
	private void dopost(final String val,final String PWS){
		
		Thread newThread;         //声明一个子线程
		   new Thread() {
			   @Override
			   public void run() {
			        //这里写入子线程需要做的工作
	      //封装数据
	      Map<String, String> parmas = new HashMap<String, String>();
	      parmas.put("logname", val);
	      parmas.put("logpass", PWS);
	      parmas.put("logintype", "1");
	      parmas.put("nameorid", val);
	      parmas.put("siteid", "956");
	      parmas.put("classid", "");
	    
	      DefaultHttpClient client = new DefaultHttpClient();//http客户端
	      HttpPost httpPost = new HttpPost("http://zntx.cc/waploginchk.asp");
	    
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
	          Log.i("test", returnConnection);
	          //show.setText(returnConnection);
	          
	          
	   } catch (IllegalStateException e) {
	    e.printStackTrace();
	   } catch (IOException e) {
	    e.printStackTrace();
	   }
	           }
	      }.start();
	 }
	 
	 private String convertStreamToString(InputStream is) {
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
