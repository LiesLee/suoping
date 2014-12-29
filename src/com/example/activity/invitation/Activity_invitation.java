package com.example.activity.invitation;

import org.json.JSONObject;

import android.os.Bundle;
import android.view.View;

import com.android.volley.VolleyError;
import com.example.activity.common.BaseActivity;
import com.example.http.Protocol;
import com.example.keyguard.R;

public class Activity_invitation extends BaseActivity {
	private long get_logistics_list;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tab_invitation);

	}

	@Override
	protected void initUI() {
		// TODO 初始化ui

	}

	@Override
	protected void initData() {
		// TODO 初始化数据
		get_logistics_list = Protocol.get_logistics_list(activity, setTag());
	}

	@Override
	public String setTag() {
		// TODO 设置tag
		return Activity_invitation.class.getSimpleName();
	}

	@Override
	public <T> void onHttpSuccess(long flag, JSONObject jsonString, T response) {
		// TODO 回调成功

	}

	@Override
	public void onHttpError(long flag, VolleyError error) {
		// TODO 回调错误

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}

}
