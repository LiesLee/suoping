package com.example.activity.more;

import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.activity.common.Activity_PublicWeb;
import com.example.activity.common.BaseActivity;
import com.example.activity.common.KeyGuardActivityManager;
import com.example.entity.Update_Entity;
import com.example.entity.respose.Code;
import com.example.entity.respose.ResponseUpdate;
import com.example.http.Protocol;
import com.example.keyguard.R;
import com.example.util.PublicUtil;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

/**
 * @Description 关于
 * @author Created by qinxianyuzou on 2014-12-30.
 */
public class Activity_about extends BaseActivity {

	/** 标题栏 */
	@ViewInject(R.id.tv_public_top_title)
	private TextView tv_public_top_title;
	/** 返回按钮 */
	@ViewInject(R.id.rl_public_back)
	private RelativeLayout rl_public_back;
	/** 创意介绍 */
	@ViewInject(R.id.ll_about_chuangyi)
	private LinearLayout ll_about_chuangyi;
	/** 功能介绍 */
	@ViewInject(R.id.ll_about_gongneng)
	private LinearLayout ll_about_gongneng;
	/** 联系客服 */
	@ViewInject(R.id.ll_about_kefu)
	private LinearLayout ll_about_kefu;
	/** 检查更新 */
	@ViewInject(R.id.ll_about_update)
	private LinearLayout ll_about_update;
	/** 市场合作 */
	@ViewInject(R.id.ll_about_hezuo)
	private LinearLayout ll_about_hezuo;
	/** 标题 */
	private static String mTitle = "关于锁屏赚";
	private long checkeUpdateFlag;

	/**
	 * @Description 不设置标题
	 * @author Created by qinxianyuzou on 2014-12-30.
	 * @param activity
	 */
	public static void luanch(Activity activity) {
		Intent intent = new Intent(activity, Activity_about.class);
		KeyGuardActivityManager.getInstance().goFoResult(activity, intent, KeyGuardActivityManager.MAIN_CODE);
	}

	/**
	 * @Description 设置标题
	 * @author Created by qinxianyuzou on 2014-12-30.
	 * @param activity
	 * @param title
	 */
	public static void luanch(Activity activity, String title) {
		mTitle = title;
		Intent intent = new Intent(activity, Activity_about.class);
		KeyGuardActivityManager.getInstance().goFoResult(activity, intent, KeyGuardActivityManager.MAIN_CODE);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_more_about);
		ViewUtils.inject(activity);
		initUI();
		initData();
	}

	@Override
	protected void initUI() {
		// TODO Auto-generated method stub
		tv_public_top_title.setText(mTitle);
		rl_public_back.setVisibility(View.VISIBLE);
		rl_public_back.setOnClickListener(this);
		ll_about_chuangyi.setOnClickListener(this);
		ll_about_gongneng.setOnClickListener(this);
		ll_about_kefu.setOnClickListener(this);
		ll_about_update.setOnClickListener(this);
		ll_about_hezuo.setOnClickListener(this);
	}

	@Override
	protected void initData() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.rl_public_back:
			finish();
			break;
		case R.id.ll_about_chuangyi:
			Activity_PublicWeb.luanch(activity, "创意介绍", "http://www.baidu.com");
			break;
		case R.id.ll_about_gongneng:
			Activity_PublicWeb.luanch(activity, "功能介绍", "http://www.baidu.com");
			break;
		case R.id.ll_about_kefu:
			Activity_PublicWeb.luanch(activity, "联系客服", "http://www.baidu.com");
			break;
		case R.id.ll_about_update:
			checkeUpdateFlag = Protocol.check_update(activity, setTag());
//			PublicUtil.updateAPP(activity, "");
			break;
		case R.id.ll_about_hezuo:
			Activity_PublicWeb.luanch(activity, "市场合作", "http://www.baidu.com");
			break;

		default:
			break;
		}
	}

	@Override
	public <T> void onHttpSuccess(long flag, JSONObject jsonString, T response) {
		// TODO Auto-generated method stub
		if (checkeUpdateFlag == flag) {
			ResponseUpdate responseUpdate = (ResponseUpdate) response;
			if (responseUpdate.getCode().equals(Code.CODE_SUCCESS)) {
				Update_Entity update_Entity = responseUpdate.getData();
				if (update_Entity.getApp_version() > PublicUtil.getVersionCode(activity)) {
					PublicUtil.updateAPP(activity, update_Entity.getDownload_url());
				}
			}
		}
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
