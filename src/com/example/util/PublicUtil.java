package com.example.util;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import com.example.activity.common.KeyGuardApplication;
import com.example.entity.UserInfo;
import com.google.gson.Gson;

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
	private boolean isApkInstalled(Context context, String packagename) {
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
	private void installAPK(Context context, String apkPath) {
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
	private void openAPK(Context context, String packagename) {
		PackageManager packageManager = context.getPackageManager();
		Intent intent = new Intent();
		intent = packageManager.getLaunchIntentForPackage(packagename);
		context.startActivity(intent);
	}

}
