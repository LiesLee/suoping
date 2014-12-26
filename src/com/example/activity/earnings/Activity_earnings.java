package com.example.activity.earnings;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;

import com.android.volley.VolleyError;
import com.example.activity.common.BaseActivity;
import com.example.keyguard.R;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

/**
 * @Description
 * @author Created by qinxianyuzou on 2014-12-26.
 */
public class Activity_earnings extends BaseActivity implements OnClickListener {
	/** 收益 */
	@ViewInject(R.id.rl_earnings_earnings)
	private RelativeLayout rl_earnings_earnings;
	/** 活动 */
	@ViewInject(R.id.rl_earnings_action)
	private RelativeLayout rl_earnings_action;
	/** 明细 */
	@ViewInject(R.id.rl_earnings_details)
	private RelativeLayout rl_earnings_details;
	/** 兑换 */
	@ViewInject(R.id.rl_earnings_exchange)
	private RelativeLayout rl_earnings_exchange;
	/** 公告 */
	@ViewInject(R.id.rl_earnings_announcement)
	private RelativeLayout rl_earnings_announcement;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tab_earnings);
		ViewUtils.inject(activity);
		initUI();
		initData();

	}

	@Override
	public <T> void onHttpSuccess(long flag, JSONObject jsonString, T response) {

	}

	@Override
	public void onHttpError(long flag, VolleyError error) {

	}

	@Override
	protected void initUI() {
		rl_earnings_earnings.setOnClickListener(this);
		rl_earnings_action.setOnClickListener(this);
		rl_earnings_details.setOnClickListener(this);
		rl_earnings_exchange.setOnClickListener(this);
		rl_earnings_announcement.setOnClickListener(this);
	}

	@Override
	protected void initData() {
		
	}

	@Override
	public String setTag() {
		return null;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.rl_earnings_earnings:

			break;
		case R.id.rl_earnings_action:

			break;
		case R.id.rl_earnings_details:

			break;
		case R.id.rl_earnings_exchange:

			break;
		case R.id.rl_earnings_announcement:

			break;

		default:
			break;
		}
	}
}
