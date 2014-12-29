package com.example.activity.more;

import com.android.volley.VolleyError;
import com.example.activity.common.BaseActivity;
import com.example.keyguard.R;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONObject;

/**
 * @Description 更多
 * @author Created by qinxianyuzou on 2014-12-25.
 */
public class Activity_more extends BaseActivity {
	/** 标题栏 */
	@ViewInject(R.id.tv_public_top_title)
	private TextView tv_public_top_title;
	/**  */
	@ViewInject(R.id.ll_more_myinfo)
	private LinearLayout ll_more_myinfo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tab_more);
		ViewUtils.inject(this);
		initUI();
		initData();
	}

	@Override
	protected void initUI() {
		tv_public_top_title.setText("更多");
		ll_more_myinfo.setOnClickListener(this);
	}

	@Override
	protected void initData() {

	}

	@Override
	public <T> void onHttpSuccess(long flag, JSONObject jsonString, T response) {

	}

	@Override
	public void onHttpError(long flag, VolleyError error) {

	}

	@Override
	public String setTag() {
		return Activity_more.class.getSimpleName();
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.ll_more_myinfo:
			Activity_MyInfo.luanch(activity);
			break;

		default:
			break;
		}
	}
}
