package com.example.keyguard;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * @Description 监听安装状态
 * @author Created by qinxianyuzou on 2015-1-9.
 */
public class StartInstalledBroadcast extends BroadcastReceiver {
	private String mPackageName = "";

	public StartInstalledBroadcast(String mPackageName) {
		super();
		this.mPackageName = mPackageName;
	}

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		if (intent.getAction().equals("android.intent.action.PACKAGE_ADDED")) {
			String packageName = intent.getDataString();
			Log.i("Test", "---------------" + packageName);
			if (packageName.equals(mPackageName)) {

			}
		}

		if (intent.getAction().equals("android.intent.action.PACKAGE_REMOVED")) {
			String packageName = intent.getDataString();
			Log.i("Test", "---------------" + "PACKAGE_REMOVED" + packageName);
		}
	}

}
