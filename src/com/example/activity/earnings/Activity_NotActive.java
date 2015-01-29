package com.example.activity.earnings;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.activity.common.BaseActivity;
import com.example.activity.common.KeyGuardActivityManager;
import com.example.activity.more.Activity_MyInfo;
import com.example.entity.SignIn_Entity;
import com.example.http.base.Code;
import com.example.http.base.Protocol;
import com.example.http.respose.ResponseDownAPP;
import com.example.http.respose.ResponseSignIn;
import com.example.keyguard.R;
import com.example.util.PublicUtil;
import com.example.util.UIHelper;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.bitmap.PauseOnScrollListener;
import com.lidroid.xutils.view.annotation.ViewInject;

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
	private List<SignIn_Entity> listData = new ArrayList<>();
	private APKInstalledBroadcast receiveBroadCast;
	private long downFlag;
	private long activationFlag;

	/** 存放当前调用了安装的包名 */
	private ArrayList<SignIn_Entity> packageList = new ArrayList<>();

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
		receiveBroadCast = new APKInstalledBroadcast();
		IntentFilter filter = new IntentFilter();
		filter.addAction(""); // 只有持有相同的action的接受者才能接收此广播
		registerReceiver(receiveBroadCast, filter);
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
		rListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				if (listData != null && listData.size() != 0) {
					final SignIn_Entity apk = listData.get(position);
					if (PublicUtil.isApkInstalled(activity, apk.getProcess_name())) {
						PublicUtil.APPTiming(activity, setTag(), apk.getProcess_name(), apk.getUse_time(),
								apk.getReturn_url());
					} else {
						UIHelper.showMsgProgressDialog(activity, "正在加载...");
						downFlag = Protocol.get_earn_downloadurl(activity, setTag(), apk.getEaid());
						packageList.add(apk);
					}
				}
			}
		});
	}

	@Override
	protected void initData() {
		// apps = PublicUtil.getDownloadAppsEntityIsInstalled();
		UIHelper.showMsgProgressDialog(activity, "正在加载...");
		activationFlag = Protocol.get_jihuo_earn(activity, setTag());
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		unregisterReceiver(receiveBroadCast);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.rl_public_back:
			this.finish();
			break;

		default:
			break;
		}

	}

	@Override
	public <T> void onHttpSuccess(long flag, JSONObject jsonString, T response) {
		// TODO Auto-generated method stub
		if (flag == downFlag) {
			UIHelper.cancelProgressDialog();
			final ResponseDownAPP responseDownAPP = (ResponseDownAPP) response;
			if (responseDownAPP.getCode().equals(Code.CODE_SUCCESS)) {
				new Thread(new Runnable() {
					@Override
					public void run() {
						// TODO Auto-generated method stub
						PublicUtil.downloadAPP(activity, responseDownAPP.getData().getDownload_url());
					}
				}).start();
			} else {
				showToast(responseDownAPP.getMsg());
			}
		}
		if (activationFlag == flag) {
			UIHelper.cancelProgressDialog();
			ResponseSignIn msgInfo = (ResponseSignIn) response;
			if (msgInfo.getCode().equals(Code.CODE_SUCCESS)) {
				listData = msgInfo.getData();
				adapter.setData(listData);
			}
		}
	}

	@Override
	public void onHttpError(long flag, VolleyError error) {
		// TODO Auto-generated method stub
		UIHelper.cancelProgressDialog();
		if (activationFlag == flag) {
			showToast("获取下载地址错误");
		}
	}

	@Override
	public String setTag() {
		// TODO Auto-generated method stub
		return this.getClass().getSimpleName();
	}

	/**
	 * @Description 安装广播
	 * @author Created by qinxianyuzou on 2015-1-26.
	 */
	private class APKInstalledBroadcast extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, Intent intent) {
			// 得到广播中得到的数据，并显示出来
			PackageManager manager = context.getPackageManager();
			if (intent.getAction().equals(Intent.ACTION_PACKAGE_ADDED)) {
				String packageName = intent.getData().getSchemeSpecificPart();
				// Toast.makeText(context, "安装成功" + packageName,
				// Toast.LENGTH_LONG).show();
				for (int i = 0; i < packageList.size(); i++) {
					if (packageName.equals(packageList.get(i).getProcess_name())) {
						PublicUtil.APPTiming(activity, setTag(), packageList.get(i).getProcess_name(),
								packageList.get(i).getUse_time(), packageList.get(i).getReturn_url());
						packageList.remove(i);
					}
				}
			}
		}

	}
}
