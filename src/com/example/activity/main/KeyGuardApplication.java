package com.example.activity.main;

import android.app.Application;

import com.example.util.UserManager;
import com.umeng.analytics.MobclickAgent;
import com.umeng.socialize.common.SocializeConstants;

public class KeyGuardApplication extends Application {
	private static KeyGuardApplication mInstance;

	public static KeyGuardApplication getInstance() {
		return mInstance;
	}

	@Override
	public void onCreate() {
		mInstance = this;
		UserManager.getInstance();
		MobclickAgent.updateOnlineConfig(getApplicationContext());
	}

}
