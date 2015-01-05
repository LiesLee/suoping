package com.example.util;

import java.io.File;
import java.text.DecimalFormat;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.example.activity.common.KeyGuardApplication;
import com.example.entity.UserInfo;
import com.example.http.InstallService;
import com.example.keyguard.R;
import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.HttpHandler;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;

/**
 * @Description 公共工具类
 * @author Created by qinxianyuzou on 2014-12-24.
 */
public class PublicUtil {
	private final static String TAG = PublicUtil.class.getSimpleName();

	/**
	 * @Description 短时间toast
	 * @author Created by qinxianyuzou on 2014-12-24.
	 * @param context
	 * @param text
	 *            内容
	 */
	public static void showToast(Context context, String text) {
		Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
	}

	public static boolean isApkDebugable() {
		try {
			ApplicationInfo info = KeyGuardApplication.getInstance().getApplicationInfo();
			return (info.flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0;
		} catch (Exception e) {

		}
		return false;
	}

	/**
	 * @Description 获取用户实体缓存
	 * @author Created by qinxianyuzou on 2015-1-1.
	 * @param context
	 * @return
	 */
	public static UserInfo getUserInfo(Context context) {
		UserInfo userInfo = new Gson().fromJson(
				SharedPreferenceUtil.getInstance(context).getString(SharedPreferenceUtil.USERINFO), UserInfo.class);
		return userInfo;
	}

	/**
	 * @Description 判断是否有网络
	 * @author Created by qinxianyuzou on 2013-12-1.
	 * @param mContext
	 * @return
	 */
	public static boolean isNetworkAvailable(Context mContext) {
		boolean flag = false;
		ConnectivityManager cm = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (cm != null) {
			NetworkInfo ni = cm.getActiveNetworkInfo();
			if (ni != null) {
				flag = ni.isAvailable();
			}
		}
		return flag;

	}

	/**
	 * @Description 获取软件名
	 * @author Created by qinxianyuzou on 2014-12-26.
	 * @param context
	 * @param apkPath
	 * @return
	 */
	public static String getAPPName(Context context, String apkPath) {
		PackageManager pm = context.getPackageManager();
		PackageInfo info = pm.getPackageArchiveInfo(apkPath, PackageManager.GET_ACTIVITIES);
		if (info != null) {
			ApplicationInfo appInfo = info.applicationInfo;
			// 软件名
			String label = appInfo.loadLabel(pm).toString();
			try {
				return label;
			} catch (OutOfMemoryError e) {
				Log.e("ApkIconLoader", e.toString());
			}
		}
		return "";
	}

	/**
	 * @Description 获取icon
	 * @author Created by qinxianyuzou on 2014-12-26.
	 * @param context
	 * @param apkPath
	 * @return
	 */
	public static Drawable getApkIcon(Context context, String apkPath) {
		PackageManager pm = context.getPackageManager();
		PackageInfo info = pm.getPackageArchiveInfo(apkPath, PackageManager.GET_ACTIVITIES);
		if (info != null) {
			ApplicationInfo appInfo = info.applicationInfo;
			appInfo.sourceDir = apkPath;
			appInfo.publicSourceDir = apkPath;
			try {
				return appInfo.loadIcon(pm);
			} catch (OutOfMemoryError e) {
				Log.e("ApkIconLoader", e.toString());
			}
		}
		return null;
	}

	/**
	 * @Description 获取apk版本号
	 * @author Created by qinxianyuzou on 2015-1-5.
	 * @param context
	 * @param apkPath
	 * @return
	 */
	public static int getApkVersionCode(Context context, String apkPath) {
		PackageManager pm = context.getPackageManager();
		PackageInfo info = pm.getPackageArchiveInfo(apkPath, PackageManager.GET_ACTIVITIES);
		if (info != null) {
			try {
				return info.versionCode;
			} catch (OutOfMemoryError e) {
				Log.e("ApkIconLoader", e.toString());
			}
		}
		return -1;
	}

	/**
	 * @Description 获取包名
	 * @author Created by qinxianyuzou on 2014-12-26.
	 * @param context
	 * @param apkPath
	 * @return
	 */
	public static String getPackageName(Context context, String apkPath) {
		PackageManager pm = context.getPackageManager();
		PackageInfo info = pm.getPackageArchiveInfo(apkPath, PackageManager.GET_ACTIVITIES);
		if (info != null) {
			ApplicationInfo appInfo = info.applicationInfo;
			// 包名
			String packageName = appInfo.packageName;
			try {
				return packageName;
			} catch (OutOfMemoryError e) {
				Log.e("ApkIconLoader", e.toString());
			}
		}
		return "";
	}

	/**
	 * @Description 判断是否安装
	 * @author Created by qinxianyuzou on 2014-12-26.
	 * @param context
	 * @param packagename
	 *            包名
	 * @return
	 */
	public static boolean isApkInstalled(Context context, String packagename) {
		PackageManager localPackageManager = context.getPackageManager();
		try {
			PackageInfo localPackageInfo = localPackageManager.getPackageInfo(packagename,
					PackageManager.GET_UNINSTALLED_PACKAGES);
			return true;
		} catch (PackageManager.NameNotFoundException localNameNotFoundException) {
			return false;
		}

	}

	/**
	 * @Description 安装apk
	 * @author Created by qinxianyuzou on 2014-12-26.
	 * @param context
	 * @param apkPath
	 */
	public static void installAPK(Context context, String apkPath) {
		Intent intent = new Intent();
		intent.setAction(Intent.ACTION_VIEW);
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		intent.setDataAndType(Uri.parse("file://" + apkPath), "application/vnd.android.package-archive");
		context.startActivity(intent);
	}

	/**
	 * @Description 打开apk
	 * @author Created by qinxianyuzou on 2014-12-26.
	 * @param context
	 * @param packagename
	 *            包名
	 */
	public static void openAPK(Context context, String packagename) {
		PackageManager packageManager = context.getPackageManager();
		Intent intent = new Intent();
		intent = packageManager.getLaunchIntentForPackage(packagename);
		context.startActivity(intent);
	}

	/**
	 * @Description 获取版本号
	 * @author Created by qinxianyuzou on 2015-1-5.
	 * @param context
	 * @return
	 */
	public static int getVersionCode(Context context) {
		try {
			PackageInfo pi = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
			return pi.versionCode;
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}

	@SuppressWarnings({ "deprecation", "rawtypes" })
	public static void downloadAPP(final Activity activity, String url) {
		String sdDir = "";
		boolean sdCardExist = Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED); // 判断sd卡是否存在
		if (sdCardExist) {
			sdDir = Environment.getExternalStorageDirectory().toString();// 获取跟目录
		}
		final String downFile = sdDir + "/suopingzhuan/app.apk";
		// File file = new File(downFile);
		// if (file.exists()) {
		// if (getApkVersionCode(activity, downFile) > getVersionCode(activity))
		// {
		// installAPK(activity, downFile);
		// return;
		// } else {
		// file.delete();
		// }
		// }
		HttpUtils http = new HttpUtils();
		final Notification mNotification;
		final NotificationManager mNotificationManager;
		mNotificationManager = (NotificationManager) activity.getSystemService(Context.NOTIFICATION_SERVICE);
		mNotification = new Notification(R.drawable.logo, "正在下载...", System.currentTimeMillis());
		mNotification.contentView = new RemoteViews(activity.getPackageName(), R.layout.download);
		mNotification.flags = Notification.FLAG_INSISTENT;
		mNotification.sound = null;
		HttpHandler handler = http.download(url, downFile, true, true, new RequestCallBack<File>() {

			@Override
			public void onStart() {
				// tv_info.setText("conn...");
				PublicUtil.showToast(activity, "正在下载...");
			}

			@Override
			public void onLoading(long total, long current, boolean isUploading) {
				// tv_info.setText(current + "/" + total);
				LogUtil.d("handler", "<==========handler");
				DecimalFormat decimalFormat = new DecimalFormat("0%");
				LogUtil.d("handler", "total:" + total);
				LogUtil.d("handler", "current:" + current);
				LogUtil.d("handler", "(float) current / (float) total:" + (float) current / (float) total);
				LogUtil.d("handler", "decimalFormat:" + decimalFormat.format((float) current / (float) total));
				LogUtil.d("handler", "handler==========>");
				mNotification.contentView.setTextViewText(R.id.content_view_text1,
						decimalFormat.format((float) current / (float) total));
				mNotification.contentView.setProgressBar(R.id.content_view_progress, (int) total, (int) current, false);
				mNotificationManager.notify(3566, mNotification);
			}

			@Override
			public void onSuccess(ResponseInfo<File> responseInfo) {
				mNotificationManager.cancel(3566);
				PublicUtil.showToast(activity, "下载完成...");
				PublicUtil.installAPK(activity, downFile);
			}

			@Override
			public void onFailure(HttpException error, String msg) {
				// tv_info.setText(msg);
				LogUtil.d("handler", msg);
				if (msg.equals("maybe the file has downloaded completely")) {
					showToast(activity, "文件已经下载完成");
					installAPK(activity, downFile);
				}
			}

		});
	}
}