package com.example.util;

import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
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
import com.example.entity.Download_APK_Install;
import com.example.entity.UserInfo;
import com.example.keyguard.R;
import com.example.keyguard.StartInstalledBroadcast;
import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.HttpHandler;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.bean.SocializeEntity;
import com.umeng.socialize.controller.UMSocialService;
import com.umeng.socialize.controller.listener.SocializeListeners.SnsPostListener;
import com.umeng.socialize.media.QQShareContent;
import com.umeng.socialize.media.SinaShareContent;
import com.umeng.socialize.media.SmsShareContent;
import com.umeng.socialize.media.TencentWbShareContent;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.sso.QZoneSsoHandler;
import com.umeng.socialize.sso.SmsHandler;
import com.umeng.socialize.sso.UMQQSsoHandler;
import com.umeng.socialize.weixin.controller.UMWXHandler;
import com.umeng.socialize.weixin.media.CircleShareContent;
import com.umeng.socialize.weixin.media.WeiXinShareContent;

/**
 * @Description 公共工具类
 * @author Created by qinxianyuzou on 2014-12-24.
 */
public class PublicUtil {
	private final static String TAG = PublicUtil.class.getSimpleName();
	/** 下载目录 **/
	public final static String DOWNLOAD_APP_PATH = Environment.getExternalStorageDirectory() + File.separator
			+ "SuoPingZhuan";

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
			appInfo.sourceDir = apkPath;
			appInfo.publicSourceDir = apkPath;
			// ActivityInfo appInfo = info.activities[0];
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
	 * @Description 判断apk是否运行
	 * @author Created by qinxianyuzou on 2015-1-9.
	 * @param context
	 * @param packageName
	 * @return
	 */
	public static boolean isRunningAPK(Context context, String packageName) {
		ActivityManager am = (ActivityManager) context.getSystemService(Activity.ACTIVITY_SERVICE);
		List<RunningAppProcessInfo> infos = am.getRunningAppProcesses();
		for (RunningAppProcessInfo rapi : infos) {
			if (rapi.processName.equals(packageName))
				return true;
		}
		return false;
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
		// PackageManager pManager=context.getPackageManager();
	}

