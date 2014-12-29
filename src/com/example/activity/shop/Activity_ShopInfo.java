package com.example.activity.shop;

import org.json.JSONObject;

import android.os.Bundle;

import com.android.volley.VolleyError;
import com.example.activity.common.BaseActivity;
import com.example.keyguard.R;

/**
 * @Description 兑换详情页
 * @author Created by qinxianyuzou on 2014-12-29.
 */
public class Activity_ShopInfo extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_shopinfo);
	}

	@Override
	protected void initUI() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void initData() {
		// TODO Auto-generated method stub

	}

	@Override
	public <T> void onHttpSuccess(long flag, JSONObject jsonString, T response) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onHttpError(long flag, VolleyError error) {
		// TODO Auto-generated method stub

	}

	@Override
	public String setTag() {
		// TODO Auto-generated method stub
		return null;
	}

}
