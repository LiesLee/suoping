package com.example.activity.common;

import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;

import com.android.volley.VolleyError;
import com.example.http.ConnectorManage;
import com.example.http.HttpCallBack;
import com.example.util.PublicUtil;

/**
 * @Description 基础activity
 * @author Created by qinxianyuzou on 2014-12-23.
 */
public abstract class BaseActivity extends Activity implements HttpCallBack {
	protected ConnectorManage connectorManage;
	protected String Tag = "";
	protected BaseActivity activity;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		activity = this;
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

	@Override
	protected void onResume() {
		super.onResume();
		setConnectorListener();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}

	@Override
	public abstract <T> void onHttpSuccess(long flag, JSONObject jsonString, T response);

	@Override
	public abstract void onHttpError(long flag, VolleyError error);

	/**
	 * @Description 初始化ui
	 * @author Created by qinxianyuzou on 2014-12-24.
	 */
	protected abstract void initUI();

	/**
	 * @Description 初始化数据
	 * @author Created by qinxianyuzou on 2014-12-24.
	 */
	protected abstract void initData();

	/**
	 * @Description 设置tag
	 * @author Created by qinxianyuzou on 2014-12-24.
	 * @return
	 */
	public abstract String setTag();

	/**
	 * @Description 短时间toast
	 * @author Created by qinxianyuzou on 2014-12-24.
	 * @param text
	 */
	public void showToast(String text) {
		PublicUtil.showToast(activity, text);
	}

	// 创建网络连接，并设置当前的activity为接收消息的对象
	private void setConnectorListener() {
		connectorManage = ConnectorManage.getInstance(this);
		connectorManage.setActivityCallBack(this);
	}
}
