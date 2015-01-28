package com.example.activity.earnings;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.activity.common.Activity_OnlySubmit;
import com.example.activity.common.Activity_PublicWeb;
import com.example.activity.common.BaseActivity;
import com.example.activity.common.EnumOnlySubmit;
import com.example.activity.common.KeyGuardActivityManager;
import com.example.activity.more.Activity_MyInfo;
import com.example.keyguard.R;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.ViewInjectInfo;
import com.lidroid.xutils.view.annotation.ViewInject;

import org.json.JSONObject;

/**
 * @Description 新手活动
 * @author Created by qinxianyuzou on 2015-1-26.
 */
public class Activity_newcomer extends BaseActivity {
	/** 完善信息 */
	@ViewInject(R.id.ly_improve_msg)
	private LinearLayout ly_improve_msg;
	/** 填写邀请码 */
	@ViewInject(R.id.ly_input_auth)
	private LinearLayout ly_input_auth;
	/** 了解锁屏赚 */
	@ViewInject(R.id.ly_understand)
	private LinearLayout ly_understand;
	/** 跳过 */
	@ViewInject(R.id.tv_jump)
	private TextView tv_jump;

	public static void luanch(Activity activity) {
		Intent intent = new Intent(activity, Activity_newcomer.class);
		KeyGuardActivityManager.getInstance().goTo(activity, intent);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_newcomer);
		ViewUtils.inject(activity);
		initUI();
		initData();
	}

	@Override
	protected void initUI() {
		ly_improve_msg.setOnClickListener(this);
		ly_input_auth.setOnClickListener(this);
		ly_understand.setOnClickListener(this);
		tv_jump.setOnClickListener(this);
	}

	@Override
	protected void initData() {
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ly_improve_msg:
			Activity_MyInfo.luanch(activity);
			break;
		case R.id.ly_input_auth:
			Activity_OnlySubmit.luanch(activity, "邀请码", EnumOnlySubmit.INVITE_NO);
			break;
		case R.id.ly_understand:
			Activity_PublicWeb.luanch(activity, "了解锁屏赚", "");
			break;
		case R.id.tv_jump:
			finish();
			break;

		default:
			break;
		}
	}

	@Override
	public <T> void onHttpSuccess(long flag, JSONObject jsonString, T response) {

	}

	@Override
	public void onHttpError(long flag, VolleyError error) {

	}

	@Override
	public String setTag() {
		return this.getClass().getSimpleName();
	}
}
