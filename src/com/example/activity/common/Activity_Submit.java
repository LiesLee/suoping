package com.example.activity.common;

import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.keyguard.R;
import com.lidroid.xutils.view.annotation.ViewInject;

/**
 * @Description 单内容提交
 * @author Created by qinxianyuzou on 2014-12-29.
 */
public class Activity_Submit extends BaseActivity {
	/** 标题栏 */
	@ViewInject(R.id.tv_public_top_title)
	private TextView tv_public_top_title;
	/** 返回按钮 */
	@ViewInject(R.id.rl_public_back)
	private RelativeLayout rl_public_back;

	public static void luanch(Activity activity, String title, String hit) {
		Intent intent = new Intent(activity, Activity_Submit.class);
		KeyGuardActivityManager.getInstance().goFoResult(activity, intent, 111111);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_nickname);
	}

	@Override
	protected void initUI() {
		// TODO Auto-generated method stub
		rl_public_back.setOnClickListener(this);
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
		return Activity_Submit.class.getSimpleName();
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

	}

}
