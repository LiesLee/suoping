package com.example.activity.common;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;

import com.android.volley.VolleyError;
import com.example.activity.reg.Activity_Reg;
import com.example.keyguard.MainActivity;
import com.example.keyguard.R;
import com.example.util.SharedPreferenceUtil;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

/**
 * @Description 引导页
 * @author Created by qinxianyuzou on 2014-12-26.
 */
public class Activity_Guide extends BaseActivity implements OnClickListener {
	@ViewInject(R.id.vp_guide_body)
	private ViewPager vp_guide_body;
	private PagerAdapter_Guide adapter_Guide;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_guide);
		ViewUtils.inject(this);
		initUI();
		initData();
	}

	@Override
	protected void initUI() {
		// TODO Auto-generated method stub
		adapter_Guide = new PagerAdapter_Guide(activity);
	}

	@Override
	protected void initData() {
		// TODO Auto-generated method stub
		vp_guide_body.setAdapter(adapter_Guide);
		View view1 = LayoutInflater.from(this).inflate(R.layout.activity_guide1, null);
		View view2 = LayoutInflater.from(this).inflate(R.layout.activity_guide2, null);
		View view3 = LayoutInflater.from(this).inflate(R.layout.activity_guide3, null);
		List<View> dataList = new ArrayList<View>();
		dataList.add(view1);
		dataList.add(view2);
		dataList.add(view3);
		adapter_Guide.setShowView(dataList);
		view3.findViewById(R.id.but_guide_goto).setOnClickListener(this);
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

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.but_guide_goto:
			if (SharedPreferenceUtil.getInstance(activity).getString(SharedPreferenceUtil.USERINFO).equals("")) {
				// startActivity(new Intent(this, LoginActivity.class));
				startActivity(new Intent(this, Activity_Reg.class));
			} else {
				startActivity(new Intent(this, MainActivity.class));
			}
			finish();
			break;

		default:
			break;
		}
	}

}