	/**
	 * @Description 监听软件安装状态
	 * @author Created by qinxianyuzou on 2015-1-9.
	 * @param context
	 * @param packagename
	 */
	public static void startInstalledBroadcast(Context context, String packagename) {
		IntentFilter intentFilter = new IntentFilter();
		intentFilter.addAction("startInstalledBroadcast");
		context.registerReceiver(new StartInstalledBroadcast(packagename), intentFilter);
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
		Intent intent = packageManager.getLaunchIntentForPackage(packagename);
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

	/** 遍历文件，路径结果 List */
	public static List<String> listFile = new ArrayList<String>();

	/**
	 * 遍历文件夹搜索文件
	 * 
	 * @param Path
	 *            搜索的目录
	 * @param Extension
	 *            扩展名
	 * @param IsIterative
	 *            是否进入子文件夹
	 */
	public static void getFiles(String Path, String Extension, boolean IsIterative) {
		listFile.clear();
		File[] files = new File(Path).listFiles();
		if (files != null && files.length != 0) {
			for (int i = 0; i < files.length; i++) {
				File f = files[i];
				if (f.isFile()) {
					if (f.getPath().substring(f.getPath().length() - Extension.length()).equals(Extension)) // 判断扩展名
						listFile.add(f.getPath());
					if (!IsIterative)
						break;
					// 忽略点文件（隐藏文件/文件夹）
				} else if (f.isDirectory() && f.getPath().indexOf("/.") == -1) {
					getFiles(f.getPath(), Extension, IsIterative);
				}
			}
		}
	}

	/**
	 * @Description 设置分享平台
	 * @author Created by qinxianyuzou on 2015-1-20.
	 * @param activity
	 * @param mController
	 * @param title
	 * @param fshareMSG
	 * @param url
	 */
	public static void setShare(Activity activity, UMSocialService mController, String title, String fshareMSG,
			String url) {
		// ShareListener shareListener = new ShareListener(activity);
		// mController.postShare(activity, SHARE_MEDIA.QQ, shareListener);
		// mController.postShare(activity, SHARE_MEDIA.QZONE, shareListener);
		// mController.postShare(activity, SHARE_MEDIA.WEIXIN, shareListener);
		// mController.postShare(activity, SHARE_MEDIA.WEIXIN_CIRCLE,
		// shareListener);
		// mController.postShare(activity, SHARE_MEDIA.SINA, shareListener);
		// mController.postShare(activity, SHARE_MEDIA.SMS, shareListener);
		// mController.postShare(activity, SHARE_MEDIA.TENCENT, shareListener);
		shareQQ(activity, mController, title, fshareMSG, url);
		shareWeibo(activity, mController, title, fshareMSG, url);
		shareSMS(activity, mController, fshareMSG, url);
		shareWX(activity, mController, title, fshareMSG, url);
		shareWXCircle(activity, mController, title, fshareMSG, url);
		// // 设置分享内容
		// mController.setShareContent(fshareMSG);
		// 设置分享图片, 参数2为图片的url地址
		// mController.setShareMedia(new UMImage(activity,
		// "http://www.umeng.com/images/pic/banner_module_social.png"));
		// 设置分享图片，参数2为本地图片的资源引用
		// mController.setShareMedia(new UMImage(activity, R.drawable.logo));
		// mController.setShareMedia(new UMImage(activity, url));
	}

	/**
	 * @Description 分享微博
	 * @author Created by qinxianyuzou on 2015-1-14.
	 * @param activity
	 */
	private static void shareWeibo(Activity activity, UMSocialService mController, String title, String fshareMSG,
			String url) {
		SinaShareContent sinaShareContent = new SinaShareContent();
		// 设置朋友圈title
		sinaShareContent.setTitle(title);
		sinaShareContent.setShareContent(fshareMSG);
		sinaShareContent.setShareImage(new UMImage(activity, R.drawable.logo));
		if (!StringUtils.isEmpty(url)) {
			sinaShareContent.setTargetUrl(url);
		}
		mController.setShareMedia(sinaShareContent);
		TencentWbShareContent tencentWbShareContent = new TencentWbShareContent();
		// 设置朋友圈title
		tencentWbShareContent.setTitle(title);
		tencentWbShareContent.setShareContent(fshareMSG);
		tencentWbShareContent.setShareImage(new UMImage(activity, R.drawable.logo));
		if (!StringUtils.isEmpty(url)) {
			tencentWbShareContent.setTargetUrl(url);
		}
		mController.setShareMedia(tencentWbShareContent);
	}

	/**
	 * @Description 分享微信
	 * @author Created by qinxianyuzou on 2015-1-14.
	 * @param activity
	 */
	private static void shareWX(Activity activity, UMSocialService mController, String title, String fshareMSG,
			String url) {
		String appID = "wx2f8d9e440cb2d07d";
		String appSecret = "c4efeef102f7df2c59e12a71b2f2c59a";
		// 添加微信平台
		UMWXHandler wxHandler = new UMWXHandler(activity, appID, appSecret);
		wxHandler.addToSocialSDK();
		// 添加微信朋友圈
		UMWXHandler wxCircleHandler = new UMWXHandler(activity, appID, appSecret);
		wxCircleHandler.setToCircle(true);
		wxCircleHandler.addToSocialSDK();
		//
		WeiXinShareContent shareContent = new WeiXinShareContent();
		// 设置朋友圈title
		shareContent.setTitle(title);
		shareContent.setShareContent(fshareMSG);
		shareContent.setShareImage(new UMImage(activity, R.drawable.logo));
		if (!StringUtils.isEmpty(url)) {
			shareContent.setTargetUrl(url);
		}
		mController.setShareMedia(shareContent);
	}

	/**
	 * @Description 分享朋友圈
	 * @author Created by qinxianyuzou on 2015-1-20.
	 * @param activity
	 * @param mController
	 * @param title
	 * @param fshareMSG
	 * @param url
	 */
	private static void shareWXCircle(Activity activity, UMSocialService mController, String title, String fshareMSG,
			String url) {
		CircleShareContent shareContent = new CircleShareContent();
		// 设置朋友圈title
		shareContent.setTitle(title);
		shareContent.setShareContent(fshareMSG);
		shareContent.setShareImage(new UMImage(activity, R.drawable.logo));
		if (!StringUtils.isEmpty(url)) {
			shareContent.setTargetUrl(url);
		}
		mController.setShareMedia(shareContent);
	}

	/**
	 * @Description 分享qq和qq空间
	 * @author Created by qinxianyuzou on 2015-1-14.
	 * @param activity
	 */
	private static void shareQQ(Activity activity, UMSocialService mController, String title, String fshareMSG,
			String url) {
		// 参数1为当前Activity，参数2为开发者在QQ互联申请的APP ID，参数3为开发者在QQ互联申请的APP kEY.
		UMQQSsoHandler qqSsoHandler = new UMQQSsoHandler(activity, "100424468", "c7394704798a158208a74ab60104f0ba");
		qqSsoHandler.addToSocialSDK();
		QZoneSsoHandler qZoneSsoHandler = new QZoneSsoHandler(activity, "100424468", "c7394704798a158208a74ab60104f0ba");
		qZoneSsoHandler.addToSocialSDK();
		//
		QQShareContent shareContent = new QQShareContent();
		shareContent.setTitle(title);
		shareContent.setShareImage(new UMImage(activity, R.drawable.logo));
		shareContent.setShareContent(fshareMSG);
		if (!StringUtils.isEmpty(url)) {
			shareContent.setTargetUrl(url);
		}
		mController.setShareMedia(shareContent);
	}

	/**
	 * @Description 分享短信
	 * @author Created by qinxianyuzou on 2015-1-14.
	 * @param activity
	 */
	private static void shareSMS(Activity activity, UMSocialService mController, String fshareMSG, String url) {
		// 添加短信
		SmsHandler smsHandler = new SmsHandler();
		smsHandler.addToSocialSDK();
		SmsShareContent shareContent = new SmsShareContent();
		shareContent.setShareContent(fshareMSG + ":" + url);
		mController.setShareMedia(shareContent);
	}

	@SuppressWarnings({ "deprecation", "rawtypes" })
	public static void updateAPP(final Activity activity, String url) {
		String apkName[] = url.split("/");
		final String downFile = DOWNLOAD_APP_PATH + "/" + apkName[apkName.length - 1];
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
				LogUtil.d("updateAPP", "<==========handler");
				DecimalFormat decimalFormat = new DecimalFormat("0%");
				LogUtil.d("updateAPP", "total:" + total);
				LogUtil.d("updateAPP", "current:" + current);
				LogUtil.d("updateAPP", "(float) current / (float) total:" + (float) current / (float) total);
				LogUtil.d("updateAPP", "decimalFormat:" + decimalFormat.format((float) current / (float) total));
				LogUtil.d("updateAPP", "handler==========>");
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

	@SuppressWarnings({ "deprecation", "rawtypes" })
	public static void downloadAPP(final Activity activity, String url) {
		String apkName[] = url.split("/");
		final String downFile = DOWNLOAD_APP_PATH + "/" + apkName[apkName.length - 1] + ".apk";
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
				LogUtil.d("downloadAPP", "<==========handler");
				DecimalFormat decimalFormat = new DecimalFormat("0%");
				LogUtil.d("downloadAPP", "total:" + total);
				LogUtil.d("downloadAPP", "current:" + current);
				LogUtil.d("downloadAPP", "(float) current / (float) total:" + (float) current / (float) total);
				LogUtil.d("downloadAPP", "decimalFormat:" + decimalFormat.format((float) current / (float) total));
				LogUtil.d("downloadAPP", "handler==========>");
				mNotification.contentView.setTextViewText(R.id.content_view_text1,
						decimalFormat.format((float) current / (float) total));
				mNotification.contentView.setProgressBar(R.id.content_view_progress, (int) total, (int) current, false);
				mNotificationManager.notify(3567, mNotification);
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
				if (msg.indexOf("maybe the file has downloaded completely") > 0) {
					showToast(activity, "文件已经下载完成");
					installAPK(activity, downFile);
				}
				if (msg.indexOf("Target host must not be null") > 0) {
					showToast(activity, "文件下载失败，下载文件不存在");
				}
			}

		});
	}

	/**
	 * 获取所有已下载的应用并返回集合
	 * 
	 * @return List<Download_APK_Install>
	 */
	public static List<Download_APK_Install> getDownloadAppsEntity() {
		Context ctx;
		List<Download_APK_Install> apps = new ArrayList<>();
		getFiles(DOWNLOAD_APP_PATH, "apk", true);
		if (listFile.size() != 0) {
			ctx = KeyGuardApplication.getInstance().getApplicationContext();
			for (String path : listFile) {
				Download_APK_Install apk = new Download_APK_Install();
				// apk.setAppIcon(getApkIcon(ctx, path));
				apk.setAppName(getAPPName(ctx, path));
				apk.setAppPath(path);
				apk.setInstalled(isApkInstalled(ctx, getPackageName(ctx, path)));
				apk.setFileSize(formatSizeM(getFileSize(path)));
				apps.add(apk);
			}
			return apps;
		} else {
			return apps;
		}
	}

	/**
	 * 获取所有已下载的应用并返回集合
	 * 
	 * @return List<Download_APK_Install>
	 */
	public static List<Download_APK_Install> getDownloadAppsEntityIsInstalled() {
		Context ctx;
		List<Download_APK_Install> apps = new ArrayList<>();
		getFiles(DOWNLOAD_APP_PATH, "apk", true);
		if (listFile.size() != 0) {
			ctx = KeyGuardApplication.getInstance().getApplicationContext();
			for (String path : listFile) {
				Download_APK_Install apk = new Download_APK_Install();
				// apk.setAppIcon(getApkIcon(ctx, path));
				apk.setAppName(getAPPName(ctx, path));
				apk.setAppPath(path);
				apk.setInstalled(isApkInstalled(ctx, getPackageName(ctx, path)));
				apk.setFileSize(formatSizeM(getFileSize(path)));
				if (apk.isInstalled()) {
					apps.add(apk);
				}
			}
			return apps;
		} else {
			return apps;
		}
	}

	/**
	 * 根据路径获取文件大小
	 * 
	 * @param path
	 * @return 大小 单位m
	 */
	public static double getFileSize(String path) {
		File f = new File(path);
		if (f.exists() && f.isFile()) {
			double size = f.length();
			return size / 1024 / 1024;
		} else {
			return 0;
		}
	}

	/**
	 * 格式化文件大小保留两位小数并且后面带m
	 * 
	 * @param size
	 * @return 字符串size 后面带 m
	 */
	public static String formatSizeM(double size) {
		DecimalFormat df = new DecimalFormat("#.00");
		return String.valueOf(df.format(size)) + "m";
	}
}
