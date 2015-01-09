package com.example.activity.earnings;

import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.activity.common.BaseActivity;
import com.example.activity.common.KeyGuardActivityManager;
import com.example.activity.more.Activity_MyInfo;
import com.example.activity.more.My_Download_Adapter;
import com.example.entity.Download_APK_Install;
import com.example.keyguard.R;
import com.example.util.PublicUtil;
import com.example.util.UIHelper;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.bitmap.PauseOnScrollListener;
import com.lidroid.xutils.view.annotation.ViewInject;

import java.util.List;

/**
 * @Description 待激活收益
 * @author Created by qinxianyuzou on 2014-12-30.
 */
public class Activity_NotActive extends BaseActivity {

	/** 标题栏 */
	@ViewInject(R.id.tv_public_top_title)
	private TextView tv_public_top_title;
	/** 返回按钮 */
	@ViewInject(R.id.rl_public_back)
	private RelativeLayout rl_public_back;
	/** 列表 */
	@ViewInject(R.id.lv_shop_body)
	private ListView rListView;
	/** 标题 */
	private static String mTitle = "待激活收益";
    private BitmapUtils bitmapUtils;
    private My_Not_Active_Adapter adapter;
    private List<Download_APK_Install> apps;

    /**
	 * @Description 不设置标题
	 * @author Created by qinxianyuzou on 2014-12-30.
	 * @param activity
	 */
	public static void luanch(Activity activity) {
		Intent intent = new Intent(activity, Activity_NotActive.class);
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
		Intent intent = new Intent(activity, Activity_MyInfo.class);
		KeyGuardActivityManager.getInstance().goFoResult(activity, intent, KeyGuardActivityManager.MAIN_CODE);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_myinfo);
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
        bitmapUtils = new BitmapUtils(activity);
        adapter = new My_Not_Active_Adapter(this, bitmapUtils);
        rListView.setOnScrollListener(new PauseOnScrollListener(bitmapUtils, false, true));
        rListView.setAdapter(adapter);
	}

	@Override
	protected void initData() {
        apps = PublicUtil.getDownloadAppsEntityIsInstalled();
        adapter.setData(apps);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		    case R.id.rl_public_back :
		        this.finish();
		        break;

		    default:
		        break;
		}

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
