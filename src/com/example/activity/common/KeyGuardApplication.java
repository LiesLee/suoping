package com.example.activity.common;

import android.app.Application;

public class KeyGuardApplication extends Application {
	private static KeyGuardApplication mInstance;

	public static KeyGuardApplication getInstance() {
		return mInstance;
	}

	@Override
	public void onCreate() {
		mInstance = this;
	}
}
