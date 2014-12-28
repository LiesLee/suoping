package com.example.activity.common;

import android.app.Application;

import com.example.util.UserManager;

public class KeyGuardApplication extends Application {
	private static KeyGuardApplication mInstance;

	public static KeyGuardApplication getInstance() {
		return mInstance;
	}

	@Override
	public void onCreate() {
		mInstance = this;
		UserManager.getInstance();
	}

}
