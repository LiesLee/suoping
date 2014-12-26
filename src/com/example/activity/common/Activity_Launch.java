package com.example.activity.common;

import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.android.volley.VolleyError;
import com.example.keyguard.MainActivity;
import com.example.keyguard.R;
import com.example.util.SharedPreferenceUtil;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

/**
 * @Description 启动页
 * @author Created by qinxianyuzou on 2014-12-26.
 */
public class Activity_Launch extends BaseActivity implements AnimationListener {
	@ViewInject(R.id.iv_launch_ad)
	private ImageView iv_launch_ad;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_launch);
		ViewUtils.inject(activity);
		initUI();
		initData();
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
	protected void initUI() {
		// TODO Auto-generated method stub
		Animation at = AnimationUtils.loadAnimation(this, R.anim.launch_ad);
		at.setAnimationListener(this);
		iv_launch_ad.startAnimation(at);

	}

	@Override
	protected void initData() {
		// TODO Auto-generated method stub
	}

	@Override
	public String setTag() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onAnimationStart(Animation animation) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onAnimationEnd(Animation animation) {
		// TODO Auto-generated method stub
		if (SharedPreferenceUtil.getInstance(activity).getInt(SharedPreferenceUtil.ISFIRSTUSED) == 0) {
			startActivity(new Intent(activity, Activity_Guide.class));
		} else {
			startActivity(new Intent(activity, MainActivity.class));
		}
		SharedPreferenceUtil.getInstance(activity).putInt(SharedPreferenceUtil.ISFIRSTUSED, 1);
		animFinish(3);
	}

	@Override
	public void onAnimationRepeat(Animation animation) {
		// TODO Auto-generated method stub

	}

	


	
}
