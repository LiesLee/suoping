package com.example.http.base;

import com.example.util.PublicUtil;

import android.app.Service;
import android.content.Intent;
import android.os.Environment;
import android.os.IBinder;

public class InstallService extends Service {

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		String sdDir = "";
		boolean sdCardExist = Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED); // 判断sd卡是否存在
		if (sdCardExist) {
			sdDir = Environment.getExternalStorageDirectory().toString();// 获取跟目录
		}
		String downFile = sdDir + "/suopingzhuan/app.apk";
		PublicUtil.installAPK(this, downFile);
		super.onCreate();
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		return super.onStartCommand(intent, flags, startId);
	}

}
