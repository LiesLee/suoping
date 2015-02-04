package com.example.activity.main;

import java.lang.ref.WeakReference;

import android.annotation.SuppressLint;
import android.app.KeyguardManager;
import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.Toast;

import com.example.activity.common.KeyGuardActivityManager;
import com.example.activity.earnings.Activity_earnings;
import com.example.activity.invitation.Activity_invitation;
import com.example.activity.more.Activity_more;
import com.example.activity.shop.Activity_shop;
import com.example.keyguard.LockActivity;
import com.example.keyguard.LockService;
import com.example.keyguard.R;
import com.example.util.LogUtil;
import com.example.util.PublicUtil;
import com.tencent.android.tpush.XGIOperateCallback;
import com.tencent.android.tpush.XGPushManager;
import com.tencent.android.tpush.common.Constants;
import com.tencent.android.tpush.service.XGPushService;
import com.tencent.android.tpush.service.cache.CacheManager;
import com.umeng.analytics.MobclickAgent;

public class MainActivity extends TabActivity implements OnCheckedChangeListener {

	private static final String CELLPHOME_NUMBER = "cellphone_number";
	private static final String PASSEORD = "password";
	private static final String REGISTER_LOGIN = "register_login";
	private TabHost tabHost;
	private RadioGroup radioderGroup;

	private static String TAG = "QINZDLOCK";

	private RelativeLayout sliderLayout = null;

	private AnimationDrawable animArrowDrawable = null;

	private Context mContext = null;

	public static int MSG_LOCK_SUCESS = 1;

	public static MainActivity instance = null;
	private Message m = null;

	@SuppressLint("NewApi")
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		instance = this;
		tabHost = this.getTabHost();
		tabHost.addTab(tabHost.newTabSpec("1").setIndicator("1").setContent(new Intent(this, Activity_earnings.class)));
		tabHost.addTab(tabHost.newTabSpec("2").setIndicator("2").setContent(new Intent(this, Activity_shop.class)));
		tabHost.addTab(tabHost.newTabSpec("3").setIndicator("3")
				.setContent(new Intent(this, Activity_invitation.class)));
		tabHost.addTab(tabHost.newTabSpec("4").setIndicator("4").setContent(new Intent(this, Activity_more.class)));

		radioderGroup = (RadioGroup) findViewById(R.id.main_radio);
		radioderGroup.setOnCheckedChangeListener(this);
		radioderGroup.check(R.id.mainTabs_radio_earnings);// 默认第一个按钮

		KeyguardManager mKeyguardManager = null;
		KeyguardManager.KeyguardLock mKeyguardLock = null;
		mKeyguardManager = (KeyguardManager) this.getSystemService(Context.KEYGUARD_SERVICE);
		mKeyguardLock = mKeyguardManager.newKeyguardLock("zdLock 1");
		mKeyguardLock.disableKeyguard();

		Button btn1 = new Button(this);
		btn1.setText("---------------------");
		btn1.setId(1);

		btn1.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// loginQQ("1103513011");
				Toast.makeText(MainActivity.this, "GO to LockActivity!", Toast.LENGTH_SHORT).show();
				Intent intent = new Intent(MainActivity.this, LockActivity.class);
				startActivity(intent);
			}
		});

		startService(new Intent(MainActivity.this, LockService.class));
		
		initXG();
	}

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		switch (checkedId) {
		case R.id.mainTabs_radio_earnings:
			tabHost.setCurrentTabByTag("1");
			break;
		case R.id.mainTabs_radio_shop:
			tabHost.setCurrentTabByTag("2");
			break;
		case R.id.mainTabs_radio_invitation:
			tabHost.setCurrentTabByTag("3");
			break;
		case R.id.mainTabs_radio_more:
			tabHost.setCurrentTabByTag("4");
			break;
		}

	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		MobclickAgent.onResume(this);
	}

	@Override
	protected void onPause() {

		Log.i("hhz", "onPause");
		/*
		 * if (MainActivity.instance !=null) { MainActivity.instance.finish(); }
		 */
		if (LockActivity.instance != null) {
			LockActivity.instance.finish();
		}
		MobclickAgent.onPause(this);
		super.onPause();
	}

	private long mExitTime;

	@Override
	public boolean dispatchKeyEvent(KeyEvent event) {
		if (event.getKeyCode() == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN
				&& event.getRepeatCount() == 0) {
			if ((System.currentTimeMillis() - mExitTime) > 2000) {
				mExitTime = System.currentTimeMillis();
				PublicUtil.showToast(instance, "再按一次退出");
			} else {
				KeyGuardActivityManager.getInstance().cleanActivity();
			}
			return true;
		}
		return super.dispatchKeyEvent(event);
	}

	/**
	 * @Description 初始化信鸽
	 * @author Created by qinxianyuzou on 2014-11-24.
	 */
	private void initXG() {
		// // 0.注册数据更新监听器
		// updateListViewReceiver = new MsgReceiver();
		// IntentFilter intentFilter = new IntentFilter();
		// intentFilter.addAction("com.qq.xgdemo.activity.UPDATE_LISTVIEW");
		// registerReceiver(updateListViewReceiver, intentFilter);
		// 1.获取设备Token
		LogUtil.w(Constants.LogTag, "获取设备Token");
		Handler handler = new HandlerExtension(MainActivity.this);
		m = handler.obtainMessage();
		// 注册接口
		final String uidString = PublicUtil.getUserInfo(mContext).getId();
		LogUtil.w(Constants.LogTag, uidString);
		XGPushManager.registerPush(getApplicationContext(), uidString, new XGIOperateCallback() {
			@Override
			public void onSuccess(Object data, int flag) {
				LogUtil.w(Constants.LogTag, "+++ register push sucess. token:" + data);
				m.obj = "+++ register push sucess. token:" + data;
				m.sendToTarget();
				CacheManager.getRegisterInfo(getApplicationContext());
			}

			@Override
			public void onFail(Object data, int errCode, String msg) {
				LogUtil.w(Constants.LogTag, "+++ register push fail. token:" + data + ", errCode:" + errCode + ",msg:"
						+ msg);
				m.obj = "+++ register push fail. token:" + data + ", errCode:" + errCode + ",msg:" + msg;
				m.sendToTarget();
			}
		});
		Context context = getApplicationContext();
		Intent service = new Intent(context, XGPushService.class);
		context.startService(service);
	}

	private static class HandlerExtension extends Handler {
		WeakReference<MainActivity> mActivity;

		HandlerExtension(MainActivity activity) {
			mActivity = new WeakReference<MainActivity>(activity);
		}

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			MainActivity theActivity = mActivity.get();
			if (theActivity == null) {
				theActivity = new MainActivity();
			}
			if (msg != null) {
				LogUtil.w(Constants.LogTag, msg.obj.toString());
				// TextView textView = (TextView)
				// theActivity.findViewById(R.id.tv_token);
				// textView.setText(XGPushConfig.getToken(theActivity));
				// Log.d("token", XGPushConfig.getToken(theActivity));

			}
			// XGPushManager.registerCustomNotification(theActivity,
			// "BACKSTREET", "BOYS", System.currentTimeMillis() + 5000, 0);
		}
	}
}
