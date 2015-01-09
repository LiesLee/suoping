package com.example.keyguard;

import net.youmi.android.AdManager;
import net.youmi.android.offers.OffersManager;

import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.android.volley.VolleyError;
import com.example.activity.common.BaseActivity;
import com.example.activity.common.KeyGuardActivityManager;

public class Activity_YouMi extends BaseActivity {
	/**
	 * @Description 不设置标题
	 * @author Created by qinxianyuzou on 2014-12-30.
	 * @param activity
	 */
	public static void luanch(Activity activity) {
		Intent intent = new Intent(activity, Activity_YouMi.class);
		KeyGuardActivityManager.getInstance().goFoResult(activity, intent, KeyGuardActivityManager.MAIN_CODE);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		initUI();
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
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
	public void onClick(View v) {
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
		return this.getClass().getSimpleName();
	}

}
