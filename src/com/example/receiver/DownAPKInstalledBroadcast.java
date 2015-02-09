package com.example.receiver;

import com.example.activity.common.Activity_DownloadWeb;
import com.example.util.LogUtil;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class DownAPKInstalledBroadcast extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		LogUtil.d(setTag(), "intent.getAction()==" + intent.getAction());
		if (intent.getAction().equals(Intent.ACTION_PACKAGE_ADDED)) {
			LogUtil.d(setTag(), "ACTION_PACKAGE_ADDED");
			String packageName = intent.getData().getSchemeSpecificPart();
			LogUtil.d(setTag(), "安装成功" + packageName);
			if (Activity_DownloadWeb.add_APK_Interface != null) {
				Activity_DownloadWeb.add_APK_Interface.addAPK(packageName);
			}
			// if (packageName.equals(mPackageName)) {
			// LogUtil.d(setTag(), "mPackageName:" + mPackageName);
			// PublicUtil.openAPK(activity, mPackageName);
			// countTime();
			// }
		}
	}

	public String setTag() {
		// TODO Auto-generated method stub
		return this.getClass().getSimpleName();
	}
}
